package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.model.entities.KaijumonFactory;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KaijumonInfoShould {

    private final KaijumonGame game;

    public KaijumonInfoShould() {
        this.game = new KaijumonGame();
        new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void setHealth() {
        // Arrange & Act
        KaijumonFactory kaijumonFactory = new KaijumonFactory();

        Kaijumon kaijumon = kaijumonFactory.createKaijumon();
        kaijumon.takeDamage(10);
        KaijumonInfo kaijumonInfo = new KaijumonInfo(kaijumon, game.getSkin());

        // Assert
        float kaijumonHealth = kaijumonInfo.getHealthBar().getValue();
        assertEquals(kaijumon.getStats().hp, kaijumonHealth);
    }

}
