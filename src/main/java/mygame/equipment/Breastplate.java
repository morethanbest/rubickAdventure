package mygame.equipment;

import mygame.type.ArmourType;

public class Breastplate extends Armour{

    @Override
    public String toString() {
        return "Breastplate" + super.toString();
    }

    @Override
    public ArmourType getArmourType() {
        return ArmourType.breastplate;
    }
}
