package com.kaijumon.game.model.battle.event;

import com.kaijumon.game.model.battle.BattleState;
import com.kaijumon.game.screens.ui.DialogueBox;
import com.kaijumon.game.screens.ui.HealthBar;

public interface IEventHandler {

    /**
     * Enable events to retrieve the dialogue box from the event handler.
     */
    public DialogueBox getDialogueBox();

    /**
     * Enable events to retrieve the health bar from the event handler.
     */
    public HealthBar getHealthBar();

}
