package mygame.item;

import mygame.equipment.Armour;

public class ArmourSlot<T extends Armour> implements Slot{

    private T armour;

    public void setArmour(T t) {
        this.armour = t;
    }

    public T unloadArmour(){
        T t = armour;
        armour = null;
        return t;
    }

    public T getArmour() {
        return armour;
    }

    @Override
    public BagItem getBagItem() {
        return armour;
    }
}
