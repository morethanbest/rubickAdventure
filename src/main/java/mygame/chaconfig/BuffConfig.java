package mygame.chaconfig;

import mygame.type.BuffType;
import mygame.type.DamageType;

public class BuffConfig {

    private BuffType type; // damage
    private double basicValue; // 30
    private double valueGrow; // 5
    private String feature; // mgk
    private String featureAffect;
    private double basicRatio; // 0.5
    private double ratioGrow; // 0.25
    private int round; // 1
    private boolean toEnemy;
    private DamageType damageType;

    public BuffType getType() {
        return type;
    }

    public void setType(BuffType type) {
        this.type = type;
    }

    public double getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(double basicValue) {
        this.basicValue = basicValue;
    }

    public double getValueGrow() {
        return valueGrow;
    }

    public void setValueGrow(double valueGrow) {
        this.valueGrow = valueGrow;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getFeatureAffect() {
        return featureAffect;
    }

    public void setFeatureAffect(String featureAffect) {
        this.featureAffect = featureAffect;
    }

    public double getBasicRatio() {
        return basicRatio;
    }

    public void setBasicRatio(double basicRatio) {
        this.basicRatio = basicRatio;
    }

    public double getRatioGrow() {
        return ratioGrow;
    }

    public void setRatioGrow(double ratioGrow) {
        this.ratioGrow = ratioGrow;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public boolean isToEnemy() {
        return toEnemy;
    }

    public void setToEnemy(boolean toEnemy) {
        this.toEnemy = toEnemy;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }
}
