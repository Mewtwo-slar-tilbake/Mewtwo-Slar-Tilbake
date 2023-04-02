package com.kaijumon.game.dialog;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.screens.ui.DialogueBox;
import com.kaijumon.game.screens.ui.OptionBox;
import com.kaijumon.game.utils.SceneUtils;

public class DialogueTraverser {

    DialogueSystem dialogueSystem;
    DialogueNode currentNode;
    Stage stage;
    KaijumonGame game;
    OptionBox optionBox;

    public DialogueTraverser(Integer startNodeId) {
        dialogueSystem = DialogueSystem.getInstance();
        currentNode = dialogueSystem.getNode(startNodeId);
        DialogueSystem.getInstance().registerEventSubscriber(this);

        stage = new Stage();
    }

    /**
     * moves to the next DialogueNode in the dialogue if the given node is linear or end node
     */
    public void next(){
        if (currentNode.getNodeType() == NodeType.END_NODE) {
            endDialogue();
            return;
        }
        if (currentNode.getNodeType() != NodeType.LINEAR_NODE) {
            throw new IllegalStateException("Cannot call next() on a non-linear node");
        }
        currentNode = dialogueSystem.getNode(currentNode.getOptions().get(0).id);
        createUi();
    }

    /**
     * moves to the next DialogueNode in the dialogue if the given node is branching, or end node
     * @param optionIndex - the index of the option to choose
     */
    public void next(Integer optionIndex){
        if (currentNode.getNodeType() == NodeType.END_NODE) {
            endDialogue();
            return;
        }
        if (currentNode.getNodeType() == NodeType.LINEAR_NODE) {
            throw new IllegalStateException("Cannot call next(Integer) on a linear node");
        }
        if (currentNode.getOptions().size() <= optionIndex) {
            throw new IllegalArgumentException("The optionIndex is out of bounds");
        }
        Integer nextNodeId = currentNode.getOptions().get(optionIndex).id;
        currentNode = dialogueSystem.getNode(nextNodeId);
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

        optionBox = new OptionBox(game.getSkin());
        for (DialogueOption option : currentNode.getOptions()) {
            optionBox.addOption(option.text);
        }
        root.add(optionBox);

        stage.addActor(root);
        stage.setDebugAll(true);

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
            if (currentNode.getNodeType() == NodeType.LINEAR_NODE) {
                next();
            } else if (currentNode.getNodeType() == NodeType.END_NODE) {
                endDialogue();
            } else {
                next(optionBox.getSelected());
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
}