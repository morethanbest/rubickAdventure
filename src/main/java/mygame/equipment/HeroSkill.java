package mygame.equipment;

import mygame.chaconfig.HeroSkillConfig;
import mygame.item.BagItem;
import mygame.type.BagItemType;

public class HeroSkill extends Skill implements BagItem {

    private int level;
    private int enhanceLevel;
    private int coldDown;

    public HeroSkill(HeroSkillConfig config) {
        super(config);
        this.level = config.getLevel();
        this.enhanceLevel = 1;
        this.coldDown = config.getColdDown();
    }

    @Override
    public BagItemType getBagItemType() {
        return BagItemType.skill;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEnhanceLevel() {
        return enhanceLevel;
    }

    public void setEnhanceLevel(int enhanceLevel) {
        this.enhanceLevel = enhanceLevel;
    }

    public int getColdDown() {
        return coldDown;
    }

    public void setColdDown(int coldDown) {
        this.coldDown = coldDown;
    }
}
