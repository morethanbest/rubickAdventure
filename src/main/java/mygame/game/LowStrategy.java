package mygame.game;

import mygame.character.HeroFactory;
import mygame.equipment.Armour;
import mygame.equipment.EquipmentFactory;
import mygame.equipment.HeroSkill;
import mygame.equipment.Weapon;

import java.util.Random;

public class LowStrategy implements DropItemStrategy {

    private Random random = new Random();

    @Override
    public HeroSkill getSkillByLevel(int eLevel) {
        HeroFactory factory = HeroFactory.getInstance();
        double a = 1 / (double)(eLevel * eLevel) * random.nextDouble();
        double basic = 0.01;
        for (int i = HeroFactory.MAX_LEVEL; i > 0; i--) {
            if (a < basic){
                System.out.println("You've got a new skill level " + i);
                return factory.getHeroSkillByLevel(i);
            }
            basic += 1 / (double)(i * (i + 1));
        }
        return null;
    }

    @Override
    public Weapon getWeaponByLevel(int eLevel) {
        EquipmentFactory factory = EquipmentFactory.getInstance();
        double a = 1 / (double)(eLevel * eLevel) * random.nextDouble();
        double basic = 0.01;
        for (int i = EquipmentFactory.MAX_WEAPON_LEVEL; i > 0; i--) {
            if (a < basic){
                System.out.println("You've got a new weapon level " + i);
                return factory.getWeaponByLevel(i);
            }
            basic += 1 / (double)(i * (i + 1));
        }
        return null;
    }

    @Override
    public Armour getArmourByLevel(int eLevel) {
        EquipmentFactory factory = EquipmentFactory.getInstance();
        double a = 1 / (double)(eLevel * eLevel) * random.nextDouble();
        double basic = 0.01;
        for (int i = EquipmentFactory.MAX_ARMOUR_LEVEL; i > 0; i--) {
            if (a < basic){
                System.out.println("You've got a new armour level " + i);
                return factory.getArmourByLevel(i);
            }
            basic += 1 / (double)(i * (i + 1));
        }
        return null;
    }
}
