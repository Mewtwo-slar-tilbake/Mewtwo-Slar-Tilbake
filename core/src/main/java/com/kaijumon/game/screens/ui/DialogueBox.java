package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Represents a Dialogue Box that can hold any given text.
 * The dialogue box can be placed on the screen the same way as LibGDX tables are placed.
 */
public class DialogueBox extends Table {

    private Label textLabel;

    public DialogueBox(String text, Skin skin) {
        super(skin);
        


        textLabel = new Label(text, skin);
        this.add(textLabel).expand().align(Align.left).pad(10);
    }

    @Override
    public float getPrefWidth() {
        return 200f;
    }
}