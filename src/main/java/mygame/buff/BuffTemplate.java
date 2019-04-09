package mygame.buff;

import mygame.chaconfig.BuffConfig;
import mygame.character.SimpleCharacter;
import mygame.type.BuffType;
import mygame.type.DamageType;

import java.lang.reflect.Field;

public class BuffTemplate {

    private final BuffType type; // damage
    private final String name;
    private double value; // 30
    private final String feature; // mgk
    private final String featureAffect;
    private final DamageType damageType;//only damage buff uses this field
    private double ratio; // 0.5
    private final int round; // 1
    private final boolean toEnemy;

    public BuffTemplate(String name, BuffConfig config) {
        this.name = name;
        type = config.getType();
        value = config.getBasicValue();
        feature = config.getFeature();
        featureAffect = config.getFeatureAffect();
        ratio = config.getBasicRatio();
        round = config.getRound();
        toEnemy = config.isToEnemy();
        damageType = config.getDamageType();
    }

    public String getName() {
        return name;
    }

    public String getFeatureAffect() {
        return featureAffect;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public BuffType getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getFeature() {
        return feature;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public int getRound() {
        return round;
    }

    public boolean isToEnemy() {
        return toEnemy;
    }

    public Buff generateBuff(SimpleCharacter character){
        double fValue = 0, fValueBasic = 0;
        Buff buff = null;
        try {
            Field field = SimpleCharacter.class.getDeclaredField(feature);
            field.setAccessible(true);
            fValue = field.getDouble(character);
            Field fieldBasic = SimpleCharacter.class.getDeclaredField(feature + "Basic");
            fieldBasic.setAccessible(true);
            fValueBasic = fieldBasic.getDouble(character);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        String res = character.getName() + " add a new " + type + " buff " + name + (toEnemy ? " to enemy" : " to hero") +
                ", rounds " + round;
        double hpType = fValue * ratio + value;
        double featureType = fValueBasic * ratio + value;
        switch (type){
            case damage:
                res += ", damage " + hpType + " type " + damageType;
                buff = new DamageBuff(name, round, hpType, damageType);
                break;
            case weakness:
                res += ", weaken " + featureType + featureAffect;
                buff = new WeaknessBuff(name, round, featureType, featureAffect);
                break;
            case healing:
                res += ", healing " + hpType;
                buff = new HealingBuff(name, round, hpType);
                break;
            case enhancement:
                res += ", enhance " + featureType + featureAffect;
                buff = new EnhancementBuff(name, round, featureType, featureAffect);
                break;
        }
        System.out.println(res);
        return buff;
    }
}
