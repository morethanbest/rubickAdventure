package mygame.character;

import mygame.chaconfig.HeroConfig;

public class Hero extends SimpleCharacter{

    private int level;
    private final double hpGrow;
    private final double atkGrow;
    private final double mgkGrow;
    private int exp;
    public Hero(HeroConfig config) {
        super(config);
        this.level = 0;
        this.hpGrow = config.getHpGrow();
        this.atkGrow = config.getAtkGrow();
        this.mgkGrow = config.getMgkGrow();
    }

    private void checkLevelUp(){
        int nowLevel = exp / 10;
        if (nowLevel > level) {
            System.out.println("Level up!!! " + name + " level " + level + " -> " + nowLevel);
            int increase = nowLevel - level;
            hpBasic += hpGrow * increase;
            mgkBasic += mgkGrow * increase;
            atkBasic += atkGrow * increase;
            level = nowLevel;
        }
        initHeroFeature();
    }

    public void addExp(int newExp){
        exp += newExp;
        checkLevelUp();
    }

    public void initHeroFeature(){
        hpNow = hpBasic;
        hp = hpBasic;
        mgk = mgkBasic;
        atk = atkBasic;
        mgkDef = mgkDefBasic;
        atkDef = atkDefBasic;
        critDamage = critDamageBasic;
        critProp = critPropBasic;
    }

    @Override
    public String toString() {
        String heroStr = "Hero" + super.toString();
        return heroStr;
    }

    public int getLevel() {
        return level;
    }

    public double getHpGrow() {
        return hpGrow;
    }

    public double getAtkGrow() {
        return atkGrow;
    }

    public double getMgkGrow() {
        return mgkGrow;
    }

    public int getExp() {
        return exp;
    }
}
