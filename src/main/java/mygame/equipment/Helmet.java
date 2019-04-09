package mygame.equipment;

import mygame.type.ArmourType;

public class Helmet extends Armour{

    @Override
    public String toString() {
        return "Helmet" + super.toString();
    }

    @Override
    public ArmourType getArmourType() {
        return ArmourType.helmet;
    }
}
