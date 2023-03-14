package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Represents a widget that displays a Kaijumon's stats during battle.
 */
public class KaijumonInfo extends Table {

    private String name = "Pikachu";
    private int lvl = 1;
    private int hp = 100;

    private Label textLabel;

    public KaijumonInfo(Skin skin) {
        super(skin);
        this.defaults().pad(5);

        textLabel = new Label(name, skin);
        this.add(textLabel).expand().align(Align.topLeft);

        textLabel = new Label(String.valueOf(lvl), skin);
        this.add(textLabel).expand().align(Align.topRight);
        this.row();

        HealthBar healthBar = new HealthBar(70, skin);
        this.add(healthBar).expand().align(Align.bottomRight);

        textLabel = new Label("HP", skin);
        this.add(textLabel).expand().align(Align.right);
    }

    @Override
    public float getPrefWidth() {
        return 200f;
    }
}
