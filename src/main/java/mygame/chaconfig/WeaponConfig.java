package mygame.chaconfig;

public class WeaponConfig {
    private String name; //Stein Staff
    private int level; //2
    private String feature;
    private double basic; //20
    private double range; //10
    private double enhanceBasic; //5
    private double enhanceRange; //5
    private WeaponSkillConfig skill;

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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public double getBasic() {
        return basic;
    }

    public void setBasic(double basic) {
        this.basic = basic;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getEnhanceBasic() {
        return enhanceBasic;
    }

    public void setEnhanceBasic(double enhanceBasic) {
        this.enhanceBasic = enhanceBasic;
    }

    public double getEnhanceRange() {
        return enhanceRange;
    }

    public void setEnhanceRange(double enhanceRange) {
        this.enhanceRange = enhanceRange;
    }

    public WeaponSkillConfig getSkill() {
        return skill;
    }

    public void setSkill(WeaponSkillConfig skill) {
        this.skill = skill;
    }
}
