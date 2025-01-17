package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kaijumon.game.utils.SceneUtils;

public class HealthBar extends ProgressBar {

    public HealthBar(int health, Skin skin) {
        super(0, 100, 1, false, skin);
        getStyle().background = SceneUtils.getColoredDrawable((int) getWidth(), (int) getHeight(), Color.RED);
        getStyle().knob = SceneUtils.getColoredDrawable(0, (int) getHeight(), Color.GREEN);
        getStyle().knobBefore = SceneUtils.getColoredDrawable((int) getWidth(), (int) getHeight(), Color.GREEN);

        setValue(health);
    }

}
