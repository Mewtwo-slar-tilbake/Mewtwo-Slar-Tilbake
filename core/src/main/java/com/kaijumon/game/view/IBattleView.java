package com.kaijumon.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kaijumon.game.model.battle.BattleParty;
import com.kaijumon.game.screens.ui.ActionSelectBox;
import com.kaijumon.game.screens.ui.KaijumonInfo;
import com.kaijumon.game.screens.ui.OptionBox;

public interface IBattleView {

    /**
     * Render the model to the screen.
     *
     * @param delta delta time.
     */
    void render(float delta);

    /**
     * Dispose of all loaded assets.
     */
    void dispose();

    /**
     * Set the player's kaijumon texture
     * @param texture the texture to set.
     */
    void setPlayerTexture(Texture texture);

    /**
     * @return the BattleView label
     */
    Label getLabel();

    /**
     *
     * @param party the party to get the kaijumon info for. (eather player or opponent)
     * @return the KaijumonInfo for the given party.
     */
    KaijumonInfo getKaijumonInfo(BattleParty party);

    /**
     * @return the ActionSelectBox for the BattleView.
     */
    ActionSelectBox getActionSelectBox();

    /**
     * @return the OptionBox for the BattleView.
     */
    OptionBox getOptionBox();

    /**
     * Resize the BattleView.
     * @param width width to set the view to
     * @param height height to set the view to
     */
    void resize(int width, int height);
}
