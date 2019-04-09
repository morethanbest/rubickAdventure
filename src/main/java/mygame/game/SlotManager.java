package mygame.game;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.collections.SetListenerHelper;
import mygame.equipment.*;
import mygame.item.ArmourSlot;
import mygame.item.BagItem;
import mygame.item.SkillSlot;
import mygame.item.WeaponSlot;
import mygame.type.BagItemType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlotManager {

    private HeroBag heroBag;

    private final HeroSkillSlots heroSkillSlots;

    private final WeaponSlot weaponSlot;

    private final ArmourSlot<Helmet> helmetArmourSlot;

    private final ArmourSlot<Boot> bootArmourSlot;

    private final ArmourSlot<Breastplate> breastplateArmourSlot;

    public SlotManager() {
        heroBag = new HeroBag();
        heroSkillSlots = new HeroSkillSlots();
        weaponSlot = new WeaponSlot();
        helmetArmourSlot = new ArmourSlot<>();
        bootArmourSlot = new ArmourSlot<>();
        breastplateArmourSlot = new ArmourSlot<>();
    }

    public BagItem equip(int index){
        BagItem item = heroBag.getBagItem(index);
        BagItem unloadItem = null;
        boolean isEquipSuccess = true;
        switch (item.getBagItemType()){
            case skill:
                isEquipSuccess = heroSkillSlots.equipSkill((HeroSkill) item);
                break;
            case weapon:
                unloadItem = weaponSlot.getBagItem();
                weaponSlot.setWeapon((Weapon) item);
                break;
            case armour:
                Armour armour = (Armour) item;
                switch (armour.getArmourType()){
                    case helmet:
                        unloadItem = helmetArmourSlot.getBagItem();
                        helmetArmourSlot.setArmour((Helmet) item);
                        break;
                    case breastplate:
                        unloadItem = breastplateArmourSlot.getBagItem();
                        breastplateArmourSlot.setArmour((Breastplate) item);
                        break;
                    case boot:
                        unloadItem = bootArmourSlot.getBagItem();
                        bootArmourSlot.setArmour((Boot) item);
                        break;
                }
                break;
        }
        if (isEquipSuccess)
            heroBag.removeBagItem(index);
        if (unloadItem != null)
            heroBag.setBagItem(unloadItem);

        return unloadItem;
    }

    private BagItem getSlotItem(int index){
        BagItem bagItem;
        switch (index){
            case 0:
            case 1:
            case 2:
            case 3:
                bagItem = heroSkillSlots.getSkill(index);
                break;
            case 4:
                bagItem = weaponSlot.getWeapon();
                break;
            case 5:
                bagItem = helmetArmourSlot.getArmour();
                break;
            case 6:
                bagItem = breastplateArmourSlot.getArmour();
                break;
            case 7:
                bagItem = bootArmourSlot.getArmour();
                break;
            default:
                return null;
        }
        return bagItem;
    }

    private BagItem unloadSlotItem(int index){
        BagItem bagItem;
        switch (index){
            case 0:
            case 1:
            case 2:
            case 3:
                bagItem = heroSkillSlots.unloadSkill(index);
                break;
            case 4:
                bagItem = weaponSlot.unloadWeapon();
                break;
            case 5:
                bagItem = helmetArmourSlot.unloadArmour();
                break;
            case 6:
                bagItem = breastplateArmourSlot.unloadArmour();
                break;
            case 7:
                bagItem = bootArmourSlot.unloadArmour();
                break;
            default:
                return null;
        }
        return bagItem;
    }

    public JSONObject getSlotItemJson(int index){
        BagItem bagItem = getSlotItem(index);
        if (bagItem == null){
            JSONObject object = new JSONObject();
            object.put("name", "none");
            return object;
        }

        return (JSONObject) JSONObject.toJSON(bagItem);
    }

    public void unLoad(int index){
        BagItem bagItem = unloadSlotItem(index);
        heroBag.setBagItem(bagItem);
    }

    public JSONObject getSlotInfo(){
        JSONObject object = new JSONObject();
        JSONObject object1 = new JSONObject();
        object1.put("name", "none");
        object1.put("level", 0);
        object1.put("enhanceLevel", 0);
        int i = 0;
        for (SkillSlot skillSlot : heroSkillSlots.getSkillSlots()){
            String s = "skill" + i;
            BagItem skill = skillSlot.getBagItem();
            JSONObject tmp = new JSONObject();
            if(skill != null){
                tmp.put("name", skill.getName());
                tmp.put("level", skill.getLevel());
                tmp.put("enhanceLevel", skill.getEnhanceLevel());
                object.put(s, tmp);
            }
            else {
                object.put(s, object1);
            }
            i++;
        }
        if(weaponSlot.getBagItem() != null) {
            JSONObject tmp = new JSONObject();
            tmp.put("name", weaponSlot.getBagItem().getName());
            tmp.put("level", weaponSlot.getBagItem().getLevel());
            tmp.put("enhanceLevel", weaponSlot.getBagItem().getEnhanceLevel());
            object.put("weapon", tmp);
        }
        else
            object.put("weapon", object1);
        if(breastplateArmourSlot.getBagItem() != null) {
            JSONObject tmp = new JSONObject();
            tmp.put("name", breastplateArmourSlot.getBagItem().getName());
            tmp.put("level", breastplateArmourSlot.getBagItem().getLevel());
            tmp.put("enhanceLevel", breastplateArmourSlot.getBagItem().getEnhanceLevel());
            object.put("breastplate", tmp);
        }
        else
            object.put("breastplate", object1);
        if(bootArmourSlot.getBagItem() != null) {
            JSONObject tmp = new JSONObject();
            tmp.put("name", bootArmourSlot.getBagItem().getName());
            tmp.put("level", bootArmourSlot.getBagItem().getLevel());
            tmp.put("enhanceLevel", bootArmourSlot.getBagItem().getEnhanceLevel());
            object.put("boot", tmp);
        }
        else
            object.put("boot", object1);
        if(helmetArmourSlot.getBagItem() != null) {
            JSONObject tmp = new JSONObject();
            tmp.put("name", helmetArmourSlot.getBagItem().getName());
            tmp.put("level", helmetArmourSlot.getBagItem().getLevel());
            tmp.put("enhanceLevel", helmetArmourSlot.getBagItem().getEnhanceLevel());
            object.put("helmet", tmp);
        }
        else
            object.put("helmet", object1);
        return object;
    }

    public void equipSkill(HeroSkill heroSkill){
        heroSkillSlots.equipSkill(heroSkill);
    }

    public boolean newItem(BagItem item){
        if(heroBag.isFull())
            return false;
        heroBag.setBagItem(item);
        return true;
    }

    public BagItem getIndex(int index){
        BagItem item = heroBag.getBagItem(index);
        return item;
    }

    public void removeIndex(int index){
        heroBag.removeBagItem(index);
    }
    public JSONArray getBagInfo(){
        return heroBag.getBagInfo();
    }

    public HeroBag getHeroBag() {
        return heroBag;
    }

    public void setHeroBag(HeroBag heroBag) {
        this.heroBag = heroBag;
    }

    public HeroSkillSlots getHeroSkillSlots() {
        return heroSkillSlots;
    }

    public WeaponSlot getWeaponSlot() {
        return weaponSlot;
    }

    public ArmourSlot<Helmet> getHelmetArmourSlot() {
        return helmetArmourSlot;
    }

    public ArmourSlot<Boot> getBootArmourSlot() {
        return bootArmourSlot;
    }

    public ArmourSlot<Breastplate> getBreastplateArmourSlot() {
        return breastplateArmourSlot;
    }


}
