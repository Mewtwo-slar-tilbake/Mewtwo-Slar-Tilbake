package com.kaijumon.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.IBattleModel;
import com.kaijumon.game.model.battle.Action;
import com.kaijumon.game.model.battle.BattleResultListener;
import com.kaijumon.game.model.battle.BattleState;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.battle.event.HpChangeEvent;
import com.kaijumon.game.model.battle.event.KaijumonChangeEvent;
import com.kaijumon.game.model.battle.event.TextEvent;
import com.kaijumon.game.model.entities.*;
import com.kaijumon.game.model.items.Item;
import com.kaijumon.game.model.items.KaijuBall;
import com.kaijumon.game.screens.ui.ActionSelectBox;
import com.kaijumon.game.screens.ui.HealthBar;
import com.kaijumon.game.screens.ui.KaijumonInfo;
import com.kaijumon.game.screens.ui.OptionBox;
import com.kaijumon.game.utils.KaijumonTextureMap;
import com.kaijumon.game.view.IBattleView;

import java.util.Queue;

/**
 * Represents a controller for handling input on the battle screen.
 */
public class BattleController extends InputAdapter implements IBattleController {

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
    private IBattleModel battleModel;
    private IBattleView battleView;
    private Queue<Event> eventQueue;

    private ActionSelectBox actionSelectBox;
    private OptionBox optionBox;

    private Player player;
    private ITrainer opponent;
    private BattleResultListener resultListener;

    private Kaijumon opponentKaijumon;


    public BattleController(
            final KaijumonGame game,
            IBattleModel model,
            IBattleView battleView,
            Queue<Event> eventQueue,
            Kaijumon opponentKaijumon,
            Player player,
            ITrainer opponentTrainer,
            BattleResultListener resultListener
    ) {
        this.game = game;
        this.battleModel = model;
        this.battleView = battleView;
        this.actionSelectBox = battleView.getActionSelectBox();
        this.optionBox = battleView.getOptionBox();
        this.eventQueue = eventQueue;
        this.opponentKaijumon = opponentKaijumon;
        this.player = player;
        this.resultListener = resultListener;
        this.opponent = opponentTrainer;

        newTurn();
    }

    @Override
    public void update() {
        while (eventQueue.size() > 0) {
            Event event = eventQueue.poll();
            handleEvent(event);
        }

        BattleState state = battleModel.getState();
        if (state == BattleState.WIN || state == BattleState.LOSS || state == BattleState.FLEE) {
            game.setScreen(game.worldScreen);
            battleView.dispose();
            resultListener.onBattleResult(state, player); // calling a custom method given when the controller is created to handle the result of the battle.
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
        eventQueue.add(new TextEvent("What will you do?"));
        this.state = STATE.SELECT_ACTION;
        actionSelectBox.setVisible(true);
        optionBox.setVisible(false);
    }


    /**
     * Sets the state of the object to "SELECT_OPTION", making the optionBox visible and hiding the actionSelectBox.
     */
    public void setSelectOption() {
        this.state = STATE.SELECT_OPTION;
        actionSelectBox.setVisible(false);
        optionBox.setVisible(true);
    }

    private void endTurn() {
        battleModel.progress(playerAction, playerSelectOption, Action.FIGHT, handleRandomOpponentOption());
        battleView.setPlayerTexture(KaijumonTextureMap.getTexture(
                battleModel.getPlayerKaijumon().getSpecies()));
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
                    eventQueue.add(new TextEvent("Which attack do you want to use?"));
                    playerAction = Action.FIGHT;
                    optionBox.clear();

                    for (Attack attack : battleModel.getPlayerKaijumon().getAttacksList()) {
                        optionBox.addOption(attack.getName());
                    }

                    break;
                case 1:
                    eventQueue.add(new TextEvent("Which Kaijumon do you want to use?"));
                    playerAction = Action.KAIJUMON;
                    optionBox.clear();

                    for (Kaijumon kaijumon : battleModel.getPlayerTrainer().getAliveCrew()) {
                        if (kaijumon.isFainted()){
                            optionBox.addOption(kaijumon.getName() + " (fainted)");
                            continue;
                        }
                        optionBox.addOption(kaijumon.getName());
                    }


                    break;
                case 2:
                    eventQueue.add(new TextEvent("Which item do you want to use?"));
                    playerAction = Action.BAG;
                    optionBox.clear();

                    for (Item item : player.getBag().getItems()) {
                        if (item instanceof KaijuBall && opponent.getType() != TrainerType.Wild)
                            continue;
                        optionBox.addOption(item.getName());
                    }

                    break;
                case 3:
                    eventQueue.add(new TextEvent("Are you sure you want to flee?"));
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
        if (keycode == Input.Keys.X || keycode == Input.Keys.ENTER) {
            int selection = optionBox.getSelected();
            playerSelectOption = selection;
            switch (playerAction) {
                case FIGHT:
                    eventQueue.add(new TextEvent("You used " + battleModel.getPlayerKaijumon().getAttacksList().get(selection).getName() + "!"));
                    break;
                case KAIJUMON:
                    if(battleModel.getPlayerKaijumon() == battleModel.getPlayerTrainer().getAliveCrew().get(selection)){
                        eventQueue.add(new TextEvent(battleModel.getPlayerKaijumon().getName() + " is already in battle!"));
                        return true;
                    } else{
                        eventQueue.add(new TextEvent("You sent out " + battleModel.getPlayerTrainer().getAliveCrew().get(selection).getName() + "!"));
                        break;
                    }
                case BAG:
                    eventQueue.add(new TextEvent("You used " + player.getBag().getItems().get(selection).getName() + "!"));
                    break;
                case FLEE:
                    if (selection == 0) {
                        eventQueue.add(new TextEvent("You fled from the battle!"));
                    } else {
                        eventQueue.add(new TextEvent("You decided to stay and fight!"));
                        return true;
                    }
                    break;
            }

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
        if (event instanceof TextEvent textEvent) {
            battleView.getLabel().setText(textEvent.getText());
        } else if (event instanceof HpChangeEvent hpChangeEvent) {
            HealthBar healthBar = battleView.getKaijumonInfo(hpChangeEvent.getParty()).getHealthBar();
            healthBar.setValue(hpChangeEvent.getHp());
        } else if (event instanceof KaijumonChangeEvent kaijumonChangeEvent) {
            KaijumonInfo kaijumonInfo = battleView.getKaijumonInfo(kaijumonChangeEvent.getParty());
            kaijumonInfo.updateKaijumon(kaijumonChangeEvent.getKaijumon());
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
