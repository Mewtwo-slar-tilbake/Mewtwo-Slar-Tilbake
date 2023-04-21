package com.kaijumon.game.model.entities;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class KaijumonFactory {

    public Kaijumon createKaijumon(){

        Species species = Species.randomSpecies();
        int randomNumber =  ThreadLocalRandom.current().nextInt(1, 10) + 1;

        return new Kaijumon(species.name(),
                new Stats(100, randomNumber, randomNumber, randomNumber, randomNumber, randomNumber,0,1),
                species.element, Arrays.asList(Attack.PUNCH, Attack.SLASH), species);
    }
}