package mygame.type;

public enum BuffType {

    damage("damage"), healing("healing"), weakness("weakness"), enhancement("enhancement");

    private String name;

    BuffType(String name) {
        this.name = name;
    }
}
