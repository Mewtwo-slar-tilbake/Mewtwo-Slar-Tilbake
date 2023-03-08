package com.kaijumon.game.battle;

import com.badlogic.gdx.math.MathUtils;
import com.kaijumon.game.model.entities.Attack;
import com.kaijumon.game.model.entities.Kaijumon;

/**
 * Represents a battle between a trainer and another trainer.
 *
 * TODO: Decouple convoluted logic to determine which side performed an action.
 */
public class Battle {

    private BattleState state;

    private Trainer player;
    private Trainer opponent;

    private Kaijumon playerKaijumon;
    private Kaijumon opponentKaijumon;

    public Battle(Trainer player, Trainer opponent) {
        this.player = player;
        this.opponent = opponent;

        this.playerKaijumon = player.getKaijumon(0);
        this.opponentKaijumon = opponent.getKaijumon(0);

        this.state = BattleState.ACTIVE;
    }

    /**
     * Get the player trainer.
     */
    public Trainer getPlayerTrainer() {
        return player;
    }

    /**
     * Get the opponent trainer.
     */
    public Trainer getOpponentTrainer() {
        return opponent;
    }

    /**
     * Get the player's active Kaijumon.
     */
    public Kaijumon getPlayerKaijumon() {
        return playerKaijumon;
    }

    /**
     * Get the opponent's active Kaijumon.
     */
    public Kaijumon getOpponentKaijumon() {
        return opponentKaijumon;
    }

    /**
     * Get the battle state. Win or loss is relative to the player, not the opponent.
     */
    public BattleState getState() {
        return state;
    }

    /**
     * Progress the battle by one turn. One turn consists of both players performing an action.
     * Updates the battle state if one side wins or flees.
     *
     * @param playerAction the player's action.
     * @param playerIndex an index related to the player's action.
     * @param opponentAction the opponent's action.
     * @param opponentIndex an index related to the opponent's action.
     *
     * @return the battle state after the turn has been made.
     */
    public BattleState progress(Action playerAction, int playerIndex, Action opponentAction, int opponentIndex) {
        if (state != BattleState.ACTIVE)
            return state;

        performAction(playerKaijumon, opponentKaijumon, playerAction, playerIndex);
        // PerformAction updated the state to WIN if the opposing Kaijumon fainted.
        if (opponentKaijumon.isFainted()) {
            state = BattleState.WIN;
            return state;
        }

        performAction(opponentKaijumon, playerKaijumon, opponentAction, opponentIndex);
        if (playerKaijumon.isFainted())
            state = BattleState.LOSS;
        return state;
    }

    private void performAction(Kaijumon active, Kaijumon passive, Action action, int index) {
        switch (action) {
            case FIGHT:
                attack(active, passive, index);
                break;
            case KAIJUMON:
                changeKaijumon(active, index);
                break;
            case BAG:
                useItem(active, index);
                break;
            case FLEE:
                flee();
                break;
        }
    }

    private void attack(Kaijumon attacking, Kaijumon defending, int index) {
        Attack attack = attacking.getAttack(index);

        int damage = calculateDamage(attack, defending);
        defending.takeDamage(damage);
    }

    private void changeKaijumon(Kaijumon previous, int index) {
        // Determine whether the player or the opponent changed their Kaijumon.
        boolean playerChanged = previous.equals(playerKaijumon);

        Trainer activeTrainer = playerChanged
                ? player
                : opponent;

        Kaijumon selected = activeTrainer.getKaijumon(index);
        if (selected.isFainted())
            throw new IllegalArgumentException("Cannot select a Kaijumon which has fainted.");

        if (playerChanged)
            playerKaijumon = selected;
        else
            opponentKaijumon = selected;
    }

    private void useItem(Kaijumon affected, int index) {
        // TODO: Add bag to trainer class + add items to use.
    }

    private void flee() {
        this.state = BattleState.FLEE;
    }

    private int calculateDamage(Attack attack, Kaijumon defending) {
        int damage = attack.getPower();                      // Factor in attack power.
        damage /= Math.max(defending.getStats().defense, 1); // Factor in defense.
        damage *= getEffectiveness(attack, defending);       // Factor in type effectiveness.

        return damage;
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
