package com.kaijumon.game.model.items;

import com.kaijumon.game.model.entities.IStatBoost;

public class SpeedPotion extends Item {

    private final int speedBoost;

    public SpeedPotion(String name, String description, int speedBoost) {
        super(name, description);
        this.speedBoost = speedBoost;
    }

    @Override
    public void use(IStatBoost stats) {
        if (speedBoost > 0)
            stats.increaseSpeed(speedBoost);
    }
}