package com.kaijumon.game.model.items;

import com.kaijumon.game.model.entities.IStatBoost;

public class BattlePotion extends Item {
    private final int attackBoost;
    private final int defenseBoost;

    public BattlePotion(String name, String description, int attackBoost, int defenseBoost){
        super(name, description);
        this.attackBoost = attackBoost;
        this.defenseBoost = defenseBoost;
    }

    @Override
    public void use(IStatBoost stats) {
        if (attackBoost > 0)
            stats.increaseAttack(attackBoost);
        if (defenseBoost > 0)
            stats.increaseDefense(defenseBoost);
    }
}
