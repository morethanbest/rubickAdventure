package mygame.equipment;

import mygame.buff.BuffTemplate;
import mygame.chaconfig.BuffConfig;
import mygame.chaconfig.WeaponSkillConfig;
import mygame.type.StatusType;

import java.util.ArrayList;
import java.util.List;

public class WeaponSkill extends Skill{

    private final double prop; //0.25

    public WeaponSkill(WeaponSkillConfig config) {
        super(config);
        this.prop = config.getProp();
    }

    public double getProp() {
        return prop;
    }
}
