import mygame.chaconfig.EnemyConfig;
import mygame.chaconfig.HeroConfig;
import mygame.character.Enemy;
import mygame.character.EnemyFactory;
import mygame.character.Hero;
import mygame.character.HeroFactory;
import mygame.equipment.Armour;
import mygame.equipment.EquipmentFactory;
import mygame.equipment.HeroSkill;
import mygame.equipment.Weapon;
import mygame.game.BattleField;
import mygame.util.YamlReader;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BattleTest {

    private HeroFactory heroFactory;

    EnemyFactory enemyFactory;

    EquipmentFactory eFactory;

    @Before
    public void initFactory(){
        YamlReader<List<EnemyConfig>> reader = new YamlReader<>();
        List<EnemyConfig> config = reader.readConfig("character/enemy");
        enemyFactory = new EnemyFactory(config);
        YamlReader<HeroConfig> reader2 = new YamlReader<>();
        HeroConfig config2 = reader2.readConfig("character/wizard");
        heroFactory = new HeroFactory(config2);
        eFactory = new EquipmentFactory(config2.getWeapons(), config2.getArmours());

    }

    @Test
    public void testFight(){
//
//        BattleField battleField = new BattleField();
//        battleField.initHero("wizard");
//        HeroSkill heroSkill = heroFactory.getHeroSkillByLevel(1);
//        HeroSkill heroSkill2 = heroFactory.getHeroSkillByLevel(2);
//        heroFactory.levelUp(heroSkill);
//        heroFactory.levelUp(heroSkill2);
//        Weapon weapon = eFactory.getWeaponByLevel(1);
//        Armour armour = eFactory.getArmourByLevel(2);
//        Armour armour1 = eFactory.getArmourByLevel(2);
//        Armour armour2 = eFactory.getArmourByLevel(2);
//        battleField.newItem(heroSkill);
//        battleField.newItem(heroSkill2);
//        battleField.equip(0);
//        battleField.equip(1);
//        battleField.newItem(weapon);
//        battleField.newItem(armour);
//        battleField.newItem(armour1);
//        battleField.newItem(armour2);
//        battleField.equip(0);
//        battleField.equip(1);
//        battleField.equip(2);
//        battleField.equip(3);
//        battleField.fight();
//        battleField.nextTurn();
//        System.out.println("====================================");
//        battleField.fight();
//        System.out.println(battleField.getHeroBagInfo());
//        System.out.println(battleField.getBagItemInfoByIndex(1));
    }
}
