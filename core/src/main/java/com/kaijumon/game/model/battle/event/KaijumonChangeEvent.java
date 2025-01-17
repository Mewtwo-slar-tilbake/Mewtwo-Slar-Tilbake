package com.kaijumon.game.model.battle.event;

import com.kaijumon.game.model.battle.BattleParty;
import com.kaijumon.game.model.entities.Kaijumon;

public class KaijumonChangeEvent extends Event {

    private BattleParty party;
    private Kaijumon kaijumon;

    public KaijumonChangeEvent(BattleParty party, Kaijumon kaijumon) {
        this.party = party;
        this.kaijumon = kaijumon;
    }

    public BattleParty getParty() {
        return party;
    }

    public Kaijumon getKaijumon() {
        return kaijumon;
    }

}
