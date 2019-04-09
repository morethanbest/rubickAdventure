package mygame.type;

public enum ArmourType {

    helmet("helmet", 0), breastplate("breastplate", 1), boot("boot", 2);

    private String name;
    private int order;

    ArmourType(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
