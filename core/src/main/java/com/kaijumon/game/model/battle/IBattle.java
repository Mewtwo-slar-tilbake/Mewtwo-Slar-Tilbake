package com.kaijumon.game.model.battle;

import com.kaijumon.game.model.entities.Attack;

public interface IBattle {


    /**
     * Start the battle. The player with the highest speed will go first.
     */
    public void startBattle();

    /**
     * Attacks the opponent. The damage dealt on the opponent is determined by the player's attack stat,
     * the opponent's defense stat and the types of both the attacker and defender.
     */

    public void attackOpponent();

    /**
     * Checks if the battle is over.
     */
    public Boolean isBattleOver();

    /**
     * Flee from the battle. The player will have a certain % chance of fleeing successfully.
     */
    public void flee();

    /**
     * Swap the player's kaijumon with another kaijumon in the player's bag.
     */
    public void swapKaijumon();

    /**
     * Use an item on the player's kaijumon.
     */
    public void useItem();


    /**
     * goes to the next step in the battle
     */
    public void performMoves(Attack first, Attack second);

}
