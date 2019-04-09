package mygame.buff;

import mygame.character.SimpleCharacter;
import mygame.type.DamageType;

public class DamageBuff extends Buff
{
    private DamageType damageType;
    public DamageBuff(String name, int rounds, double value, DamageType damageType) {
        super(name, rounds, value);
        this.damageType = damageType;
    }

    @Override
    public String takeEffect(SimpleCharacter character) {
        if (remainedRounds > 0) {
            double damage = 0;
            switch (damageType){
                case atk:
                    damage = value * (1 - character.getAtkDef() / 100);
                    break;
                case mgk:
                    damage = value * (1 - character.getMgkDef() / 100);
                    break;
                case pure:
                    damage = value;
            }
            damage = damage > 0 ? damage : 0;
            double before = character.getHpNow();
            character.setHpNow(character.getHpNow() - damage);
            remainedRounds--;
            return character.getName() + " Hp " + before + " -> " + character.getHpNow() + " " + name
                    + "造成" + damageType + "类型伤害 " + damage;
        }
        return "已失效";
    }
}
