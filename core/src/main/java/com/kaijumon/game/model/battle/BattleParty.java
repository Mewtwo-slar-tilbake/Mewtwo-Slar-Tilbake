package com.kaijumon.game.model.battle;

/**
 * Represents both parties in a battle, the player or the opponent.
 */
public enum BattleParty {

    PLAYER,
    OPPONENT;

    public static BattleParty getOpposite(BattleParty party) {
        switch(party) {
            case PLAYER:
                return OPPONENT;
            case OPPONENT:
                return PLAYER;
            default:
                return null;
        }
    }
}
