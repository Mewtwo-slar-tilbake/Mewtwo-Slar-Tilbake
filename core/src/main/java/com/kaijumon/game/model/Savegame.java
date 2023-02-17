package com.kaijumon.game.model;

import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.controller.Controller;
import com.kaijumon.game.controller.IController;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.screens.GameScreen;
import com.kaijumon.game.view.IView;
import com.kaijumon.game.view.View;

import java.io.*;

public class Savegame {

    Player player;
    KaijumonGame game;
    public Savegame(Player player) {
        this.player = player;
    }
    public Savegame(KaijumonGame game) {
        this.game = game;
    }

    public void saveGameState(){
        try (FileOutputStream saveFile = new FileOutputStream("core/src/main/java/com/kaijumon/game/savefiles/testing.txt")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(saveFile);
            objectOutputStream.writeObject(player);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Successfully saved game state");
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void loadGameState(){
        try (FileInputStream loadFile = new FileInputStream("core/src/main/java/com/kaijumon/game/savefiles/testing.txt")) {
            ObjectInputStream objectInputStream = new ObjectInputStream(loadFile);
            Player loadedTestPlayer = (Player) objectInputStream.readObject();
            objectInputStream.close();

            IModel model = new Model(loadedTestPlayer);
            IView view = new View(game, model);
            IController controller = new Controller(game, model);

            game.setScreen(new GameScreen(game, view, controller));
            //dispose();
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
