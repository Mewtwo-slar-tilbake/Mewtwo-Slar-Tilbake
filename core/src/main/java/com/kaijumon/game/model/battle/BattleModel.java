package com.kaijumon.game.model.battle;

import com.badlogic.gdx.math.MathUtils;
import com.kaijumon.game.model.IBattleModel;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.battle.event.HpChangeEvent;
import com.kaijumon.game.model.battle.event.KaijumonChangeEvent;
import com.kaijumon.game.model.battle.event.TextEvent;
import com.kaijumon.game.model.entities.Attack;
import com.kaijumon.game.model.entities.ITrainer;
import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.model.entities.Trainer;
import com.kaijumon.game.model.items.Item;
import com.kaijumon.game.model.items.KaijuBall;

import java.util.Queue;

/**
 * Represents a battle between a trainer and another trainer.
 * Upon starting the battle, it has the state 'Ready to progress'.
 * After one of the sides have attacked, the state is set to 'In progress'.
 *
 * This continues until one side wins.
 */
public class BattleModel implements IBattleModel {

    private BattleState state;
    private BattleParty activeParty;

    private ITrainer playerTrainer;
    private ITrainer opponentTrainer;

    private Kaijumon playerKaijumon;
    private Kaijumon opponentKaijumon;
    private boolean playerCapturedKaijumon = false;

    private Queue<Event> eventQueue;

    public BattleModel(ITrainer player, ITrainer opponentTrainer, Queue<Event> eventQueue) {
        this.playerTrainer = player;
        this.opponentTrainer = opponentTrainer;
        this.eventQueue = eventQueue;
        if (player.getCrewSize() == 0 || opponentTrainer.getCrewSize() == 0) {
            throw new IllegalArgumentException("Trainers must have at least one Kaijumon in their crew.");
        }

        this.playerKaijumon = player.getKaijumon(getAliveKaijumonIndex(player));
        this.opponentKaijumon = opponentTrainer.getKaijumon(0);
        this.state = BattleState.READY_TO_PROGRESS;

    }


    @Override
    public ITrainer getPlayerTrainer(){
        return playerTrainer;
    }

    @Override
    public ITrainer getOpponentTrainer(){
        return opponentTrainer;
    }



    @Override
    public Kaijumon getPlayerKaijumon() {
        return playerKaijumon;
    }


    @Override
    public Kaijumon getOpponentKaijumon() {
        return opponentKaijumon;
    }


    @Override
    public BattleState getState() {
        return state;
    }




    @Override
    public BattleState progress(Action playerAction, int playerSelection, Action opponentAction, int opponentSelection) {

        if (goesFirst(playerKaijumon, opponentKaijumon)){
            activeParty = BattleParty.PLAYER;
            performAction(playerKaijumon, opponentKaijumon, playerAction, playerSelection);

            if (!opponentKaijumon.isFainted() || !playerCapturedKaijumon){
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

        if (opponentKaijumon.isFainted() || playerCapturedKaijumon) {
            state = BattleState.WIN;
            return state;
        }

        if (playerKaijumon.isFainted()){

            if (getAliveKaijumon(playerTrainer)){
                state = BattleState.SELECT_NEW_KAIJUMON;
                eventQueue.add(new KaijumonChangeEvent(BattleParty.PLAYER, playerKaijumon));
                return state;
            }
            state = BattleState.LOSS;
            return state;
        }
        return state;

    }

    /**
     * Determines which player goes first based on their respective Kaijumon's speed stats.
     * @param player The Kaijumon of the player.
     * @param opponent The Kaijumon of the opponent.
     * @return true if the player goes first, false if the opponent goes first.
     */
    public boolean goesFirst(Kaijumon player, Kaijumon opponent) {
        if (player.getStats().speed > opponent.getStats().speed) {
            return true;
        } else if (opponent.getStats().speed > player.getStats().speed) {
            return false;
        } else {
            return MathUtils.randomBoolean();
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
                useItem(active, passive, index);
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

        eventQueue.add(new HpChangeEvent(getOpposite(activeParty), defending.getStats().hp));
    }


    private void changeKaijumon(Kaijumon previous, int index) {
        // Determine whether the player or the opponent changed their Kaijumon.
        boolean playerChanged = previous.equals(playerKaijumon);

        ITrainer activeTrainer = activeParty == BattleParty.PLAYER
                ? playerTrainer
                : opponentTrainer;

        Kaijumon selected = activeTrainer.getKaijumon(index);
        if (selected.isFainted())
            throw new IllegalArgumentException("Cannot select a Kaijumon which has fainted.");

        if (playerChanged){
            playerKaijumon = selected;
            eventQueue.add(new TextEvent("You sent out " + selected.getName() + "!"));
        }
        else
            opponentKaijumon = selected;

        eventQueue.add(new KaijumonChangeEvent(activeParty, selected));
    }
    private boolean getAliveKaijumon(ITrainer trainer){
        boolean stillAlive = false;
        for (int i = 0; i < trainer.getCrewSize(); i++){
            if (!trainer.getKaijumon(i).isFainted()){
                playerKaijumon = trainer.getKaijumon(i);
                stillAlive = true;
            }
        }
        return stillAlive;
    }
    private int getAliveKaijumonIndex(ITrainer trainer) {
        for (int i = 0; i < trainer.getCrewSize(); i++) {
            if (!trainer.getKaijumon(i).isFainted()) {
                return i;
            }
        }
        return 0;
    }

    private void useItem(Kaijumon active, Kaijumon passive, int index) {
        Item item = playerTrainer.takeItem(index);
        if (item instanceof KaijuBall){
            //TODO maybe add a percentage chance of capture success here.
            KaijuBall ball = (KaijuBall) item;
            ball.capture(passive, playerTrainer);
            playerCapturedKaijumon = true;
            eventQueue.add(new TextEvent("You captured " + passive.getName() + "!"));
        } else {
            item.use(active);
        }
    }

    private void flee() {
        eventQueue.add(new TextEvent("You fled from the battle!"));
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
