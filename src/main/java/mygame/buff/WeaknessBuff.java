package mygame.buff;

import mygame.character.SimpleCharacter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class WeaknessBuff extends Buff {

    private Field field;
//    private Field fieldBasic;

    public WeaknessBuff(String name, int rounds, double value, String feature) {
        super(name, rounds, value);
        Class clazz = SimpleCharacter.class;
        try {
            field = clazz.getDeclaredField(feature);
            field.setAccessible(true);
//            fieldBasic = clazz.getDeclaredField(feature + "Basic");
//            fieldBasic.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String takeEffect(SimpleCharacter character) {
        double d;
        String res = "已失效";
        try {
            d = field.getDouble(character);
            if (remainedRounds == rounds) {
                field.setDouble(character, d - value);
            }
            remainedRounds--;
            res = name + "虚弱状态生效，" + field.getName() + "降低" + value;
            if (remainedRounds == 0) {
                field.setDouble(character, d + value);
                remainedRounds--;
                res = name + "虚弱状态失效，" + field.getName() + "增强" + value;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }

}
