package com.kaijumon.game.model.entities;

import java.util.Random;

public enum Species {
    BLAZEOX(Element.FIRE),
    FROSTBITE(Element.ICE),
    CHARALALA(Element.DRAGON),
    MAGDO(Element.GROUND),
    THUNDERBOLT(Element.ELECTRIC),
    CHICO(Element.FLYING),
    PANDO(Element.GROUND),
    MONKO(Element.FIGHTING),
    SQUEEKS(Element.GRASS),
    KATO(Element.FIGHTING),
    WEIRDO(Element.ROCK),
    GIRAFFO(Element.FIRE),
    PINGO(Element.WATER),
    SICKO(Element.PSYCHIC),
    GOATO(Element.FIGHTING),
    CATO(Element.ICE),
    XACO(Element.ELECTRIC),
    HAIRO(Element.NORMAL),
    FIDO(Element.FIGHTING),
    OTTO(Element.WATER),
    SIMBAO(Element.ELECTRIC),
    ARTICFOXO(Element.ICE),
    DOGGO(Element.NORMAL),
    OWLO(Element.FLYING),
    DEERO(Element.POISON)
    ;

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
