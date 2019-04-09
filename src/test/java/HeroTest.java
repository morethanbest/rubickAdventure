import com.alibaba.fastjson.JSONObject;
import mygame.buff.Buff;
import mygame.buff.DamageBuff;
import mygame.chaconfig.HeroConfig;
import mygame.character.Hero;
import mygame.character.HeroFactory;
import mygame.type.DamageType;
import mygame.util.YamlReader;
import org.junit.Before;
import org.junit.Test;

public class HeroTest {

    private HeroFactory heroFactory;

    @Before
    public void initFactory(){
        YamlReader<HeroConfig> reader = new YamlReader<>();
        HeroConfig config = reader.readConfig("character/wizard");
        heroFactory = new HeroFactory(config);
    }
    @Test
    public void testHeroBuff(){
        Hero hero = heroFactory.createHero();
        System.out.println(hero);
        Buff buff = new DamageBuff("Test buff", 3, 20, DamageType.pure);
        buff.takeEffect(hero);
        buff.takeEffect(hero);
        buff.takeEffect(hero);
        System.out.println(hero);
        System.out.println(JSONObject.toJSON(hero));
    }
}
