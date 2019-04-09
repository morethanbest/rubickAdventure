package mygame.buff;

import mygame.character.SimpleCharacter;

public class HealingBuff extends Buff {

    public HealingBuff(String name, int rounds, double value) {
        super(name, rounds, value);
    }

    @Override
    public String takeEffect(SimpleCharacter character) {
        if (remainedRounds > 0) {
            double before = character.getHpNow();
            character.setHpNow(character.getHpNow() + value);
            remainedRounds--;
            return character.getName() + " Hp " + before + " -> " + character.getHpNow() + " " + name
                    + "治疗" + value;
        }
        return "已失效";
    }
}
