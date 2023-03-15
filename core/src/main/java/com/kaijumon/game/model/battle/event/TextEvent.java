package com.kaijumon.game.model.battle.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.kaijumon.game.screens.ui.DialogueBox;

/**
 * Represents an event where text is displayed on the screen.
 */
public class TextEvent extends Event {

    private boolean finished = false;
    private boolean awaitInput = false;
    private String text;

    private DialogueBox dialogueBox;

    public TextEvent(String text) {
        this.text = text;
    }

    public TextEvent(String text, boolean awaitInput) {
        this(text);
        this.awaitInput = awaitInput;
    }

    @Override
    public void begin(IEventHandler handler) {
        super.begin(handler);
        dialogueBox = handler.getDialogueBox();
        dialogueBox.setVisible(true);
        dialogueBox.setText(text);
    }

    @Override
    public void update(float delta) {
        if (awaitInput || Gdx.input.isKeyJustPressed(Input.Keys.X) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

}
