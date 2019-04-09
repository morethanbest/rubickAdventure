package mygame.type;

public enum  StatusType {
    normal("normal"), stun("stun"), silence("silence"), disarm("disarm");

    private String name;

    StatusType(String name) {
        this.name = name;
    }
}
