package mygame.game;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mygame.buff.Buff;
import mygame.buff.BuffTemplate;
import mygame.buff.DamageBuff;
import mygame.chaconfig.EnemyConfig;
import mygame.chaconfig.HeroConfig;
import mygame.character.*;
import mygame.equipment.*;
import mygame.item.BagItem;
import mygame.item.BagSlot;
import mygame.item.SkillSlot;
import mygame.type.DamageType;
import mygame.type.StatusType;
import mygame.util.LogTracker;
import mygame.util.YamlReader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;

@Component
public class BattleField {

    private static final int MAX_ROUNDS = 100;

    @Resource
    private LogTracker logTracker;

    private Hero hero;

    private Enemy enemyNow;

    private Enemy enemyNext;

    private State hState;

    private State eState;

    private List<Buff> heroBuffs;

    private List<Buff> enemyBuffs;

    private SlotManager slotManager;

    private DropItemStrategy strategy;

    private WarShop warShop;

    public BattleField() {
        init();
        this.heroBuffs = new ArrayList<>();
        this.enemyBuffs = new ArrayList<>();
        this.slotManager = new SlotManager();
        this.strategy = new HighStategy();
        this.warShop = new WarShop();
    }

    private void init(){
        YamlReader<List<EnemyConfig>> reader1 = new YamlReader<>();
        List<EnemyConfig> enemyConfig = reader1.readConfig("character/enemy");
        EnemyFactory enemyFactory = new EnemyFactory(enemyConfig);
        this.enemyNow = enemyFactory.createEnemyByLevel(1);
        this.enemyNext = enemyFactory.createEnemyByLevel(1);
    }

    public void initHero(String characterType){
        YamlReader<HeroConfig> reader = new YamlReader<>();
        HeroConfig config = reader.readConfig("character/" + characterType);
        HeroFactory factory = new HeroFactory(config);
        new EquipmentFactory(config.getWeapons(), config.getArmours());
        this.hero = factory.createHero();
    }

    public void nextTurn(){
        enemyNow = enemyNext;
        enemyNext = EnemyFactory.getInstance().createEnemyByLevel(hero.getLevel() / 10 + 1);
    }

    public boolean fight() {
        refreshHero();
        refreshBattleGround();
        Map<HeroSkill, Integer> coldDownMap = new HashMap<>();
        int enemySkillColdDown = 0;
        EnemySkill enemySkill = enemyNow.getEnemySkill();
        if (enemySkill != null) {
            enemySkillColdDown = enemySkill.getColdDown();
        }
        for(SkillSlot slot : slotManager.getHeroSkillSlots().getSkillSlots()){
            HeroSkill skill = (HeroSkill) slot.getSkill();
            if (skill == null) continue;
            coldDownMap.put(skill, skill.getColdDown());
        }


        for (int i = 0; i < MAX_ROUNDS; i++){
            System.out.println("================= Round " + i + " =================");
            logTracker.addLog("================= 第 " + i + " 回合 =================");
            for(Map.Entry<HeroSkill, Integer> entry : coldDownMap.entrySet()){
                HeroSkill skill = entry.getKey();
                Integer coldDown = entry.getValue();
                if (coldDown > 0) {
                    entry.setValue(coldDown - 1);
                } else {
                    if(hState.doSkill(false, skill)){
                        if (skill.getStatus() != null)
                            eState = getPostState(eState.getStatusType(), skill.getStatus());
                        entry.setValue(skill.getColdDown());
                    }
                    hState = new NormalState();
                }
            }

            if(enemySkill != null ) {
                if (enemySkillColdDown > 0)
                    enemySkillColdDown--;
                else {
                    if(eState.doSkill(true, enemySkill)){
                        enemySkillColdDown = enemySkill.getColdDown();
                        if (enemySkill.getStatus() != null)
                            hState = getPostState(eState.getStatusType(), enemySkill.getStatus());
                    }

                }
            }

            eState.doAtk(true);
            hState.doAtk(false);

            eState = new NormalState();
            hState = new NormalState();
            Iterator<Buff> enemyIterator = enemyBuffs.iterator();
             while (enemyIterator.hasNext()){
                 Buff buff = enemyIterator.next();
                 logTracker.addLog(buff.takeEffect(enemyNow));
                 if (buff.isOver())
                     enemyIterator.remove();
            }
            Iterator<Buff> heroIterator = heroBuffs.iterator();
            while (heroIterator.hasNext()){
                Buff buff = heroIterator.next();
                logTracker.addLog(buff.takeEffect(hero));
                if (buff.isOver())
                    heroIterator.remove();
            }

            logTracker.addLog(hero.toString());
            logTracker.addLog(enemyNow.toString());
            if (enemyNow.getHpNow() <= 0) {
                hero.addExp(enemyNow.getExp());
                HeroSkill skill = strategy.getSkillByLevel(enemyNow.getLevel());
                newItem(skill);
                Armour armour = strategy.getArmourByLevel(enemyNow.getLevel());
                newItem(armour);
                Weapon weapon = strategy.getWeaponByLevel(enemyNow.getLevel());
                newItem(weapon);
                refreshHero();
                return true;
            }
            else if (hero.getHpNow() <= 0)
                return false;
        }

        return false;
    }
    private void refreshHero(){
        System.out.println("Refresh hero basic features");
        logTracker.addLog("刷新初始状态");
        hero.initHeroFeature();
        Weapon weapon = slotManager.getWeaponSlot().getWeapon();
        if(weapon != null)
            enhanceFeature(weapon.getFeature(), weapon.getValue());
        List<Armour> armours = new ArrayList<>();
        armours.add(slotManager.getHelmetArmourSlot().getArmour());
        armours.add(slotManager.getBreastplateArmourSlot().getArmour());
        armours.add(slotManager.getBootArmourSlot().getArmour());
        for (Armour a :
                armours) {
            if (a != null) {
                enhanceFeature(a.getFeature1(), a.getValue1());
                enhanceFeature(a.getFeature2(), a.getValue2());
            }
        }
        logTracker.addLog(hero.toString());
        logTracker.addLog(enemyNow.toString());
    }

