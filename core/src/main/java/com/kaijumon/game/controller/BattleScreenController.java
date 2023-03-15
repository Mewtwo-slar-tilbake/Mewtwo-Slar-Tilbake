package com.kaijumon.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.kaijumon.game.model.battle.Action;
import com.kaijumon.game.model.battle.Battle;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.battle.event.TextEvent;
import com.kaijumon.game.model.entities.Attack;
import com.kaijumon.game.screens.ui.ActionSelectBox;
import com.kaijumon.game.screens.ui.DialogueBox;
import com.kaijumon.game.screens.ui.OptionBox;

import java.util.Queue;

/**
 * Represents a controller for handling input on the battle screen.
 */
public class BattleScreenController extends InputAdapter {

    public enum STATE {
        DEACTIVATED,
        SELECT_ACTION,
        SELECT_OPTION
    }

    private STATE state = STATE.DEACTIVATED;
    private Action selectedAction;

    private Queue<Event> queue;
    private Battle battle;

    private DialogueBox dialogueBox;
    private ActionSelectBox actionSelectBox;
    private OptionBox optionBox;

    public BattleScreenController(
            Battle battle,
            Queue<Event> queue,
            DialogueBox dialogueBox,
            ActionSelectBox actionSelectBox,
            OptionBox optionBox
    ) {
        this.battle = battle;
        this.queue = queue;
        this.dialogueBox = dialogueBox;
        this.actionSelectBox = actionSelectBox;
        this.optionBox = optionBox;

        newTurn();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (this.state == STATE.DEACTIVATED)
            return false;
        if (state == STATE.SELECT_ACTION)
            return selectAction(keycode);
        if (state == STATE.SELECT_OPTION)
            return selectOption(keycode);

        return false;
    }

    /**
     * Displays the UI for a new turn, where the player can select which action to perform.
     */
    public void newTurn() {
        this.state = STATE.SELECT_ACTION;
        dialogueBox.setVisible(false);
        actionSelectBox.setVisible(true);
        optionBox.setVisible(false);
    }

    public void setSelectOption() {
        this.state = STATE.SELECT_OPTION;
        dialogueBox.setVisible(false);
        actionSelectBox.setVisible(false);
        optionBox.setVisible(true);
    }

    private void endTurn() {
        actionSelectBox.setVisible(false);
        optionBox.setVisible(false);
        this.state = STATE.DEACTIVATED;
    }

    private boolean selectAction(int keycode) {
        if (keycode == Input.Keys.X || keycode == Input.Keys.ENTER) {
            int selection = actionSelectBox.getSelected();
            optionBox.clear();

            switch (selection) {
                case 0:
                    selectedAction = Action.FIGHT;
                    optionBox.clear();

                    for (Attack attack : battle.getPlayerKaijumon().getAttacksList()) {
                        optionBox.addOption(attack.getName());
                    }

                    break;
                case 1:
                    selectedAction = Action.KAIJUMON;

                    // TODO: Implement switch kaijumon logic.

                    break;
                case 2:
                    selectedAction = Action.BAG;

                    // TODO: Add bag and items.

                    break;
                case 3:
                    selectedAction = Action.FLEE;

                    optionBox.addOption("Yes");
                    optionBox.addOption("No");

                    break;
                default:
                    queue.add(new TextEvent("No such action..."));
            }

            setSelectOption();
            return true;
        } else if (keycode == Input.Keys.UP) {
            actionSelectBox.moveUp();
            return true;
        } else if (keycode == Input.Keys.DOWN) {
            actionSelectBox.moveDown();
            return true;
        } else if (keycode == Input.Keys.LEFT) {
            actionSelectBox.moveLeft();
            return true;
        } else if (keycode == Input.Keys.RIGHT) {
            actionSelectBox.moveRight();
            return true;
        }

        return false;
    }

    private boolean selectOption(int keycode) {
        if (keycode == Input.Keys.X) {
            int selection = optionBox.getSelected();

            battle.progress(selectedAction, selection);
            endTurn();

            return true;
        } else if (keycode == Input.Keys.UP) {
            optionBox.moveUp();
            return true;
        } else if (keycode == Input.Keys.DOWN) {
            optionBox.moveDown();
            return true;
        }

        return false;
    }

}
