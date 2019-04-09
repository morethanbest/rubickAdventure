package mygame.type;

public enum  BagItemType {

    skill("skill", 1), armour("armour", 3), weapon("weapon", 2);

    private String name;
    private int index;

    BagItemType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
