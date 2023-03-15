package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * Represents the four buttons used for inputting actions during battle.
 */
public class BattleButtons extends Table {

    private TextButton button;

    public BattleButtons(
            Skin skin,
            ClickListener fightListener,
            ClickListener kaijumonListener,
            ClickListener bagListener,
            ClickListener fleeListener
    ) {
        super(skin);
        this.defaults().pad(10);

        button = new TextButton("Fight", skin);
        button.addListener(fightListener);

        this.add(button).growX().align(Align.topLeft);

        button = new TextButton("Kaijumon", skin);
        button.addListener(kaijumonListener);

        this.add(button).growX().align(Align.topRight);
        this.row();

        button = new TextButton("Bag", skin);
        button.addListener(bagListener);

        this.add(button).growX().align(Align.bottomRight);

        button = new TextButton("Flee", skin);
        button.addListener(fleeListener);

        this.add(button).growX().align(Align.bottomRight);
    }

}
