package mygame.chaconfig;

public class HeroSkillConfig extends SkillConfig {

    private int level;
    private int coldDown;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getColdDown() {
        return coldDown;
    }

    public void setColdDown(int coldDown) {
        this.coldDown = coldDown;
    }
}
