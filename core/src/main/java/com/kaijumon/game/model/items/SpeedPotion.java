package com.kaijumon.game.model.items;

import com.kaijumon.game.model.entities.Kaijumon;

public class SpeedPotion extends Item {

    private final int speedBoost;

    /**
     * Create a new SpeedPotion.
     * @param name the name of this potion.
     * @param description the description of this potion.
     * @param speedBoost the amount of speed to boost.
     */
    public SpeedPotion(String name, String description, int speedBoost) {
        super(name, description);
        this.speedBoost = speedBoost;
    }

    @Override
    public void use(Kaijumon affectedKaijumon) {
        if (speedBoost > 0)
            affectedKaijumon.setSpeed(affectedKaijumon.getStats().speed + speedBoost);
    }
}
