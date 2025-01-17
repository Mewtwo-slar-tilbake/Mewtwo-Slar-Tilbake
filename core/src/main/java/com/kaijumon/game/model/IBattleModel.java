package com.kaijumon.game.model;

import com.kaijumon.game.model.battle.Action;
import com.kaijumon.game.model.battle.BattleState;
import com.kaijumon.game.model.entities.ITrainer;
import com.kaijumon.game.model.entities.Kaijumon;

public interface IBattleModel {

    /**
     * Get the battle state. Win or loss is relative to the player, not the opponent.
     */
    BattleState getState();

    /**
     * Progress the battle by performing the given action.
     * Updates the battle state if one side wins or flees.
     *
     * @param playerAction the action to perform.
     * @param playerSelection an index related to the given action.
     * @param opponentAction the action to perform.
     * @param opponentSelection an index related to the given action.
     * @return the battle state after the turn has been made.
     */
    BattleState progress(Action playerAction, int playerSelection, Action opponentAction, int opponentSelection);

    /**
     * Get the player's active Kaijumon.
     */
    Kaijumon getPlayerKaijumon();

    /**
     * Gets the player
     */
    ITrainer getPlayerTrainer();

    /**
     * Get the opponent.
     */
    ITrainer getOpponentTrainer();

    /**
     * Get the opponent's active Kaijumon.
     */
    Kaijumon getOpponentKaijumon();
}
