package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

/**
 * Represents a dialog that allows the user to select one of four actions.
 * The indices of the actions are laid out like this:
 * 		0	1
 * 		2	3
 */
public class ActionSelectBox extends Table {

    private int selectedIndex = 0;

    private Label[] labels = new Label[4];
    private Image[] arrows = new Image[4];

    private Table uiContainer;

    public ActionSelectBox(Skin skin) {
        super(skin);
        this.setBackground("white");
        this.uiContainer = new Table();

        this.add(uiContainer).pad(5);

        labels[0] = new Label("Fight", skin);
        labels[1] = new Label("Kaijumon", skin);
        labels[2] = new Label("Bag", skin);
        labels[3] = new Label("Flee", skin);

        arrows[0] = new Image(skin, "check-on");
        arrows[0].setScaling(Scaling.none);
        arrows[1] = new Image(skin, "check-on");
        arrows[1].setScaling(Scaling.none);
        arrows[2] = new Image(skin, "check-on");
        arrows[2].setScaling(Scaling.none);
        arrows[3] = new Image(skin, "check-on");
        arrows[3].setScaling(Scaling.none);

        uiContainer.add(arrows[0]).space(5f);
        uiContainer.add(labels[0]).space(5f).align(Align.left);
        uiContainer.add(arrows[1]).space(5f);
        uiContainer.add(labels[1]).space(5f).align(Align.left).row();
        uiContainer.add(arrows[2]).space(5f);
        uiContainer.add(labels[2]).space(5f).align(Align.left);
        uiContainer.add(arrows[3]).space(5f);
        uiContainer.add(labels[3]).space(5f).align(Align.left);

        setSelected(0);
    }

    public int getSelected() {
        return selectedIndex;
    }

    public void moveUp() {
        if (selectedIndex == 2)
            setSelected(0);
        else if (selectedIndex == 3)
            setSelected(1);
    }

    public void moveDown() {
        if (selectedIndex == 0)
            setSelected(2);
        else if (selectedIndex == 1)
            setSelected(3);
    }

    public void moveLeft() {
        if (selectedIndex == 1)
            setSelected(0);
        else if (selectedIndex == 3)
            setSelected(2);
    }

    public void moveRight() {
        if (selectedIndex == 0)
            setSelected(1);
        if (selectedIndex == 2)
            setSelected(3);
    }

    private void setSelected(int index) {
        selectedIndex = index;
        for (int i = 0; i < labels.length; i++) {
            if (i == index)
                arrows[i].setVisible(true);
            else
                arrows[i].setVisible(false);
        }
    }

}
