package com.kaijumon.game.model.entities;

public class Attack {

    private String name;
    private int damage;
    private KaijumonType type;

    public Attack(String name, int damage, KaijumonType type) {
        this.name = name;
        this.damage = damage;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }

    public KaijumonType getType() {
        return this.type;
    }
}