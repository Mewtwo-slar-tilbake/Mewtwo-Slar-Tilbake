package com.kaijumon.game.model.entities;

public enum Attack {

    PUNCH("Punch", Element.NORMAL, 100),
    SLASH("Slash", Element.NORMAL, 50);

    private final String name;
    private final Element element;
    private final int power;

    Attack(String name, Element element, int power) {
        this.name = name;
        this.element = element;
        this.power = power;
    }

    public String getName() {
        return this.name;
    }

    public Element getElement() {
        return this.element;
    }

    public int getPower() {
        return this.power;
    }

}
