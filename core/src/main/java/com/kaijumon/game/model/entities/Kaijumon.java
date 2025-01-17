package com.kaijumon.game.model.entities;

import java.util.List;

/**
 * Represents a Kaijumon, an entity with stats and attacks used for battling.
 */
public class Kaijumon implements Cloneable {

    private String name;
    private Stats stats;
    private Element element;
    private List<Attack> attacks;
    private Species species;


    public Kaijumon(String name, Stats stats, Element element, List<Attack> attacks, Species species) {
        this.name = name;
        this.stats = stats;
        this.element = element;
        this.attacks = attacks;
        this.species = species;
    }

    /**
     * Get this Kaijumon's name.
     */
    public String getName(){
        return this.name;
    }
    
    

    /**
     * Get this Kaijumon's attacks.
     */
    public List<Attack> getAttacksList(){
        return this.attacks;
    }

    /**
     * Get a specified attack from this Kaijumon's attack list.
     *
     * @param index attack index in the attack list.
     */
    public Attack getAttack(int index) {
        return attacks.get(index);
    }

    /**
     * Get this Kaijumon's stats.
     */
    public Stats getStats() {
        return this.stats;
    }

    /**
     * Get this Kaijumon's element.
     */
    public Element getElement(){
        return this.element;
    }

    public Species getSpecies() {
        return this.species;
    }

    /**
     * @return true if this Kaijumon has fainted, which means that its HP is depleted.
     */
    public boolean isFainted() {
        return stats.hp <= 0;
    }

    /**
     * Decrese this Kaijumon's HP by a given amount.
     */
    public void takeDamage(int damage) {
        this.stats.hp = Math.max(0, stats.hp - damage);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Kaijumon))
            return false;
        Kaijumon other = (Kaijumon) obj;

        return this.name.equals(other.name)
            && this.stats.equals(other.stats)
            && this.element.equals(other.element)
            && this.attacks.equals(other.attacks);
    }

    @Override
    public int hashCode(){
        return this.name.hashCode() + this.stats.hashCode() + this.element.hashCode() + this.attacks.hashCode();
    }



    public void setHp(int healthBoost) {
        if (this.stats.hp + healthBoost > this.stats.maxHp) {
            this.stats.hp = this.stats.maxHp;
        } else
            this.stats.hp = healthBoost;
    }


    public void setAttack(int attackBoost) {
        this.stats.attack = attackBoost;
    }

    public void setSpeed(int speedBoost) {
        this.stats.speed = speedBoost;
    }


    public void setLevel(int levelBoost) {
        this.stats.level = levelBoost;
    }

    public void setXp(int expBoost) {
        this.stats.xp = expBoost;
    }


    @Override
    public Kaijumon clone() {
        try {
            Kaijumon clone = (Kaijumon) super.clone();
            clone.name = this.name;
            clone.stats = this.stats;
            clone.element = this.element;
            clone.attacks = this.attacks;
            clone.species = this.species;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
