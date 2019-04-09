package mygame.item;

import mygame.equipment.Skill;
import mygame.type.BagItemType;

public class SkillSlot implements Slot {

    private Skill skill;

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }

    @Override
    public BagItem getBagItem() {
        return (BagItem) skill;
    }
}
