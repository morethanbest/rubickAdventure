package mygame.equipment;

import mygame.chaconfig.EnemySkillConfig;
import mygame.chaconfig.SkillConfig;

public class EnemySkill extends Skill {

    private int coldDown;

    public EnemySkill(EnemySkillConfig config) {
        super(config);
        this.coldDown = config.getColdDown();
    }

    public int getColdDown() {
        return coldDown;
    }

    public void setColdDown(int coldDown) {
        this.coldDown = coldDown;
    }
}
