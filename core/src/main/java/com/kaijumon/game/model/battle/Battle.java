package com.kaijumon.game.model.battle;

import com.kaijumon.game.model.entities.Attack;
import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.model.entities.Player;

public class Battle implements IBattle {

    private Player p1;
    private Player p2;

    private Kaijumon playerKaijumon;
    private Kaijumon opponentKaijumon;

    public Battle(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;

        this.playerKaijumon = p1.getKaijumonBag().get(0);
        this.opponentKaijumon = p2.getKaijumonBag().get(0);
    }

    @Override
    public void startBattle() {

    }

    @Override
    public void attackOpponent() {

    }

    @Override
    public Boolean isBattleOver() {
        return null;
    }

    @Override
    public void flee() {

    }

    @Override
    public void swapKaijumon() {

    }

    @Override
    public void useItem() {
    }

    @Override
    public void performMoves(Attack first, Attack second) {
        if (p1.getKaijumonBag().get(0).getSpeed() > p2.getKaijumonBag().get(0).getSpeed()){
            opponentKaijumon.takeDamage(calculateDamage(first, opponentKaijumon));
            if (opponentKaijumon.getHP() != 0){
                playerKaijumon.takeDamage(calculateDamage(second, playerKaijumon));
            }
        }
        else {
            playerKaijumon.takeDamage(calculateDamage(second, playerKaijumon));
            if (playerKaijumon.getHP() != 0){
                opponentKaijumon.takeDamage(calculateDamage(first, opponentKaijumon));
            }
        }
    }

    private int calculateDamage(Attack attack, Kaijumon defending){
        if (defending.getDefense() <= 0)
            return (int)(attack.getDamage()*getEffectiveness(attack, defending));
        return (int)((attack.getDamage() / defending.getDefense()) * getEffectiveness(attack, defending));
    }

    private double getEffectiveness(Attack attack, Kaijumon defending){
        if (attack.getType().isSuperEffectiveAgainst(defending.getType()))
            return 2;
        if (attack.getType().isNotVeryEffectiveAgainst(defending.getType()))
            return 0.5;
        if (attack.getType().hasNoEffectOn(defending.getType()))
            return 0;
        return 1;
    }

}
