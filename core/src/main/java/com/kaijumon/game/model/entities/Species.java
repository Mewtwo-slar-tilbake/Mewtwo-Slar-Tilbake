package com.kaijumon.game.model.entities;

import java.util.Random;

public enum Species {
    BLAZEOX(Element.FIRE),
    FROSTBITE(Element.ICE),
    CHARALALA(Element.DRAGON),
    MAGDO(Element.GROUND),
    THUNDERBOLT(Element.ELECTRIC);

    public final Element element;
    private Species(Element element){
        this.element = element;
    }

    public static Species randomSpecies() {
        int pick = new Random().nextInt(Species.values().length);
        return Species.values()[pick];
    }
}
