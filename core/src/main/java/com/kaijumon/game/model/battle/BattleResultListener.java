package com.kaijumon.game.model.battle;

import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.map.MapController;

public abstract class BattleResultListener {
    private Player player;

    /**
     * a result listener that will be called after every battle. this way we can add custom behavior after a battle.
     * if you want to add extra custom behavior, override the onBattleResultCustom method.
     * if you don't want to add extra custom behavior, you can just call "new BattleResultListener(){}"
     * @param state the outcome of the battle
     * @param player the player
     */
    public final void onBattleResult(BattleState state, Player player) {
        this.player = player;

        if (state != BattleState.WIN && state != BattleState.LOSS && state != BattleState.FLEE)
            throw new IllegalArgumentException("BattleResultListener.onBattleResult() can only be called after a battle has ended. (BattleState.WIN, BattleState.LOSS or BattleState.FLEE");

        // Default behavior
        onBattleResultDefault(state);
        // Custom behavior
        onBattleResultCustom(state);
    }

    /**
     * Default behavior for battle result. this will be called after every battle.
     * @param state the outcome of the battle
     */
    protected final void onBattleResultDefault(BattleState state) {
        if (state == BattleState.WIN) {
            for (Kaijumon kaijumon : player.getBag().getCrew()) {
                kaijumon.setXp(kaijumon.getStats().xp+50);
                if (kaijumon.getStats().xp >= 100) {
                    int tmpxp = kaijumon.getStats().xp-100;
                    kaijumon.setLevel(kaijumon.getStats().level+1);
                    kaijumon.setXp(Math.max(tmpxp, 0));
                }
            }
            //TODO add money or xp or something
        }
        if (state == BattleState.LOSS) {

            MapController.getInstance().moveToKaijuCenter(player);

            for (Kaijumon kaijumon : player.getBag().getCrew()){
                kaijumon.getStats().hp = 100;
            }
        }
    }

    /**
     * Override this method to add custom behavior.
     * @param state the outcome of the battle
     */
    protected void onBattleResultCustom(BattleState state){}
}
