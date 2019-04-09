package mygame.chaconfig;

public class EnemyConfig extends SimpleCharacterConfig {

    private int level;
    private int exp;
    private int gold;
    private EnemySkillConfig skills;

    public EnemySkillConfig getSkills() {
        return skills;
    }

    public void setSkills(EnemySkillConfig skills) {
        this.skills = skills;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
