package mygame.game;

import mygame.character.HeroFactory;
import mygame.equipment.HeroSkill;
import mygame.item.SkillSlot;

public class HeroSkillSlots {

    private static final int MAX_SKILL_NUM = 4;

    private SkillSlot[] skillSlots = new SkillSlot[MAX_SKILL_NUM];

    public HeroSkillSlots() {
        for (int i = 0; i < MAX_SKILL_NUM; i++){
            skillSlots[i] = new SkillSlot();
        }
    }

    public HeroSkill unloadSkill(int index){
        HeroSkill skill = (HeroSkill) skillSlots[index].getSkill();
        skillSlots[index].setSkill(null);
        return skill;
    }
    public HeroSkill getSkill(int index){
        return (HeroSkill) skillSlots[index].getSkill();
    }
    public SkillSlot[] getSkillSlots() {
        return skillSlots;
    }

    public boolean equipSkill(HeroSkill skill){
        for (SkillSlot skillSlot :
                skillSlots) {
            if(skillSlot.getSkill() != null && skillSlot.getSkill().equals(skill)){
                HeroSkill skill1 = (HeroSkill) skillSlot.getSkill();
                for (int i = 0; i < skill.getEnhanceLevel(); i++) {
                    HeroFactory.getInstance().levelUp(skill1);
                }
                return true;
            }
        }
        for (SkillSlot skillSlot :
                skillSlots) {
            if(skillSlot.getSkill() == null){
                skillSlot.setSkill(skill);
                return true;
            }
        }
        return false;
    }
}
