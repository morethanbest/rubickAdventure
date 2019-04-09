package mygame.character;

import mygame.chaconfig.EnemyConfig;
import mygame.chaconfig.SimpleCharacterConfig;
import mygame.equipment.EnemySkill;

public class Enemy extends SimpleCharacter{

    private int level;
    private int exp;
    private int gold;
    private EnemySkill enemySkill;

    public Enemy(EnemyConfig config) {
        super(config);
        this.level = config.getLevel();
        this.exp = config.getExp();
        this.gold = config.getGold();
        if (config.getSkills() != null)
            this.enemySkill = new EnemySkill(config.getSkills());
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

    public EnemySkill getEnemySkill() {
        return enemySkill;
    }

    public void setEnemySkill(EnemySkill enemySkill) {
        this.enemySkill = enemySkill;
    }

    @Override
    public String toString() {
        return "Enemy" + super.toString();
    }
}
