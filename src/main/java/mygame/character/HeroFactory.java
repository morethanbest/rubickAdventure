package mygame.character;

import mygame.buff.BuffTemplate;
import mygame.chaconfig.BuffConfig;
import mygame.chaconfig.HeroConfig;
import mygame.chaconfig.HeroSkillConfig;
import mygame.chaconfig.WeaponConfig;
import mygame.equipment.HeroSkill;

import java.util.*;

public class HeroFactory {

    private static HeroFactory heroFactory;
    public static final int MAX_LEVEL = 3;
    private HeroConfig heroConfig;
    private Random random;

    private Map<HeroSkill, HeroSkillConfig> heroSkillConfigMap;
    private Map<Integer, List<HeroSkillConfig>> heroSkillLevelMap;

    public HeroFactory(HeroConfig heroConfig) {
        this.heroConfig = heroConfig;
        heroSkillConfigMap = new HashMap<>();
        heroSkillLevelMap = generateHeroSkillMap(heroConfig.getSkills());
        random = new Random();
        heroFactory = this;
    }

    private Map<Integer, List<HeroSkillConfig>> generateHeroSkillMap(List<HeroSkillConfig> heroSkillConfigs){
        Map<Integer, List<HeroSkillConfig>> heroSkills = new HashMap<>();
        for (int i = 1; i <= MAX_LEVEL; i++) {
            List<HeroSkillConfig> tmp = new ArrayList<>();
            for (HeroSkillConfig config : heroSkillConfigs) {
                if (config.getLevel() == i) {
                    tmp.add(config);
                }
            }
            heroSkills.put(i, tmp);
        }
        return heroSkills;
    }

    public static HeroFactory getInstance() {
        return heroFactory;
    }

    public Hero createHero(){
        Hero hero = new Hero(heroConfig);
        return hero;
    }
    public HeroSkill getHeroSkillByLevel(int level){
        List<HeroSkillConfig> configs = heroSkillLevelMap.get(level);
        int randomIndex = random.nextInt(configs.size());
        HeroSkillConfig config = configs.get(randomIndex);
        HeroSkill skill = new HeroSkill(config);
        heroSkillConfigMap.put(skill, config);
        return skill;
    }

    public HeroSkill levelUp(HeroSkill skill){
        HeroSkillConfig config = heroSkillConfigMap.get(skill);
        List<BuffConfig> buffConfigs = config.getBuffs();
        List<BuffTemplate> buffTemplates = skill.getBuffs();
        for (int i = 0; i < buffConfigs.size(); i++) {
            BuffConfig buffConfig = buffConfigs.get(i);
            BuffTemplate buffTemplate = buffTemplates.get(i);
            buffTemplate.setRatio(buffTemplate.getRatio() + buffConfig.getRatioGrow());
            buffTemplate.setValue(buffTemplate.getValue() + buffConfig.getValueGrow());
        }
        System.out.println("Level up!!! " + skill.getName() + " level " + skill.getEnhanceLevel() + " -> level " + (skill.getEnhanceLevel() + 1));
        skill.setEnhanceLevel(skill.getEnhanceLevel() + 1);
        return skill;
    }
}
