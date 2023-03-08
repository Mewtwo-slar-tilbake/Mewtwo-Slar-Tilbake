package com.kaijumon.game.model.battle;

import com.kaijumon.game.battle.Action;
import com.kaijumon.game.battle.Battle;
import com.kaijumon.game.battle.Trainer;
import com.kaijumon.game.model.entities.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleShould {

    @Test
    public void damageOpponent(){
        // Arrange
        Kaijumon test = new Kaijumon(
                "Test",
                new Stats(100, 10, 10, 5, 5, 10),
                Element.BUG,
                Arrays.asList(Attack.PUNCH, Attack.SLASH)
        );

        Trainer t1 = new Trainer(test);
        Trainer t2 = new Trainer(test);

        Battle battle = new Battle(t1, t2);

        // Act
        battle.progress(Action.FIGHT, 0, Action.FIGHT, 1);

        battle.performMoves(
                p1.getKaijumonBag().get(0).getAttack(0),
                p2.getKaijumonBag().get(0).getAttack(0));

        // Assert
        assertEquals(90, t1.getPokemon());
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
