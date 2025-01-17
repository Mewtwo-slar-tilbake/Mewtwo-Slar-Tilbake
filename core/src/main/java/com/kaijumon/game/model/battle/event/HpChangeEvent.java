package com.kaijumon.game.model.battle.event;

import com.kaijumon.game.model.battle.BattleParty;

public class HpChangeEvent extends Event {

    private BattleParty party;
    private int hp;

    public HpChangeEvent(BattleParty party, int hp) {
        this.party = party;
        this.hp = hp;
    }

    public BattleParty getParty() {
        return party;
    }

    public int getHp() {
        return hp;
    }

}
