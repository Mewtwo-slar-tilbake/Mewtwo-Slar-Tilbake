package com.kaijumon.game.model.items;

import com.kaijumon.game.model.entities.Kaijumon;

public class BattlePotion extends Item {
    private final int attackBoost;
    private final int defenseBoost;

    public BattlePotion(String name, String description, int attackBoost, int defenseBoost){
        super(name, description);
        this.attackBoost = attackBoost;
        this.defenseBoost = defenseBoost;
    }

    @Override
    public void use(Kaijumon affectedKaijumon) {
        if (attackBoost > 0)
            affectedKaijumon.setAttack(affectedKaijumon.getStats().attack+attackBoost);
        if (defenseBoost > 0)
            affectedKaijumon.setAttack(affectedKaijumon.getStats().defense + defenseBoost);
    }
}
