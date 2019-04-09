package mygame.chaconfig;

import mygame.type.ArmourType;

public class ArmourConfig {
    private String name; //Steel Helmet
    private ArmourType type; //helmet
    private int level; //2
    private String feature1; //atkDef
    private double value1; //4
    private double range1; //3
    private String feature2; //hp
    private double value2; //100
    private double range2; //50

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArmourType getType() {
        return type;
    }

    public void setType(ArmourType type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public double getRange1() {
        return range1;
    }

    public void setRange1(double range1) {
        this.range1 = range1;
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

    public double getRange2() {
        return range2;
    }

    public void setRange2(double range2) {
        this.range2 = range2;
    }
}
