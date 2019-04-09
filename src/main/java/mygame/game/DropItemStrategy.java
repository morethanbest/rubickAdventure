package mygame.game;

import mygame.equipment.Armour;
import mygame.equipment.HeroSkill;
import mygame.equipment.Weapon;

public interface DropItemStrategy {
    HeroSkill getSkillByLevel(int eLevel);
    Weapon getWeaponByLevel(int eLevel);
    Armour getArmourByLevel(int eLevel);
}
