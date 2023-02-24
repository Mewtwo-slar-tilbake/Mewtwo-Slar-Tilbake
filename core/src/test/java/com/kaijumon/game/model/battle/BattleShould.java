package com.kaijumon.game.model.battle;

import com.kaijumon.game.model.entities.Attack;
import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.model.entities.KaijumonType;
import com.kaijumon.game.model.entities.Player;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleShould {

    @Test
    public void damageOpponent(){
        // Arrange
        List<Kaijumon> kaijumons = Arrays.asList(
                new Kaijumon(
                        "k1",
                        100,
                        Arrays.asList(new Attack("test", 100, KaijumonType.ELECTRIC)),
                        10,
                        10,
                        KaijumonType.ELECTRIC
                )
        );

        Player p1 = new Player(0,0, kaijumons);
        Player p2 = new Player(0,0, kaijumons);
        Battle battle = new Battle(p1, p2);

        // Act
        battle.performMoves(
                p1.getKaijumonBag().get(0).getAttack(0),
                p2.getKaijumonBag().get(0).getAttack(0));

        // Assert
        assertEquals(90, p1.getKaijumonBag().get(0).getHP());
    }

    @Test
    public void considerSpeed(){
        // Arrange
        List<Kaijumon> playerKaijumons = Arrays.asList(
                new Kaijumon(
                        "k1",
                        100,
                        Arrays.asList(new Attack("test", 100, KaijumonType.WATER)),
                        20,
                        0,
                        KaijumonType.WATER
                )
        );

        List<Kaijumon> opponentKaijumons = Arrays.asList(
                new Kaijumon(
                        "k1",
                        100,
                        Arrays.asList(new Attack("test", 100, KaijumonType.ELECTRIC)),
                        50,
                        0,
                        KaijumonType.ELECTRIC
                )
        );

        Player p1 = new Player(0,0, playerKaijumons);
        Player p2 = new Player(0,0, opponentKaijumons);
        Battle battle = new Battle(p1, p2);

        // Act
        battle.performMoves(
                p1.getKaijumonBag().get(0).getAttack(0),
                p2.getKaijumonBag().get(0).getAttack(0)
        );

        // Assert
        assertEquals(0, p1.getKaijumonBag().get(0).getHP());
        assertEquals(100, p2.getKaijumonBag().get(0).getHP());
    }

    @Test
    public void considerKaijumonType() {
        // Arrange
        List<Kaijumon> playerKaijumons = Arrays.asList(
                new Kaijumon(
                        "k1",
                        100,
                        Arrays.asList(new Attack("test", 20, KaijumonType.WATER)),
                        10,
                        0,
                        KaijumonType.WATER
                )
        );

        List<Kaijumon> opponentKaijumons = Arrays.asList(
                new Kaijumon(
                        "k1",
                        100,
                        Arrays.asList(new Attack("test", 20, KaijumonType.ELECTRIC)),
                        0,
                        0,
                        KaijumonType.ELECTRIC
                )
        );

        Player p1 = new Player(0,0, playerKaijumons);
        Player p2 = new Player(0,0, opponentKaijumons);
        Battle battle = new Battle(p1, p2);

        // Act
        battle.performMoves(
                p1.getKaijumonBag().get(0).getAttack(0),
                p2.getKaijumonBag().get(0).getAttack(0)
        );

        // Assert
        assertEquals(60, p1.getKaijumonBag().get(0).getHP());
        assertEquals(80, p2.getKaijumonBag().get(0).getHP());
    }
}