package com.kaijumon.game.model.entities;

import java.util.List;

public class Kaijumon {

    private String name;
    private int hp;
    private List<Attack> attacks;
    private int speed;

    private int defense;

    private KaijumonType type;

    public Kaijumon(String name, int hp, List<Attack> attacks, int speed, int defense, KaijumonType type){
        this.name = name;
        this.hp = hp;
        this.attacks = attacks;
        this.speed = speed;
        this.defense = defense;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public int getHP(){
        return this.hp;
    }

    public List<Attack> getAttacksList(){
        return this.attacks;
    }

    public Attack getAttack(int index){
        return attacks.get(index);
    }

    public int getSpeed(){
        return this.speed;
    }

    public int getDefense(){
        return this.defense;
    }

    public KaijumonType getType(){
        return this.type;
    }

    public void takeDamage(int damage) {
        this.hp = Math.max(0, hp - damage);
    }

}