    private void refreshBattleGround() {
        hState = new NormalState();
        eState = new NormalState();
        enemyBuffs = new ArrayList<>();
        heroBuffs = new ArrayList<>();
    }

    public void unLoad(int index){
        slotManager.unLoad(index);
    }

    public int equip(int index){
        BagItem item = slotManager.getIndex(index);
        BagItem unload = slotManager.equip(index);
        int in = 0;
        switch (item.getBagItemType()){
            case weapon:
                if (unload != null) {
                    Weapon weaponUnload = (Weapon) unload;
                    System.out.println("Weapon " + weaponUnload.getName() + " unloaded.");
                    enhanceFeature(weaponUnload.getFeature(), -weaponUnload.getValue());
                }
                Weapon weapon = (Weapon) item;
                System.out.println("Weapon " + weapon.getName() + " equipped.");
                enhanceFeature(weapon.getFeature(), weapon.getValue());
                in = slotManager.getHeroSkillSlots().getSkillSlots().length;
                break;
            case armour:
                if (unload != null) {
                    Armour armourUnload = (Armour) unload;
                    System.out.println("Armour " + armourUnload.getName() + " unloaded.");
                    enhanceFeature(armourUnload.getFeature1(), -armourUnload.getValue1());
                    enhanceFeature(armourUnload.getFeature2(), -armourUnload.getValue2());
                }
                Armour armour = (Armour) item;
                System.out.println("Armour " + armour.getName() + " equipped.");
                enhanceFeature(armour.getFeature1(), armour.getValue1());
                enhanceFeature(armour.getFeature2(), armour.getValue2());
                in = armour.getArmourType().getOrder() + slotManager.getHeroSkillSlots().getSkillSlots().length + 1;
        }
        refreshHero();
        return in;
    }

    public void newItem(BagItem item){
        if(item != null)
            slotManager.newItem(item);
    }

    public boolean enhance(int index){
        BagItem item = slotManager.getIndex(index);
        switch (item.getBagItemType()){
            case armour:
                return warShop.enhanceArmour((Armour) item);
            case weapon:
                return warShop.enhanceWeapon((Weapon) item);
            default:
                return false;
        }
    }

    public void sell(int index){
        BagItem item = slotManager.getIndex(index);
        warShop.soldBagItem(item);
        slotManager.removeIndex(index);
    }

