package com.kaijumon.game.dialog;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.battle.BattleResultListener;
import com.kaijumon.game.model.battle.BattleState;
import com.kaijumon.game.model.entities.ITrainer;
import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.screens.BattleScreen;
import com.kaijumon.game.screens.ui.DialogueBox;
import com.kaijumon.game.screens.ui.OptionBox;
import com.kaijumon.game.utils.Consts;
import com.kaijumon.game.utils.SceneUtils;

public class DialogueTraverser {

    DialogueSystem dialogueSystem;
    DialogueNode currentNode;
    Stage stage;
    KaijumonGame game;
    OptionBox optionBox;
    Player player;
    ITrainer opponent;

    /**
     * creates a new DialogueTraverser that starts at the given node
     * this constructor is used when the dialogue does not contain any battles.
     * @param startNodeId - the id of the node to start at
     */
    public DialogueTraverser(Integer startNodeId) {
        dialogueSystem = DialogueSystem.getInstance();
        currentNode = dialogueSystem.getNode(startNodeId);
        DialogueSystem.getInstance().registerEventSubscriber(this);
        this.player = null;
        this.opponent = null;

        stage = new Stage();
    }

    public DialogueTraverser(Integer startNodeId, Player player, ITrainer opponent) {
        dialogueSystem = DialogueSystem.getInstance();
        currentNode = dialogueSystem.getNode(startNodeId);
        DialogueSystem.getInstance().registerEventSubscriber(this);
        this.player = player;
        this.opponent = opponent;


        stage = new Stage();
    }

    /**
     * moves to the next DialogueNode in the dialogue.
     */
    public void next(){
        if (currentNode.getNodeType() == NodeType.END_NODE) {
            endDialogue();
            return;
        }
        if (currentNode.getNodeType() == NodeType.LINEAR_NODE) {
            currentNode = dialogueSystem.getNode(currentNode.getOptions().get(0).id);
        } else if (currentNode.getNodeType() == NodeType.BRANCHING_NODE) {
            Integer nextNodeId = currentNode.getOptions().get(optionBox.getSelected()).id;
            currentNode = dialogueSystem.getNode(nextNodeId);
        } else {
            throw new RuntimeException("DialogueNode type has not been implemented yet.");
        }
        createUi();
    }

    /**
     * creates the ui for the current DialogueNode.
     * this method should be called each time the currentNode is changed.
     */
    private void createUi() {
        stage.clear();

        Table root = new Table();
        root.setFillParent(true);

        Table topTable = new Table(game.getSkin());
        topTable.setWidth(stage.getWidth());

        root.add(topTable).expand().align(Align.top);
        root.row();

        Table textTable = new Table(game.getSkin());
        textTable.setBackground(SceneUtils.getColoredDrawable(1,1, Color.WHITE));
        DialogueBox dialogueBox = new DialogueBox(currentNode.text, game.getSkin());
        dialogueBox.setTextColor(Color.DARK_GRAY);
        textTable.add(dialogueBox);
        root.add(textTable).fill();

        if (currentNode.getOptions().size() > 0) {
            optionBox = new OptionBox(game.getSkin());
            optionBox.setBackground(SceneUtils.getColoredDrawable(1,1, Color.WHITE));
            for (DialogueOption option : currentNode.getOptions()) {
                optionBox.addOption(option.text);
            }
            root.add(optionBox);
        }

        stage.addActor(root);
        stage.setDebugAll(Consts.DEBUG);

    }

    /**
     * renders the ui for the current DialogueNode
     * @param game - the game instance used to get the skin. (this is a hacky way to get the skin, but since we need a reference to the game, this is the easiest way to do it)
     */
    public void render(KaijumonGame game) {
        if (this.game == null) {
            this.game = game;
            createUi();
        }
        stage.act();
        stage.draw();
    }

    /**
     * handles input keyboard events for the current DialogueNode
     * @param keyCode - the keyCode of the input event
     */
    public void handleINputEvent(int keyCode) {
        if (keyCode == Input.Keys.UP){
            optionBox.moveUp();
        } else if (keyCode == Input.Keys.DOWN) {
            optionBox.moveDown();
        } else if (keyCode == Input.Keys.ENTER || keyCode == Input.Keys.SPACE) {
            if (currentNode.getNodeType() != NodeType.END_NODE && currentNode.getOptions().get(optionBox.getSelected()).battle) {
                handleBattleOption(currentNode.getOptions().get(optionBox.getSelected()));
            } else {
                next();
            }
        }
    }

    /**
     * method called when the dialogue is finished
     */
    private void endDialogue() {
        stage.clear();
        DialogueSystem.getInstance().unregisterEventSubscriber(this);
    }

    private void handleBattleOption(DialogueOption dialogueOption) {
        if (this.player == null || this.opponent == null) {
            throw new IllegalStateException("A dialogue option to start a battle was chosen. but the constructor for the DialogueTraverser that was used to start this dialogue does not allow for battles. please change to the other constructor.");
        }

        BattleResultListener resultListener = new BattleResultListener() {
            @Override
            public void onBattleResultCustom(BattleState battleResult) {
                if (currentNode.getNodeType() == NodeType.END_NODE) {
                    endDialogue();
                    return;
                }

                Integer nextNodeId;
                if (battleResult == BattleState.WIN && dialogueOption.win != null) {
                    nextNodeId = dialogueOption.win;
                } else if (battleResult == BattleState.LOSS && dialogueOption.loss != null) {
                    nextNodeId = dialogueOption.loss;
                } else if (battleResult == BattleState.FLEE && dialogueOption.loss != null) {
                    nextNodeId = dialogueOption.loss;
                } else {
                    nextNodeId = dialogueOption.id;
                }

                System.out.println("next node id: " + nextNodeId + " with battle result: " + battleResult);
                currentNode = dialogueSystem.getNode(nextNodeId);
                createUi();
            }
        };

        for (Kaijumon kaijumon : opponent.getCrew()) {
            kaijumon.setHp(100);
        }
        BattleScreen battleScreen = new BattleScreen(game, player, opponent, resultListener);
        game.setScreen(battleScreen);
    }
}
