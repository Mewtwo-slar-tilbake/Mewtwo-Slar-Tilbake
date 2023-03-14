package com.kaijumon.game.model.entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KaijumonShould {

    @Test
    public void returnInfo(){
        Stats stat = new Stats();
        List<Attack> attackList = new ArrayList<>();
        attackList.add(Attack.PUNCH);
        Kaijumon kai = new Kaijumon("abc", stat, Element.GHOST, attackList);

        assertEquals("abc", kai.getName());
        assertEquals(stat, kai.getStats());
        assertEquals(Element.GHOST, kai.getElement());
        assertEquals(attackList, kai.getAttacksList());
    }
}
