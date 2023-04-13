package com.kaijumon.game.model.items;

import com.kaijumon.game.model.entities.IStatBoost;
import com.kaijumon.game.model.entities.Kaijumon;

public class SpeedPotion extends Item {

    private final int speedBoost;

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
