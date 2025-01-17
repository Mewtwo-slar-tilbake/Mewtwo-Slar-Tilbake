package com.kaijumon.game.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class KaijumonFactoryShould {

    @Test
    public void createRandomKaijumons() {
        KaijumonFactory kaijumonFactory = new KaijumonFactory();
        Kaijumon kaijumon = kaijumonFactory.createKaijumon();

        assertTrue(kaijumon.getStats().speed >= 1 && kaijumon.getStats().speed <= 10);
        assertTrue(kaijumon.getStats().defense > 1 && kaijumon.getStats().defense <= 10);
        assertTrue(kaijumon.getStats().attack > 1 && kaijumon.getStats().attack <= 10);
        assertTrue(kaijumon.getStats().specialAttack > 1 && kaijumon.getStats().specialAttack <= 10);
        assertTrue(kaijumon.getStats().specialDefense > 1 && kaijumon.getStats().specialDefense <= 10);
        assertTrue(kaijumon.getStats().hp == 100);
    }

}
