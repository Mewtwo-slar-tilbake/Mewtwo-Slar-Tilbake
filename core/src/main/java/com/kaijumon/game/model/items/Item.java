package com.kaijumon.game.model.items;

import com.kaijumon.game.model.entities.Kaijumon;

public abstract class Item {
    private final String name;
    private final String description;


    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Get this item's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get this item's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Use this item on a Kaijumon.
     *
     * @param stats the Kaijumon to use this item on.
     */
    public abstract void use(Kaijumon stats);
}
