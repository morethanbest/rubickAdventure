package mygame.chaconfig;

import java.util.List;

public class SimpleCharacterConfig {

    private String name;
    private double hp;
    private double mgk;
    private double mgkDef;
    private double atk;
    private double atkDef;
    private double critDamage;
    private double critProp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getMgk() {
        return mgk;
    }

    public void setMgk(double mgk) {
        this.mgk = mgk;
    }

    public double getMgkDef() {
        return mgkDef;
    }

    public void setMgkDef(double mgkDef) {
        this.mgkDef = mgkDef;
    }

    public double getAtk() {
        return atk;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public double getAtkDef() {
        return atkDef;
    }

    public void setAtkDef(double atkDef) {
        this.atkDef = atkDef;
    }

    public double getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(double critDamage) {
        this.critDamage = critDamage;
    }

    public double getCritProp() {
        return critProp;
    }

    public void setCritProp(double critProp) {
        this.critProp = critProp;
    }

}
