package mygame.chaconfig;

import mygame.type.StatusType;

import java.util.List;

public class SkillConfig {
    private String name;
    private String description;
    private StatusType status;
    private List<BuffConfig> buffs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public List<BuffConfig> getBuffs() {
        return buffs;
    }

    public void setBuffs(List<BuffConfig> buffs) {
        this.buffs = buffs;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SkillConfig) {
            return name.equals(((SkillConfig) obj).name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
