package com.kaijumon.game.model.items;

import com.kaijumon.game.model.entities.IStatBoost;

public abstract class Item {
    private final String name;
    private final String description;


    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void use(IStatBoost stats);
}