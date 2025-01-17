package com.kaijumon.game.model.items;

import com.kaijumon.game.model.entities.Kaijumon;

public class HealthPotion extends Item {
    private final int healthBoost;

    public HealthPotion(String name, String description, int healthBoost) {
        super(name, description);
        this.healthBoost = healthBoost;
    }

    @Override
    public void use(Kaijumon affectedKaijumon) {
        affectedKaijumon.setHp(affectedKaijumon.getStats().hp + healthBoost);

    }
}
