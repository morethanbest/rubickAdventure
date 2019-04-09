package mygame.item;

import mygame.equipment.Weapon;

public class WeaponSlot implements Slot {

    private Weapon weapon;

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon unloadWeapon(){
        Weapon w = weapon;
        weapon = null;
        return w;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public BagItem getBagItem() {
        return weapon;
    }
}
