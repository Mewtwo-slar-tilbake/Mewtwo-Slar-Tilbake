package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;


public class DialogueBox extends Table {

    private Label textLabel;

    public DialogueBox(String text, Skin skin) {
        super(skin);
        textLabel = new Label(text, skin);
        this.add(textLabel).expand().align(Align.left).pad(10);
    }

    public void setText(String text) {
        this.textLabel.setText(text);
    }
}
