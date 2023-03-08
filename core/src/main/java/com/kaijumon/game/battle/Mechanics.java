package com.kaijumon.game.battle;

import com.badlogic.gdx.math.MathUtils;
import com.kaijumon.game.model.entities.Attack;
import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.model.entities.Stats;

public class Mechanics {

    public boolean goesFirst(Kaijumon player, Kaijumon opponent) {
        if (player.getStats().speed > opponent.getStats().speed) {
            return true;
        } else if (opponent.getStats().speed > player.getStats().speed) {
            return false;
        } else {
            return MathUtils.randomBoolean();
        }
    }

    public void attack(Kaijumon attacker, Kaijumon defender, Attack attack) {
        int damage = calculateDamage(attack, defender);
        defender.takeDamage(damage);
    }

    private int calculateDamage(Attack attack, Kaijumon defending){
        if (defending.getStats().hp <= 0)
            return (int)(attack.getPower()*getEffectiveness(attack, defending));
        return (int)((attack.getPower() / defending.getStats().defense) * getEffectiveness(attack, defending));
    }

    private double getEffectiveness(Attack attack, Kaijumon defending){
        if (attack.getElement().isSuperEffectiveAgainst(defending.getElement()))
            return 2;
        if (attack.getElement().isNotVeryEffectiveAgainst(defending.getElement()))
            return 0.5;
        if (attack.getElement().hasNoEffectOn(defending.getElement()))
            return 0;
        return 1;
    }
}
