package mygame.equipment;

import mygame.buff.BuffTemplate;
import mygame.chaconfig.BuffConfig;
import mygame.chaconfig.SkillConfig;
import mygame.item.BagItem;
import mygame.type.BagItemType;
import mygame.type.StatusType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Skill {
    private final String name;
    private final String description;
    private final StatusType status;
    private final List<BuffTemplate> buffs;

    protected Skill(SkillConfig config) {
        this.name = config.getName();
        this.description = config.getDescription();
        this.status = config.getStatus();
        this.buffs = new ArrayList<>();
        for (BuffConfig buffConfig : config.getBuffs()) {
            buffs.add(new BuffTemplate(name, buffConfig));
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public StatusType getStatus() {
        return status;
    }

    public List<BuffTemplate> getBuffs() {
        return buffs;
    }

//    @Override
//    public BagItemType getBagItemType() {
//        return BagItemType.skill;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(name, skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
