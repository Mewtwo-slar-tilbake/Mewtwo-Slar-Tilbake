package com.kaijumon.game.model.entities;

/**
 * Represents Kaijumon stats.
 */
public class Stats {

    public int hp;
    public int attack;
    public int defense;
    public int specialAttack;
    public int specialDefense;
    public int speed;

    public Stats(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Stats))
            return false;
        Stats other = (Stats) obj;

        return this.hp == other.hp
            && this.attack == other.attack
            && this.defense == other.defense
            && this.specialAttack == other.specialAttack
            && this.specialDefense == other.specialDefense
            && this.speed == other.speed;
    }

}