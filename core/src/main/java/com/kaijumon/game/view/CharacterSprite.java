package com.kaijumon.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kaijumon.game.model.Direction;
import com.kaijumon.game.model.entities.IEntity;
import com.kaijumon.game.utils.Consts;

public class CharacterSprite {
    private final TextureRegion[] down = new TextureRegion[4];
    private final TextureRegion[] up = new TextureRegion[4];
    private final TextureRegion[] left = new TextureRegion[4];
    private final TextureRegion[] right = new TextureRegion[4];
    private final int numImages;

    protected CharacterSprite(String spritePath) {
        Texture playerSpriteSheet = new Texture(Gdx.files.internal(spritePath));
        numImages = 4; // number of images per direction.
        for (int i = 0; i < numImages;  i++) {
            down[i]   = new TextureRegion(playerSpriteSheet, 32 * (i + numImages * 0), 0, 31, 35);
            right[i]  = new TextureRegion(playerSpriteSheet, 32 * (i + numImages * 1), 0, 31, 35);
            up[i]     = new TextureRegion(playerSpriteSheet, 32 * (i + numImages * 2), 0, 31, 35);
            left[i]   = new TextureRegion(playerSpriteSheet, 32 * (i + numImages * 3), 0, 31, 35);
        }
    }

    protected TextureRegion getSprite(IEntity character) {
        Direction direction = character.getDirection();
        Long timeSinceLastMoved = character.getTimeSinceLastMove();
        int index;
        if (timeSinceLastMoved == 0 || timeSinceLastMoved > Consts.timeBetweenMovesMillis) {
            index = 0;
        } else {
            index = (int) ((timeSinceLastMoved / (Consts.timeBetweenMovesMillis / numImages)) % 4);
        }

        if (index < 0 || index >= numImages) {
            System.out.println("timeSinceLastMoved: " + timeSinceLastMoved + "( --- Consts.timeBetweenMovesMillis / numImages) " + (Consts.timeBetweenMovesMillis / numImages));
            throw new RuntimeException("Index out of bounds: " + index);
        }

        switch (direction){
            case UP:
                return up[index];
            case DOWN:
                return down[index];
            case LEFT:
                return left[index];
            case RIGHT:
                return right[index];
        }

        return down[0];

    }
}
