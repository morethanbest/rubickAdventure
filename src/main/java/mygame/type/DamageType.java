package mygame.type;

public enum DamageType {

    atk("atk"), mgk("mgk"), pure("pure");

    private String name;

    DamageType(String name) {
        this.name = name;
    }
}
