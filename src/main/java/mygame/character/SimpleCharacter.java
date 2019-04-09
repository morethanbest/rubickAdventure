package mygame.character;

import mygame.buff.Buff;
import mygame.chaconfig.SimpleCharacterConfig;

public class SimpleCharacter {

    protected final String name;
    protected double hp;
    protected double hpNow;
    protected double mgk;
    protected double mgkDef;
    protected double atk;
    protected double atkDef;
    protected double critDamage;
    protected double critProp;

    protected double hpBasic;
    protected double mgkBasic;
    protected double mgkDefBasic;
    protected double atkBasic;
    protected double atkDefBasic;
    protected double critDamageBasic;
    protected double critPropBasic;

    protected SimpleCharacter(SimpleCharacterConfig config) {
        this.name = config.getName();
        this.hpBasic = config.getHp();
        this.hp = config.getHp();
        this.hpNow = config.getHp();
        this.mgk = config.getMgk();
        this.mgkDef = config.getMgkDef();
        this.atk = config.getAtk();
        this.atkDef = config.getAtkDef();
        this.critDamage = config.getCritDamage();
        this.critProp = config.getCritProp();

        this.mgkBasic = config.getMgk();
        this.mgkDefBasic = config.getMgkDef();
        this.atkBasic = config.getAtk();
        this.atkDefBasic = config.getAtkDef();
        this.critDamageBasic = config.getCritDamage();
        this.critPropBasic = config.getCritProp();
    }

    public String getName() {
        return name;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getHpNow() {
        return hpNow;
    }

    public void setHpNow(double hpNow) {
        this.hpNow = hpNow;
    }

    public double getHpBasic() {
        return hpBasic;
    }

    public void setHpBasic(double hpBasic) {
        this.hpBasic = hpBasic;
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


    public double getMgkBasic() {
        return mgkBasic;
    }

    public void setMgkBasic(double mgkBasic) {
        this.mgkBasic = mgkBasic;
    }

    public double getMgkDefBasic() {
        return mgkDefBasic;
    }

    public void setMgkDefBasic(double mgkDefBasic) {
        this.mgkDefBasic = mgkDefBasic;
    }

    public double getAtkBasic() {
        return atkBasic;
    }

    public void setAtkBasic(double atkBasic) {
        this.atkBasic = atkBasic;
    }

    public double getAtkDefBasic() {
        return atkDefBasic;
    }

    public void setAtkDefBasic(double atkDefBasic) {
        this.atkDefBasic = atkDefBasic;
    }

    public double getCritDamageBasic() {
        return critDamageBasic;
    }

    public void setCritDamageBasic(double critDamageBasic) {
        this.critDamageBasic = critDamageBasic;
    }

    public double getCritPropBasic() {
        return critPropBasic;
    }

    public void setCritPropBasic(double critPropBasic) {
        this.critPropBasic = critPropBasic;
    }

    @Override
    public String toString() {
        String s = "[Name: " + name + ", HP_MAX: " + hp + ", HP_NOW: " +
                 hpNow + ", ATK: " + atk + ", MGK: " + mgk + "]";
        return s;
    }
}
