package com.kaijumon.game.model.entities;

public interface IStatBoost {
    void increaseHp(int healthBoost);
    void increaseAttack(int attackBoost);
    void increaseDefense(int defenseBoost);
    void increaseSpeed(int speedBoost);
}