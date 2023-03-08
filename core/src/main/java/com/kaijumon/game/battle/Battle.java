package com.kaijumon.game.battle;

import com.badlogic.gdx.math.MathUtils;
import com.kaijumon.game.model.entities.Attack;
import com.kaijumon.game.model.entities.Kaijumon;

/**
 * Represents a battle between a trainer and another trainer.
 */
public class Battle {

    public enum BATTLE_STATE {
        READY_TO_PROGRESS,
        SELECT_NEW_POKEMON,
        FLEE,
        WIN,
        LOSE;
    }

    private BATTLE_STATE state;
    private BATTLE_PARTY activeParty;

    private Trainer[] trainers;
    private Kaijumon[] kaijumons;

    public Battle(Trainer player, Trainer opponent) {
        this.trainers = new Trainer[] { player, opponent };
        this.kaijumons = new Kaijumon[] { player.getKaijumon(0), opponent.getKaijumon(0) };

        this.state = BATTLE_STATE.READY_TO_PROGRESS;
    }

    /**
     * Progress the battle one turn. One turn consists of both players performing an action.
     *
     * @param playerAction the player's action.
     * @param playerIndex an index related to the player's action.
     * @param opponentAction the opponent's action.
     * @param opponentIndex an index related to the opponent's action.
     */
    public void progress(Action playerAction, int playerIndex, Action opponentAction, int opponentIndex) {
        if (state != BATTLE_STATE.READY_TO_PROGRESS)
            return;

        if (goesFirst(kaijumons[0], kaijumons[1]))
            activeParty = BATTLE_PARTY.PLAYER;
        else
            activeParty = BATTLE_PARTY.OPPONENT;

        performAction(playerAction, playerIndex);
        if (state != BATTLE_STATE.READY_TO_PROGRESS)
            performAction(opponentAction, opponentIndex);

        performAction(opponentAction, opponentIndex);
        if (state != BATTLE_STATE.READY_TO_PROGRESS)
            performAction(playerAction, playerIndex);

    }

    private void performAction(Kaijumon kaijumon, Action action, int index) {
        switch (action) {
            case Action.FIGHT:
                if (isFainted(kaijumon)){
                    chooseNewKaijumon();
                }
                performAttack(index);
                break;
            case Action.KAIJUMON:
                // todo
                break;
            case Action.BAG:
                useItem(index);
                break;
            case Action.FLEE:
                attemptRun();
        }
    }

    /**
     * Sends out a new Pokemon, in the case that the old one fainted.
     * This will NOT take up a turn.
     * @param Kaijumon	Kaijumon the trainer is sending in
     */
    public void chooseNewKaijumon(int kaijumon) {
        trainers[activeParty].getKaijumon(kaijumon);
        this.player = kaijumon;
        this.state = STATE.READY_TO_PROGRESS;
    }

    /**
     * Attempts to run away
     */
    private void attemptRun() {
        this.state = STATE.FLEE;
    }

    /**
     * Play through a turn. A turn consists of both players making a move.
     *
     * @param playerAttackNum the index of the player's attack in the move list.
     * @param opponentAttackNum the index of the opponent's attack in the move list.
     */
    public void playTurn(int playerAttackNum, int opponentAttackNum) {
        Attack playerAttack = player.getAttacksList().get(playerAttackNum);
        Attack opponentAttack = opponent.getAttacksList().get(opponentAttackNum);

        if (goesFirst(player, opponent)) {
            performAttack(playerAttack, opponent);
        } else {
            performAttack(opponentAttack, player);
        }

        Kaijumon kaijumonUser = null;
        Kaijumon kaijumonTarget = null;
        if (user == BATTLE_PARTY.PLAYER) {
            kaijumonUser = player;
            kaijumonTarget = opponent;
        } else if (user == BATTLE_PARTY.OPPONENT) {
            kaijumonUser = opponent;
            kaijumonTarget = player;
        }

        if (action == Action.FIGHT) {
            mechanics.attack(kaijumonUser, kaijumonTarget);
        } else if (action == Action.KAIJUMON) {
            mechanics.switchPokemon(kaijumonUser, kaijumonTarget);
        } else if (action == Action.BAG) {
            mechanics.useItem(kaijumonUser, kaijumonTarget);
        } else if (action == Action.FLEE) {
            mechanics.run(kaijumonUser, kaijumonTarget);
        }

        if (!player.isAlive()) {
            boolean anyoneAlive = false;
            for (int i = 0; i < getPlayerTrainer().getTeamSize(); i++) {
                if (getPlayerTrainer().getPokemon(i).isAlive()) {
                    anyoneAlive = true;
                    break;
                }
            }
            if (anyoneAlive) {
                this.state = STATE.SELECT_NEW_POKEMON;
            } else {
                this.state = STATE.LOSE;
            }
        } else if (!opponent.isAlive()) {
            this.state = STATE.WIN;
        }
    }

    private void performAttack(Attack attack, Kaijumon defending) {
        int damage = calculateDamage(attack, defending);
        defending.takeDamage(damage);
    }

    private boolean goesFirst(Kaijumon player, Kaijumon opponent) {
        if (player.getStats().speed > opponent.getStats().speed) {
            return true;
        } else if (opponent.getStats().speed > player.getStats().speed) {
            return false;
        } else {
            return MathUtils.randomBoolean();
        }
    }

    private int calculateDamage(Attack attack, Kaijumon defending){
        if (defending.getStats().defense <= 0)
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

    private boolean isFainted(Kaijumon kaijumon){
          return kaijumon.getStats().hp <= 0;
    }

    public Kaijumon getPlayerPokemon() {
        return player;
    }

    public Kaijumon getOpponentPokemon() {
        return opponent;
    }

    public Trainer getPlayerTrainer() {
        return playerTrainer;
    }

    public Trainer getOpponentTrainer() {
        return opponentTrainer;
    }

    public STATE getState() {
        return state;
    }
}
