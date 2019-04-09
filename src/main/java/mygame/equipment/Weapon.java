package mygame.equipment;

import mygame.chaconfig.WeaponConfig;
import mygame.item.BagItem;
import mygame.type.BagItemType;

import java.util.Random;

public class Weapon implements BagItem {
    private String name; //Stein Staff
    private int level; //2
    private int enhanceLevel;
    private String feature;
    private double value; //20

    private WeaponSkill weaponSkill;

    public Weapon(WeaponConfig config) {
        this.name = config.getName();
        this.level = config.getLevel();
        this.enhanceLevel = 0;
        this.feature = config.getFeature();
        this.value = config.getBasic();
        if(config.getSkill() != null)
            this.weaponSkill = new WeaponSkill(config.getSkill());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEnhanceLevel() {
        return enhanceLevel;
    }

    public void setEnhanceLevel(int enhanceLevel) {
        this.enhanceLevel = enhanceLevel;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public WeaponSkill getWeaponSkill() {
        return weaponSkill;
    }

    public void setWeaponSkill(WeaponSkill weaponSkill) {
        this.weaponSkill = weaponSkill;
    }

    @Override
    public String toString() {
        String weaponStr = "Weapon[Name: " + name + ", Level: " + level + ", Feature: "
                + feature + ", EnhanceLevel: " + enhanceLevel +
                ", Value: " + value + ", Weapon Skill: " + (weaponSkill == null ? "None" : weaponSkill.getName() + "]");
        return weaponStr;
    }

    @Override
    public BagItemType getBagItemType() {
        return BagItemType.weapon;
    }
}
