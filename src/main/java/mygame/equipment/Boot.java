package mygame.equipment;

import mygame.type.ArmourType;

public class Boot extends Armour {

    @Override
    public String toString() {
        return "Boot" + super.toString();
    }

    @Override
    public ArmourType getArmourType() {
        return ArmourType.boot;
    }
}
