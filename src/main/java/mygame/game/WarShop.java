package mygame.game;

import mygame.equipment.Armour;
import mygame.equipment.EquipmentFactory;
import mygame.equipment.Weapon;
import mygame.item.BagItem;

public class WarShop {

    private int gold = 0;

//    private EquipmentFactory factory = ;

    private void gain(int g){
        gold += g;
    }

    private boolean consume(int g){
        if (gold - g < 0) {
            System.out.println("Insufficient gold.");
            return false;
        } else {
            System.out.println("Consume gold " + g);
            gold -= g;
            return true;
        }
    }

    public boolean enhanceWeapon(Weapon weapon){
        int need = weapon.getLevel() * weapon.getLevel() * (weapon.getEnhanceLevel() + 1);
        if(consume(need)) {
            EquipmentFactory.getInstance().enhanceWeapon(weapon);
            return true;
        }
        return false;
    }

    public boolean enhanceArmour(Armour armour){
        int need = armour.getLevel() * armour.getLevel() * (armour.getEnhanceLevel() + 1);
        if(consume(need)) {
            EquipmentFactory.getInstance().enhanceArmour(armour);
            return true;
        }
        return false;
    }

    public void soldBagItem(BagItem bagItem){
         int g = bagItem.getLevel() * bagItem.getLevel() * (bagItem.getEnhanceLevel() + 1);
        System.out.println("You've got gold " + g);
        gain(g);
    }

    public int getGold() {
        return gold;
    }
}
