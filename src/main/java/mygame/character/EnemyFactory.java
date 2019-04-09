package mygame.character;

import mygame.chaconfig.EnemyConfig;

import java.util.*;

public class EnemyFactory {
    private static EnemyFactory factory;
    
    public final static int MAX_ENEMY_LEVEL = 5;
    private Map<Integer, List<EnemyConfig>> enemyMap;
    private Random random;
    public EnemyFactory(List<EnemyConfig> configList) {
        this.enemyMap = generateEnemyMap(configList);
        random = new Random();
        factory = this;
    }

    public static EnemyFactory getInstance() {
        return factory;
    }

    private Map<Integer, List<EnemyConfig>> generateEnemyMap(List<EnemyConfig> enemyConfigs){
        Map<Integer, List<EnemyConfig>> enemies = new HashMap<>();
        for (int i = 1; i <= MAX_ENEMY_LEVEL; i++) {
            List<EnemyConfig> tmp = new ArrayList<>();
            for (EnemyConfig config : enemyConfigs) {
                if (config.getLevel() == i) {
                    tmp.add(config);
                }
            }
            enemies.put(i, tmp);
        }
        return enemies;
    }

    public Enemy createEnemyByLevel(int level){
        List<EnemyConfig> configs = enemyMap.get(level);
        int randomIndex = random.nextInt(configs.size());
        return new Enemy(configs.get(randomIndex));
    }

}
