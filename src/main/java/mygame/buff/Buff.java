package mygame.buff;

import mygame.character.SimpleCharacter;

public abstract class Buff {
    protected final String name;
    protected int rounds;
    protected int remainedRounds;
    protected double value;

    protected Buff(String name, int rounds, double value) {
        this.name = name;
        this.rounds = rounds;
        this.remainedRounds = rounds;
        this.value = value;
    }

    public abstract String takeEffect(SimpleCharacter character);

    @Override
    public String toString() {
        String s  = "Name: " + name + ", Rounds remain: " + remainedRounds + ", Value: " + value;
        return s;
    }

    public boolean isOver(){
        return remainedRounds <= 0;
    }
}
