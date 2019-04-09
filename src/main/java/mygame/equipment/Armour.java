package mygame.equipment;

import mygame.chaconfig.ArmourConfig;
import mygame.item.BagItem;
import mygame.type.ArmourType;
import mygame.type.BagItemType;

public abstract class Armour implements BagItem {

    private String name;
    private int level;//2
    private int enhanceLevel;
    private String feature1;//atkDef
    private double value1;//4
    private String feature2;//hp
    private double value2;//100

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

    public String getFeature1() {
        return feature1;
    }

    public void setFeature1(String feature1) {
        this.feature1 = feature1;
    }

    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public String getFeature2() {
        return feature2;
    }

    public void setFeature2(String feature2) {
        this.feature2 = feature2;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "[Name: " + name + ", Level:" + level + ", Feature: " + feature1 +  ", " + feature2 + ", Value: " + value1 + ", " + value2 + "]";
    }

    public abstract ArmourType getArmourType();

    @Override
    public BagItemType getBagItemType() {
        return BagItemType.armour;
    }
}
