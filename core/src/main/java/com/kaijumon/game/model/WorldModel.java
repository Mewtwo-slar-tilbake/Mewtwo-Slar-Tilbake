package com.kaijumon.game.model;


import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.battle.BattleResultListener;
import com.kaijumon.game.model.entities.*;
import com.kaijumon.game.model.entities.npc.IInteractable;
import com.kaijumon.game.model.entities.npc.Npc;
import com.kaijumon.game.model.entities.npc.NpcFactory;
import com.kaijumon.game.model.map.MapController;
import com.kaijumon.game.screens.BattleScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.kaijumon.game.model.Savegame.saveGame;
import static java.util.Arrays.asList;

public class WorldModel implements IWorldModel {

    public final Player player;

    private final KaijumonGame game;
    private final MapController mapController;
    private final Random random;


    public WorldModel(final KaijumonGame game, Player player) {
        this.game = game;
        this.player = player;
        this.mapController = MapController.getInstance();
        this.random = new Random();
        createNpcs();
        if (player != null && player.getBag().getCrewSize() == 0) {
            player.getBag().addKaijumon(getRandomKaijumon());
        }
    }

    @Override
    public void movePlayer(Direction delta) {
        Point playerPosition = player.getPosition();
        KaijumonFactory kaijumonFactory = new KaijumonFactory();
        boolean tileIsBlocked = mapController.isTileBlocked(delta.getNewPosition(playerPosition));
        boolean tileContainsNpc = tileContainsNpc(delta.getNewPosition(playerPosition));
        if (tileIsBlocked || tileContainsNpc) {
            player.setDirection(delta);
            return;
        }

        // handle player walking into a door
        if (mapController.isDoor(delta.getNewPosition(playerPosition))) {
            Point newPos = delta.getNewPosition(playerPosition);
            player.setPosition(newPos.x, newPos.y);
            mapController.handleDoor(player);
            return;
        }

        // normal player movement
        player.move(delta);

        // todo check if player encountered a Kaijumon in tall grass
        boolean playerMoved = !playerPosition.equals(player.getPosition());
        if (playerMoved && mapController.isTallGrass(player.getPosition())) {
            int max = 100;
            int min = 1;
            int chance = random.nextInt(max - min + 1) + min;
            if (chance <= 20) {
                game.setScreen(new BattleScreen(game, player, new Trainer(Arrays.asList(kaijumonFactory.createKaijumon()), TrainerType.Wild), new BattleResultListener(){}));
            }
        }
    }

    /**
     * Check if a given point contains a npc.
     * @param point the point to check
     * @return true if the point contains a npc, false otherwise.
     */
    private boolean tileContainsNpc(Point point) {
        for (Npc npc : mapController.getNpcList()) {
            if (npc.getPosition().equals(point)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean tileContainsCharacter(Point point) {
        return player.getPosition().equals(point) || tileContainsNpc(point);
    }

    @Override
    public Point getMapDimensions(){
        return mapController.getMapDimensions();
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<Npc> getNpcList() {
        return mapController.getNpcList();
    }

    @Override
    public String getTileMapPath() {
        return mapController.getTileMapPath();
    }


    @Override
    public void saveModel() {
        saveGame(this.getPlayer(),"core/src/main/java/com/kaijumon/game/savefiles/saveFile.txt");
    }

    private void createNpcs(){
        NpcFactory npcFactory = new NpcFactory(this);
        mapController.addNpc(npcFactory.getNpc(new ArrayList<>(asList(new Point(5,18))), 2));
        mapController.addNpc(npcFactory.getNpc(new ArrayList<>(asList(new Point(15,26), new Point(9,26), new Point(9,37), new Point(18,37) )), 1));
        mapController.addNpc(npcFactory.getNpc(new Point(5,20), new Point(15,30), 3));
    }

    @Override
    public void playerInteract(){
        Point inFrontOfPlayer = player.getDirection().getNewPosition(player.getPosition());
        for (IInteractable npc : mapController.getNpcList()){
            if (npc.getPosition().equals(inFrontOfPlayer)){
                player.interact(npc);
                return;
            }
        }
    }

    @Override
    public void printPlayerPosition() {
        Point pos = player.getPosition();
        System.out.println("[x=" + pos.x + ", y=" + pos.y + "]");
    }

    @Override
    public Kaijumon getRandomKaijumon() { //TODO this needs to use a kaijumon factory
        int chance = random.nextInt(0, 2);
        if (chance == 0) {
            return new Kaijumon(
                    "Charalala",
                    new Stats(100, 10, 10, 5, 5, 10,0,1),
                    Element.BUG,
                    Arrays.asList(Attack.PUNCH, Attack.SLASH),
                    Species.CHARALALA
            );
        } else if (chance == 1) {
            return new Kaijumon(
                    "Magdo",
                    new Stats(100, 10, 10, 5, 5, 10,0,1),
                    Element.ELECTRIC,
                    Arrays.asList(Attack.PUNCH, Attack.SLASH),
                    Species.MAGDO
            );
        } else {
            throw new RuntimeException("Random number out of range");
        }
    }

    @Override
    public boolean isTileBlocked(Point point) {
        return mapController.isTileBlocked(point);
    }
}
