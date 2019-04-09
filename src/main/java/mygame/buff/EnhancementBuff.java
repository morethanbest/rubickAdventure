package mygame.buff;

import mygame.character.SimpleCharacter;

import java.lang.reflect.Field;

public class EnhancementBuff extends Buff {

    private Field field;

    public EnhancementBuff(String name, int rounds, double value, String feature) {
        super(name, rounds, value);
        Class clazz = SimpleCharacter.class;
        try {
            field = clazz.getDeclaredField(feature);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String takeEffect(SimpleCharacter character) {
        String res = "已失效";
        double d;
        try {
            d = field.getDouble(character);
            if (remainedRounds == rounds) {
                field.setDouble(character, d + value);
            }
            remainedRounds--;
            res = name + "增强状态生效，" + field.getName() + "增强" + value;
            if (remainedRounds == 0) {
                field.setDouble(character, d - value);
                remainedRounds--;
                res = name + "增强状态失效，" + field.getName() + "降低" + value;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }
}
