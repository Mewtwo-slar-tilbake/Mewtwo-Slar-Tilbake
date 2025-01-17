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

    private Label nameLabel;
    private Label lvlLabel;
    private HealthBar healthBar;

    public KaijumonInfo(Kaijumon kaijumon, Skin skin) {
        super(skin);
        String name = kaijumon.getName();
        int lvl = kaijumon.getStats().level;
        int hp = kaijumon.getStats().hp;

        this.defaults().pad(5);

        nameLabel = new Label(name, skin);
        this.add(nameLabel).expand().align(Align.topLeft);

        lvlLabel = new Label(String.valueOf(lvl), skin);
        this.add(lvlLabel).expand().align(Align.topRight);
        this.row();

        healthBar = new HealthBar(hp, skin);
        healthBar.setName(name + "-health");
        this.add(healthBar).expand().align(Align.bottomRight);

        Label label = new Label("HP", skin);
        this.add(label).expand().align(Align.right);
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void updateKaijumon(Kaijumon kaijumon){
        nameLabel.setText(kaijumon.getName());
        lvlLabel.setText(kaijumon.getStats().level);
        healthBar.setValue(kaijumon.getStats().hp);
    }

}
