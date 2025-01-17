package com.kaijumon.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kaijumon.game.model.entities.Species;

/**
 * Represents a mapping from kaijumon species to textures.
 */
public class KaijumonTextureMap {

    public static Texture getTexture(Species species) {
        return new Texture(
                Gdx.files.internal("assets/kaijumons/" + species.name().toLowerCase() + ".png")
        );
    }

}
