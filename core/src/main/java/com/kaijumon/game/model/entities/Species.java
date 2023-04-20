package com.kaijumon.game.model.entities;

import java.util.Random;

public enum Species {
    BLAZEOX(Element.FIRE),
    FROSTBITE(Element.ICE),
    CHARALALA(Element.DRAGON),
    MAGDO(Element.GROUND),
    THUNDERBOLT(Element.ELECTRIC);

    public final Element element;
    private static final Random random = new Random();
    private Species(Element element){
        this.element = element;
    }

    public static Species randomSpecies() {
        int pick = random.nextInt(Species.values().length);
        return Species.values()[pick];
    }
}
