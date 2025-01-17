package com.kaijumon.game.model.entities;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class KaijumonFactory {

    public Kaijumon createKaijumon(){

        Species species = Species.randomSpecies();

        return new Kaijumon(species.name(),
                new Stats(100, getRandInt(), getRandInt(), getRandInt(), getRandInt(), getRandInt(),0,1),
                species.element, Arrays.asList(Attack.PUNCH, Attack.SLASH), species);
    }
    public int getRandInt(){
        return ThreadLocalRandom.current().nextInt(1, 10) + 1;
    }

}
