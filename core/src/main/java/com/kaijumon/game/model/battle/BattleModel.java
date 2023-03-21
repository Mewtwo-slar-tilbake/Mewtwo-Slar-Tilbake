package com.kaijumon.game.model.battle;

import com.badlogic.gdx.math.MathUtils;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.battle.event.HpChangeEvent;
import com.kaijumon.game.model.battle.event.TextEvent;
import com.kaijumon.game.model.entities.Attack;
import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.model.entities.Trainer;

import java.util.Queue;

/**
 * Represents a battle between a trainer and another trainer.
 * Upon starting the battle, it has the state 'Ready to progress'.
 * After one of the sides have attacked, the state is set to 'In progress'.
 *
 * This continues until one side wins.
 */
public class BattleModel {

    private BattleState state;
    private BattleParty activeParty;

    private Trainer player;
    private Trainer opponent;

    private Kaijumon playerKaijumon;
    private Kaijumon opponentKaijumon;

    private Queue<Event> eventQueue;

    public BattleModel(Trainer player, Trainer opponent, Queue<Event> eventQueue) {
        this.player = player;
        this.opponent = opponent;
        this.eventQueue = eventQueue;

        this.playerKaijumon = player.getKaijumon(0);
        this.opponentKaijumon = opponent.getKaijumon(0);

        checkTurn(playerKaijumon, opponentKaijumon);
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
     * Progress the battle by performing the given action.
     * Updates the battle state if one side wins or flees.
     *
     * @param action the action to perform.
     * @param index an index related to the given action.
     *
     * @return the battle state after the turn has been made.
     */
    public BattleState progress(Action action, int index) {
        Kaijumon attacking, defending;

        if (state == BattleState.READY_TO_PROGRESS) {
            activeParty = BattleParty.OPPONENT;
            attacking = opponentKaijumon;
            defending = playerKaijumon;
        }
        else if (state == BattleState.IN_PROGRESS) {
            activeParty = BattleParty.PLAYER;
            attacking = playerKaijumon;
            defending = opponentKaijumon;
        }
        else {
            return state;
        }

        performAction(attacking, defending, action, index);
        if (state == BattleState.READY_TO_PROGRESS){
            state = BattleState.IN_PROGRESS;
        }
        else if (state == BattleState.IN_PROGRESS){
            state = BattleState.READY_TO_PROGRESS;
        }
        // PerformAction updated the state to WIN if the opposing Kaijumon fainted.
        if (opponentKaijumon.isFainted()) {
            state = BattleState.WIN;
            return state;
        }

        if (playerKaijumon.isFainted())
            state = BattleState.LOSS;
        return state;
    }

    private void checkTurn(Kaijumon k1, Kaijumon k2){
        if (k1.getStats().speed > k2.getStats().speed){
            state = BattleState.IN_PROGRESS;
        }
        else if (k1.getStats().speed < k2.getStats().speed){
            state = BattleState.READY_TO_PROGRESS;
        }
        else {
            if (MathUtils.randomBoolean()){
                state = BattleState.READY_TO_PROGRESS;
            }
            else {
                state = BattleState.IN_PROGRESS;
            }
        }
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
        eventQueue.add(new TextEvent("Oh no! The opposing Kaijumon was damaged!"));
        eventQueue.add(new HpChangeEvent(getOpposite(activeParty), defending.getStats().hp));
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

    private BattleParty getOpposite(BattleParty party) {
        return party == BattleParty.PLAYER
                ? BattleParty.OPPONENT
                : BattleParty.PLAYER;
    }

}