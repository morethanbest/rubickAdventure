import mygame.chaconfig.HeroConfig;
import mygame.character.HeroFactory;
import mygame.equipment.Armour;
import mygame.equipment.EquipmentFactory;
import mygame.equipment.Weapon;
import mygame.game.DropItemStrategy;
import mygame.game.HighStategy;
import mygame.game.WarShop;
import org.junit.Before;
import org.junit.Test;
import mygame.util.YamlReader;

/**
 * Created by xymark.wang on 2017/7/31.
 */
public class YamlTest {

    EquipmentFactory factory;
    HeroFactory factory2;
    @Before
    public void init(){
        YamlReader<HeroConfig> reader = new YamlReader<>();
        HeroConfig config = reader.readConfig("character/wizard");
        factory = new EquipmentFactory(config.getWeapons(), config.getArmours());
        factory2 = new HeroFactory(config);
    }

    @Test
    public void testSayHello() throws Exception {
        YamlReader<HeroConfig> reader = new YamlReader<>();
        HeroConfig config = reader.readConfig("character/wizard");
        System.out.println(config.getSkills().get(1).getBuffs().get(0).getType());
    }

    @Test
    public void testWeaponCreationAndEnhance(){
        Weapon weapon = factory.getWeaponByLevel(2);
        System.out.println(weapon);
        factory.enhanceWeapon(weapon);
        System.out.println(weapon);
        System.out.println(factory.enhanceWeapon(weapon));
        weapon = factory.getWeaponByLevel(3);
        System.out.println(weapon);
        System.out.println(factory.enhanceWeapon(weapon));
        System.out.println(factory.enhanceWeapon(weapon));
    }

    @Test
    public void testArmourCreationAndEnhance(){
        System.out.println(factory.getArmourByLevel(1));
        System.out.println(factory.getArmourByLevel(1));
        System.out.println(factory.getArmourByLevel(1));
        System.out.println(factory.getWeaponByLevel(1));
        System.out.println(factory.getArmourByLevel(1));
    }

    @Test
    public void testStrategy(){
        DropItemStrategy strategy = new HighStategy();
        for (int i = 0; i < 5; i++) {
            strategy.getWeaponByLevel(6);
        }
    }

    @Test
    public void testWarShop(){
        WarShop warShop = new WarShop();
        Weapon weapon = factory.getWeaponByLevel(3);
        factory.enhanceWeapon(weapon);
        factory.enhanceWeapon(weapon);
        factory.enhanceWeapon(weapon);
        factory.enhanceWeapon(weapon);
        warShop.soldBagItem(weapon);
        weapon = factory.getWeaponByLevel(2);
        warShop.enhanceWeapon(weapon);
        Armour armour = factory.getArmourByLevel(1);
        warShop.enhanceArmour(armour);
        warShop.soldBagItem(armour);
    }
}