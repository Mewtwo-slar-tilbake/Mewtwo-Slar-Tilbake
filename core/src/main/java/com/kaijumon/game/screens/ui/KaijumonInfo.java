package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kaijumon.game.model.entities.Kaijumon;

/**
 * Represents a widget that displays a Kaijumon's stats during battle.
 */
public class KaijumonInfo extends Table {


    private Label textLabel;
    private HealthBar healthBar;

    public KaijumonInfo(Kaijumon kaijumon, Skin skin) {
        super(skin);
        String name = kaijumon.getName();
        int lvl = kaijumon.getStats().level;
        int hp = kaijumon.getStats().hp;

        this.defaults().pad(5);

        textLabel = new Label(name, skin);
        this.add(textLabel).expand().align(Align.topLeft);

        textLabel = new Label(String.valueOf(lvl), skin);
        this.add(textLabel).expand().align(Align.topRight);
        this.row();

        healthBar = new HealthBar(hp, skin);
        healthBar.setName(name + "-health");
        this.add(healthBar).expand().align(Align.bottomRight);

        textLabel = new Label("HP", skin);
        this.add(textLabel).expand().align(Align.right);
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void updateKaijumon(Kaijumon kaijumon){
        textLabel.setText(kaijumon.getName());
    }

}
