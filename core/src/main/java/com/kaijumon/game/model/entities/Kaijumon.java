package com.kaijumon.game.model.entities;

import java.util.List;

/**
 * Represents a Kaijumon, an entity with stats and attacks used for battling.
 */
public class Kaijumon {

    private String name;
    private Stats stats;
    private Element element;
    private List<Attack> attacks;

    public Kaijumon(String name, Stats stats, Element element, List<Attack> attacks) {
        this.name = name;
        this.stats = stats;
        this.element = element;
        this.attacks = attacks;
    }

    public String getName(){
        return this.name;
    }

    public List<Attack> getAttacksList(){
        return this.attacks;
    }

    public Attack getAttack(int index){
        return attacks.get(index);
    }

    public Stats getStats() {
        return this.stats;
    }

    public Element getElement(){
        return this.element;
    }

    public void takeDamage(int damage) {
        this.stats.hp = Math.max(0, stats.hp - damage);
    }

}
