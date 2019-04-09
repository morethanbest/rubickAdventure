package mygame.equipment;

import mygame.buff.BuffTemplate;
import mygame.chaconfig.ArmourConfig;
import mygame.chaconfig.BuffConfig;
import mygame.chaconfig.WeaponConfig;

import java.util.*;

public class EquipmentFactory {

    private static EquipmentFactory factory;

    public static final int MAX_WEAPON_LEVEL = 3;
    public static final int MAX_ARMOUR_LEVEL = 3;
    private final Map<Integer, List<WeaponConfig>> weaponMap;
    private final Map<Integer, List<ArmourConfig>> armourMap;
    private final Map<Weapon, WeaponConfig> createdWeapons;
    private final Map<Armour, ArmourConfig> createdArmours;
    private Random random;

    public static EquipmentFactory getInstance() {
        return factory;
    }
    public EquipmentFactory(List<WeaponConfig> weapons, List<ArmourConfig> armours) {
        this.weaponMap = generateWeaponMap(weapons);
        this.armourMap = generateArmourMap(armours);
        this.createdWeapons = new WeakHashMap<>();
        this.createdArmours = new WeakHashMap<>();
        random = new Random();
        factory = this;
    }

    private Map<Integer, List<WeaponConfig>> generateWeaponMap(List<WeaponConfig> weaponConfigs){
        Map<Integer, List<WeaponConfig>> weapons = new HashMap<>();
        for (int i = 1; i <= MAX_WEAPON_LEVEL; i++) {
            List<WeaponConfig> tmp = new ArrayList<>();
            for (WeaponConfig config : weaponConfigs) {
                if (config.getLevel() == i) {
                    tmp.add(config);
                }
            }
            weapons.put(i, tmp);
        }
        return weapons;
    }

    private Map<Integer, List<ArmourConfig>> generateArmourMap(List<ArmourConfig> armourConfigs){
        Map<Integer, List<ArmourConfig>> armours = new HashMap<>();
        for (int i = 1; i <= MAX_ARMOUR_LEVEL; i++) {
            List<ArmourConfig> tmp = new ArrayList<>();
            for (ArmourConfig config : armourConfigs) {
                if (config.getLevel() == i) {
                    tmp.add(config);
                }
            }
            armours.put(i, tmp);
        }
        return armours;
    }

    public Weapon getWeaponByLevel(int level){
        List<WeaponConfig> configs = weaponMap.get(level);
        int randomIndex = random.nextInt(configs.size());
        WeaponConfig config = configs.get(randomIndex);
        Weapon weapon = new Weapon(config);
        weapon.setValue(weapon.getValue() + random.nextDouble() * config.getRange());
        System.out.println("Weapon created: " + weapon);
        createdWeapons.put(weapon, config);
        return weapon;
    }

    public Armour getArmourByLevel(int level){
        List<ArmourConfig> configs = armourMap.get(level);
        int randomIndex = random.nextInt(configs.size());
        ArmourConfig config = configs.get(randomIndex);
        Armour armour = null;
        switch (config.getType()){
            case helmet:
                armour = new Helmet();
                break;
            case boot:
                armour = new Boot();
                break;
            case breastplate:
                armour = new Breastplate();
                break;
        }
        armour.setName(config.getName());
        armour.setFeature1(config.getFeature1());
        armour.setFeature2(config.getFeature2());
        armour.setLevel(config.getLevel());
        armour.setEnhanceLevel(0);
        armour.setValue1(config.getValue1() + random.nextDouble() * config.getRange1());
        armour.setValue2(config.getValue2() + random.nextDouble() * config.getRange2());
        System.out.println("Armour created: " + armour);
        createdArmours.put(armour, config);
        return armour;
    }

    public Weapon enhanceWeapon(Weapon weapon){
        WeaponConfig config = createdWeapons.get(weapon);
        weapon.setEnhanceLevel(weapon.getEnhanceLevel() + 1);
        double newValue = weapon.getValue() + random.nextDouble() * config.getEnhanceRange() + config.getEnhanceBasic();
        weapon.setValue(newValue);
        if(weapon.getWeaponSkill() != null) {
            List<BuffTemplate> buffTemplates = weapon.getWeaponSkill().getBuffs();
            List<BuffConfig> buffConfigs = config.getSkill().getBuffs();
            for (int i = 0; i < buffConfigs.size(); i++) {
                BuffTemplate bt = buffTemplates.get(i);
                BuffConfig bc = buffConfigs.get(i);
                bt.setValue(bt.getValue() + bc.getValueGrow());
                bt.setRatio(bt.getRatio() + bc.getRatioGrow());
            }
        }
        System.out.println("Weapon enhanced: " + weapon);
        return weapon;
    }

    public Armour enhanceArmour(Armour armour){
        ArmourConfig config = createdArmours.get(armour);
        armour.setEnhanceLevel(armour.getEnhanceLevel() + 1);
        armour.setValue1(armour.getValue1() + (armour.getValue1() + random.nextDouble() * config.getRange1()) / (10 - armour.getLevel()));
        armour.setValue2(armour.getValue2() + (armour.getValue2() + random.nextDouble() * config.getRange2()) / (10 - armour.getLevel()));
        System.out.println("Armour enhanced: "  + armour);
        return armour;
    }

}
