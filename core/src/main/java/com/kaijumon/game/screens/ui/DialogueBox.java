package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Represents a Dialogue Box that can hold any given text.
 * The dialogue box can be placed on the screen the same way as LibGDX tables are placed.
 */
public class DialogueBox extends Table {

    private Label label;

    public DialogueBox(String text, Skin skin) {
        super(skin);

        this.defaults().pad(5);

        label = new Label(text, skin);
        this.add(label).expand().align(Align.left).pad(10);
    }

    public void setText(String text) {
        label.setText(text);
    }

    public void setTextColor(Color color){
        label.getStyle().fontColor = color;
    }

}
