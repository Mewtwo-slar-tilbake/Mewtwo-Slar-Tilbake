package com.kaijumon.game.model.entities;

public class Attack {

    private String attackName;
    private int damage;


    public Attack(String attackName, int damage){
        this.attackName = attackName;
        this.damage = damage;
    }
    public String getAttackName(){
        return this.attackName;
    }
    public int getDamage(){
        return this.damage;
    }
}
