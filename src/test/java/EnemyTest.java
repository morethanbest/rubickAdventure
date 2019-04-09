import com.alibaba.fastjson.JSONObject;
import mygame.buff.Buff;
import mygame.buff.DamageBuff;
import mygame.chaconfig.EnemyConfig;
import mygame.chaconfig.HeroConfig;
import mygame.character.Enemy;
import mygame.character.EnemyFactory;
import mygame.character.HeroFactory;
import mygame.type.DamageType;
import mygame.util.YamlReader;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class EnemyTest {

    EnemyFactory factory;
    @Before
    public void initFactory(){
        YamlReader<List<EnemyConfig>> reader = new YamlReader<>();
        List<EnemyConfig> config = reader.readConfig("character/enemy");
        factory = new EnemyFactory(config);
    }

    @Test
    public void testCreateEnemy(){
        Enemy enemy = factory.createEnemyByLevel(5);
        Buff buff = new DamageBuff("Test buff", 3, 50, DamageType.atk);
        buff.takeEffect(enemy);
        buff.takeEffect(enemy);
        buff.takeEffect(enemy);
        System.out.println(JSONObject.toJSON(enemy));
    }
}
