package com.kaijumon.game.model.battle;

/**
 * Represents the state of a Kaijumon battle.
 */
public enum BattleState {

    READY_TO_PROGRESS,
    IN_PROGRESS,
    WAITING_FOR_PLAYER_ACTION,
    PLAYER_ACTION,
    OPPONENT_ACTION,
    WIN,
    LOSS,
    FLEE,
    SELECT_NEW_KAIJUMON



}
