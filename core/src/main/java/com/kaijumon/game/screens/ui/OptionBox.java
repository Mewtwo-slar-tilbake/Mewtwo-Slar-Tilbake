package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.List;

public class OptionBox extends Table {

    private int selectedIndex = 0;
    private List<Image> arrows = new ArrayList<>();
    private List<Label> options = new ArrayList<>();

    private Table uiContainer;

    public OptionBox(Skin skin) {
        super(skin);
        this.setBackground("white");

        uiContainer = new Table();
        this.add(uiContainer).pad(5);
    }

    public void addOption(String option) {
        Label optionLabel = new Label(option, this.getSkin());
        options.add(optionLabel);
        Image arrow = new Image(this.getSkin(), "check-on");
        arrow.setVisible(false);
        arrows.add(arrow);

        uiContainer.add(arrow).expand().align(Align.left).space(5 );
        uiContainer.add(optionLabel).expand().align(Align.left).space(5);
        uiContainer.row();

        updateArrowVisibility();
    }

    public void moveUp() {
        selectedIndex = Math.max(selectedIndex - 1, 0);
        updateArrowVisibility();
    }

    public void moveDown() {
        selectedIndex = Math.min(selectedIndex + 1, options.size() - 1);
        updateArrowVisibility();
    }

    public int getSelected() {
        return selectedIndex;
    }

    public void clear() {
        uiContainer.clearChildren();
        arrows.clear();
        options.clear();
        selectedIndex = 0;
    }

    private void updateArrowVisibility() {
        for (int i = 0; i < arrows.size(); i++) {
            if (i == selectedIndex)
                arrows.get(i).setVisible(true);
            else
                arrows.get(i).setVisible(false);
        }
    }

}
