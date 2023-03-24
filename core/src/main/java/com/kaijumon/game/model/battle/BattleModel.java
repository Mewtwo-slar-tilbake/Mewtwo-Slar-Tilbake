package com.kaijumon.game.model.battle;

import com.badlogic.gdx.math.MathUtils;
import com.kaijumon.game.model.Model;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.battle.event.HpChangeEvent;
import com.kaijumon.game.model.battle.event.TextEvent;
import com.kaijumon.game.model.entities.Attack;
import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.model.entities.Player;
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
        this.state = BattleState.READY_TO_PROGRESS;

        //checkTurn(playerKaijumon, opponentKaijumon);
    }

    /**
     * Gets the player
     */
    public Trainer getPlayer(){
        return player;
    }


    /**
     * Get the player's active Kaijumon.
     */
    public Kaijumon getPlayerKaijumon() {
        return playerKaijumon;
    }

    public void FullHPCrew(){
        for (Kaijumon kaijumon : player.getCrew()){
            kaijumon.getStats().hp = 100;
        }
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
    public BattleState progress(Action playerAction, int playerSelection, Action opponentAction, int opponentSelection) {

        if (goesFirst(playerKaijumon, opponentKaijumon)){
            activeParty = BattleParty.PLAYER;
            performAction(playerKaijumon, opponentKaijumon, playerAction, playerSelection);
            if (!opponentKaijumon.isFainted()){
                activeParty = BattleParty.OPPONENT;
                performAction(opponentKaijumon, playerKaijumon, opponentAction, opponentSelection);
            }
        }
        else{
            activeParty = BattleParty.OPPONENT;
            performAction(opponentKaijumon, playerKaijumon, opponentAction, opponentSelection);
            if (!playerKaijumon.isFainted()){
                activeParty = BattleParty.PLAYER;
                performAction(playerKaijumon, opponentKaijumon, playerAction, playerSelection);
            }
        }

        if (opponentKaijumon.isFainted()) {
            state = BattleState.WIN;
            return state;
        }

        if (playerKaijumon.isFainted()){
            state = BattleState.LOSS;
            return state;
        }
        return state;


        /**if (state != state.READY_TO_PROGRESS) {
            return;
        }
        if (goesFirst(playerKaijumon, opponentKaijumon)) {
            playTurn(BattleParty.PLAYER, action, index);
            if (state == state.READY_TO_PROGRESS) {
                playTurn(BattleParty.OPPONENT, action, 0);

            }
        }
        else {
            playTurn(BattleParty.OPPONENT, action, 0);
            if (state == state.READY_TO_PROGRESS) {
                playTurn(BattleParty.PLAYER, action, index);
            }
        }**/

        /**Kaijumon attacking, defending;

        activeParty = playerKaijumon.getStats().speed <= opponentKaijumon.getStats().speed
                ? BattleParty.PLAYER
                : BattleParty.OPPONENT;

        if (activeParty == BattleParty.PLAYER){
            attacking = playerKaijumon;
            defending = opponentKaijumon;
        }

        else {
            attacking = opponentKaijumon;
            defending = playerKaijumon;
        }**/

        /**if (state == BattleState.READY_TO_PROGRESS) {
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

        performAction(attacking, defending, action, index);**/



        /**if (opponentKaijumon.isFainted()) {
            state = BattleState.WIN;
            return state;
        }
        if (playerKaijumon.isFainted())
            state = BattleState.LOSS;
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
        return state;**/
    }

    /**private void playTurn(BattleParty user, Action action, int input) {

       BattleParty.getOpposite(user);

        Kaijumon pokeUser = null;
        Kaijumon pokeTarget = null;
        if (user == BattleParty.PLAYER) {
            pokeUser = playerKaijumon;
            pokeTarget = opponentKaijumon;
        } else if (user == BattleParty.OPPONENT) {
            pokeUser = opponentKaijumon;
            pokeTarget = playerKaijumon;
        }

        performAction(pokeUser, pokeTarget, action, input);


        if (playerKaijumon.isFainted()) {
            boolean anyoneAlive = false;
            for (int i = 0; i < player.getCrewSize(); i++) {
                if (!player.getKaijumon(i).isFainted()) {
                    anyoneAlive = true;
                    break;
                }
            }
            if (!anyoneAlive) {
                this.state = BattleState.LOSS;
            }
        } else if (opponentKaijumon.isFainted()) {
            this.state = BattleState.WIN;
        }
    }**/

    public boolean goesFirst(Kaijumon player, Kaijumon opponent) {
        if (player.getStats().speed > opponent.getStats().speed) {
            return true;
        } else if (opponent.getStats().speed > player.getStats().speed) {
            return false;
        } else {
            return MathUtils.randomBoolean();
        }

        /**
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
        }**/
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

        Trainer activeTrainer = activeParty == BattleParty.PLAYER
                ? player
                : opponent;

        Kaijumon selected = activeTrainer.getKaijumon(index);
        if (selected.isFainted())
            throw new IllegalArgumentException("Cannot select a Kaijumon which has fainted.");

        if (playerChanged){
            playerKaijumon = selected;
        }
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
