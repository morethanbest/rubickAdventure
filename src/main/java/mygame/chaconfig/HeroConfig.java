package mygame.chaconfig;

import java.util.List;

public class HeroConfig extends SimpleCharacterConfig {


    private double hpGrow;
    private double mgkGrow;
    private double atkGrow;
    private List<HeroSkillConfig> skills;
    private List<WeaponConfig> weapons;
    private List<ArmourConfig> armours;

    public List<HeroSkillConfig> getSkills() {
        return skills;
    }

    public void setSkills(List<HeroSkillConfig> skills) {
        this.skills = skills;
    }

    public List<WeaponConfig> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<WeaponConfig> weapons) {
        this.weapons = weapons;
    }

    public List<ArmourConfig> getArmours() {
        return armours;
    }

    public void setArmours(List<ArmourConfig> armours) {
        this.armours = armours;
    }

    public double getHpGrow() {
        return hpGrow;
    }

    public void setHpGrow(double hpGrow) {
        this.hpGrow = hpGrow;
    }

    public double getMgkGrow() {
        return mgkGrow;
    }

    public void setMgkGrow(double mgkGrow) {
        this.mgkGrow = mgkGrow;
    }

    public double getAtkGrow() {
        return atkGrow;
    }

    public void setAtkGrow(double atkGrow) {
        this.atkGrow = atkGrow;
    }
}
