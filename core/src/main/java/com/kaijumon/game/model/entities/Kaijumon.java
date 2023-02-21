package com.kaijumon.game.model.entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Kaijumon {

    private String name;
    private final int hp;
    private ArrayList<Attack> attacks;
    private int speed;

    private int defense;

    private KaijumonType type;

    private final Texture picture;

    public Kaijumon(String name, int hp, ArrayList<Attack> attacks, int speed, int defense, KaijumonType type, Texture picture){
        this.name = name;
        this.hp = hp;
        this.attacks = attacks;
        this.speed = speed;
        this.defense = defense;
        this.type = type;
        this.picture = picture;
    }

    public String getName(){
        return this.name;
    }
    public int getHP(){
        return this.hp;
    }
    public ArrayList<Attack> getAttacksList(){
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
    public Texture getPicture(){
        return this.picture;
    }
}
