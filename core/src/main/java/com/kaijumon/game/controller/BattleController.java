package com.kaijumon.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.Model;
import com.kaijumon.game.model.battle.Action;
import com.kaijumon.game.model.battle.BattleModel;
import com.kaijumon.game.model.battle.BattleState;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.battle.event.HpChangeEvent;
import com.kaijumon.game.model.battle.event.TextEvent;
import com.kaijumon.game.model.entities.Attack;
import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.screens.ui.ActionSelectBox;
import com.kaijumon.game.screens.ui.HealthBar;
import com.kaijumon.game.screens.ui.OptionBox;
import com.kaijumon.game.view.BattleView;

import java.util.Queue;

/**
 * Represents a controller for handling input on the battle screen.
 */
public class BattleController extends InputAdapter {

    public enum STATE {
        DEACTIVATED,
        SELECT_ACTION,
        SELECT_OPTION
    }

    private STATE state = STATE.DEACTIVATED;
    private Action playerAction;

    private int playerSelectOption;
    private Action opponentAction;

    private final KaijumonGame game;
    private BattleModel battleModel;
    private BattleView view;
    private Queue<Event> eventQueue;

    private ActionSelectBox actionSelectBox;
    private OptionBox optionBox;

    private Model model;

    private Kaijumon opponentKaijumon;


    public BattleController(
            final KaijumonGame game,
            BattleModel model,
            BattleView view,
            Queue<Event> eventQueue,
            Model m,
            Kaijumon opponentKaijumon
    ) {
        this.game = game;
        this.battleModel = model;
        this.view = view;
        this.actionSelectBox = view.getActionSelectBox();
        this.optionBox = view.getOptionBox();
        this.eventQueue = eventQueue;
        this.model = m;
        this.opponentKaijumon = opponentKaijumon;

        newTurn();
    }

    public void update() {
        while (eventQueue.size() > 0) {
            Event event = eventQueue.poll();
            handleEvent(event);
        }

        BattleState state = battleModel.getState();
        if (state == BattleState.WIN || state == BattleState.FLEE) {
            game.setScreen(game.gameScreen);
            view.dispose();
        } else if (state == BattleState.LOSS) {
            game.setScreen(game.gameScreen);
            model.getPlayer().setPosition(18, 37);
            battleModel.FullHPCrew();
        }
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
        actionSelectBox.setVisible(true);
        optionBox.setVisible(false);
    }

    public void setSelectOption() {
        this.state = STATE.SELECT_OPTION;
        actionSelectBox.setVisible(false);
        optionBox.setVisible(true);
    }

    private void endTurn() {
        battleModel.progress(playerAction, 0, Action.FIGHT, handleRandomOpponentOption());

        actionSelectBox.setVisible(true);
        optionBox.setVisible(false);
        this.state = STATE.SELECT_ACTION;
    }

    private boolean selectAction(int keycode) {
        if (keycode == Input.Keys.X || keycode == Input.Keys.ENTER) {
            int selection = actionSelectBox.getSelected();
            optionBox.clear();
            opponentAction = Action.FIGHT;

            switch (selection) {
                case 0:
                    playerAction = Action.FIGHT;
                    optionBox.clear();

                    for (Attack attack : battleModel.getPlayerKaijumon().getAttacksList()) {
                        optionBox.addOption(attack.getName());
                    }

                    break;
                case 1:
                    playerAction = Action.KAIJUMON;
                    optionBox.clear();

                    for (Kaijumon kaijumon : battleModel.getPlayerTrainer().getCrew()) {
                        optionBox.addOption(kaijumon.getName());
                    }

                    break;
                case 2:
                    playerAction = Action.BAG;

                    // TODO: Add bag and items.

                    break;
                case 3:
                    //selectedAction = Action.FLEE;
                    playerAction = Action.FLEE;

                    optionBox.addOption("Yes");
                    optionBox.addOption("No");

                    break;
                default:
                    eventQueue.add(new TextEvent("No such action..."));
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
        playerSelectOption = keycode;
        if (keycode == Input.Keys.X || keycode == Input.Keys.ENTER) {
            int selection = optionBox.getSelected();

            battleModel.progress(playerAction, selection, opponentAction, 0);
            endTurn();

            return true;
        } else if (keycode == Input.Keys.C || keycode == Input.Keys.ESCAPE) {
            newTurn();

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

    private void handleEvent(Event event) {
        if (event instanceof TextEvent) {
            TextEvent textEvent = (TextEvent) event;
            view.getLabel().setText(textEvent.getText());
        } else if (event instanceof HpChangeEvent) {
            HpChangeEvent hpChangeEvent = (HpChangeEvent) event;

            HealthBar healthBar = view.getKaijumonInfo(hpChangeEvent.getParty()).getHealthBar();

            healthBar.setValue(hpChangeEvent.getHp());
        }
    }

    private int handleRandomOpponentOption() {
        int randomChoice;
        if (opponentAction == Action.FIGHT) {
            randomChoice = (int) (Math.random() * opponentKaijumon.getAttacksList().size());
            return randomChoice;
        } else if (opponentAction == Action.KAIJUMON) {
            randomChoice = (int) (Math.random() * battleModel.getOpponentTrainer().getCrewSize());
            return randomChoice;
        } else if (opponentAction == Action.BAG) {
            return 0;
        }
        return 0;
    }
}