    public int getGold(){
        return warShop.getGold();
    }
    private void enhanceFeature(String feature, double value){
        Field field;
        try {
            field = SimpleCharacter.class.getDeclaredField(feature);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return;
        }
        field.setAccessible(true);
        try {
            double origin = field.getDouble(hero);
            field.setDouble(hero, origin + value);
            System.out.println("Feature updated " + feature + " value " + origin + " -> " + (origin + value));
            if (feature.equals("hp")){
                Field hpNow = SimpleCharacter.class.getDeclaredField("hpNow");
                hpNow.setAccessible(true);
                hpNow.setDouble(hero, field.getDouble(hero));
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private State getPostState(StatusType before, StatusType after){
        switch (before){
            case normal:
                return getStateByStatus(after);
            case stun:
                return new StunState();
            case silence:
                if (after == StatusType.normal || after == StatusType.silence)
                    return new SlienceState();
                else
                    return new StunState();
            case disarm:
                if (after == StatusType.normal || after == StatusType.disarm)
                    return new DisarmState();
                else
                    return new StunState();
        }
        return getStateByStatus(before);
    }

    private State getStateByStatus(StatusType statusType){
        switch (statusType){
            case normal:
                return new NormalState();
            case stun:
                return new StunState();
            case silence:
                return new SlienceState();
            case disarm:
                return new DisarmState();
        }
        return null;
    }

    public void soldItem(int index){
        BagItem item = slotManager.getIndex(index);
        if (item != null)
            warShop.soldBagItem(item);
        slotManager.removeIndex(index);
    }

    public JSONArray getHeroBagInfo(){
        return slotManager.getBagInfo();
    }
    public JSONObject getSlotInfo(){
        return slotManager.getSlotInfo();
    }

    public JSONObject getSlotItemInfo(int index){
        return slotManager.getSlotItemJson(index);
    }

    public JSONObject getHeroInfo(){
        return (JSONObject) JSONObject.toJSON(hero);
    }

    public JSONObject getEnemyNowInfo(){
        return (JSONObject) JSONObject.toJSON(enemyNow);
    }

    public JSONObject getEnemyNextInfo(){
        return (JSONObject) JSONObject.toJSON(enemyNext);
    }
    public JSONObject getBagItemInfoByIndex(int index){
        BagItem bagItem = slotManager.getIndex(index);
        return (JSONObject) JSONObject.toJSON(bagItem);
    }
    interface State {
        void doAtk(boolean isEnemy);
        boolean doSkill(boolean isEnemy, Skill skill);
        StatusType getStatusType();
    }

    class NormalState implements State{
        @Override
        public void doAtk(boolean isEnemy) {
            DamageBuff damageBuff ;
            if(isEnemy) {
                logTracker.addLog(enemyNow.getName() + "攻击" + hero.getName());
                damageBuff = new DamageBuff("Attack", 1, enemyNow.getAtk(), DamageType.atk);
                heroBuffs.add(damageBuff);
            }
            else {
                logTracker.addLog(hero.getName() + "攻击" + enemyNow.getName());
                damageBuff = new DamageBuff("Attack", 1, hero.getAtk(), DamageType.atk);
                enemyBuffs.add(damageBuff);
            }
        }

        @Override
        public boolean doSkill(boolean isEnemy, Skill skill) {
            List<BuffTemplate> buffTemplates = skill.getBuffs();
            logTracker.addLog((isEnemy ? enemyNow.getName() : hero.getName()) + "释放技能" + skill.getName());
            for (BuffTemplate temp :
                    buffTemplates) {
                Buff buff = temp.generateBuff(isEnemy ? enemyNow : hero);
                if (temp.isToEnemy()) {
                    enemyBuffs.add(buff);
                } else {
                    heroBuffs.add(buff);
                }
            }
            return true;
        }

        @Override
        public StatusType getStatusType() {
            return StatusType.normal;
        }
    }

    class StunState implements State{

        @Override
        public void doAtk(boolean isEnemy) {
            logTracker.addLog((isEnemy ? enemyNow.getName() : hero.getName()) + "被眩晕，无法攻击。");
            System.out.println((isEnemy ? enemyNow.getName() : hero.getName()) + " cannot attack.");
        }

        @Override
        public boolean doSkill(boolean isEnemy, Skill skill) {
            logTracker.addLog((isEnemy ? enemyNow.getName() : hero.getName()) + "被眩晕，无法释放技能" + skill.getName());
            System.out.println((isEnemy ? enemyNow.getName() : hero.getName()) + " Stunned.");
            return false;
        }

        @Override
        public StatusType getStatusType() {
            return StatusType.stun;
        }
    }

    class SlienceState implements State{

        @Override
        public void doAtk(boolean isEnemy) {
            DamageBuff damageBuff ;
            if(isEnemy) {
                logTracker.addLog(enemyNow.getName() + "攻击" + hero.getName());
                damageBuff = new DamageBuff("Attack", 1, enemyNow.getAtk(), DamageType.atk);
                heroBuffs.add(damageBuff);
            }
            else {
                logTracker.addLog(hero.getName() + "攻击" + enemyNow.getName());
                damageBuff = new DamageBuff("Attack", 1, hero.getAtk(), DamageType.atk);
                enemyBuffs.add(damageBuff);
            }
        }

        @Override
        public boolean doSkill(boolean isEnemy, Skill skill) {
            logTracker.addLog((isEnemy ? enemyNow.getName() : hero.getName()) + "被沉默，无法释放技能" + skill.getName());
            System.out.println((isEnemy ? enemyNow.getName() : hero.getName()) + " Silenced.");
            return false;
        }

        @Override
        public StatusType getStatusType() {
            return StatusType.silence;
        }
    }

    class DisarmState implements State{

        @Override
        public void doAtk(boolean isEnemy) {
            logTracker.addLog((isEnemy ? enemyNow.getName() : hero.getName()) + "被缴械，无法攻击。");
            System.out.println((isEnemy ? enemyNow.getName() : hero.getName()) + " cannot attack.");
        }

        @Override
        public boolean doSkill(boolean isEnemy, Skill skill) {
            List<BuffTemplate> buffTemplates = skill.getBuffs();
            logTracker.addLog((isEnemy ? enemyNow.getName() : hero.getName()) + "释放技能" + skill.getName());
            for (BuffTemplate temp :
                    buffTemplates) {
                Buff buff = temp.generateBuff(isEnemy ? enemyNow : hero);
                if (temp.isToEnemy()) {
                    enemyBuffs.add(buff);
                } else {
                    heroBuffs.add(buff);
                }
            }
            return true;
        }

        @Override
        public StatusType getStatusType() {
            return StatusType.disarm;
        }
    }

}
