package com.kaijumon.game.model;

import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.entities.*;
import com.kaijumon.game.model.entities.npc.*;
import com.kaijumon.game.screens.BattleScreen;
import com.kaijumon.game.utils.Consts;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Random;

import static com.kaijumon.game.model.Savegame.saveGame;
import static java.util.Arrays.asList;

public class Model implements IModel {

    public final Player player;

    private final KaijumonGame game;
    private final boolean[][] collisionMap;
    private final boolean[][] tallGrassMap;
    private final String mapPath;
    private final List<Npc> npcList = new ArrayList<>();

    public Model(final KaijumonGame game, Player player) {
        this.game = game;
        this.player = player;
        mapPath = Consts.mapPath;
        IMapLoader mapLoader = new LayerMapLoader();
        collisionMap = mapLoader.getLayerData(mapPath, "BLOCK");
        tallGrassMap = mapLoader.getLayerData(mapPath, "tallgrass");
        createNpcs();
        if (player != null && player.getBag().getCrewSize() == 0) {
            player.getBag().addKaijumon(getRandomKaijumon());
        }
    }

    public Model(final KaijumonGame game, Player player, boolean[][] collisionMap, boolean[][] tallGrassMap) {
        this.game = game;
        this.player = player;
        mapPath = Consts.mapPath;
        this.collisionMap = collisionMap;
        this.tallGrassMap = tallGrassMap;
    }

    @Override
    public void movePlayer(Direction delta) {
        Point playerPosition = player.getPosition();
        boolean tileIsBlocked = isTileBlocked(delta.getNewPosition(playerPosition));
        boolean tileContainsNpc = tileContainsNpc(delta.getNewPosition(playerPosition));
        if (tileIsBlocked || tileContainsNpc) {
            player.setDirection(delta);
            return;
        }
        player.move(delta);

        // todo check if player encountered a Kaijumon in tall grass
        boolean playerMoved = !playerPosition.equals(player.getPosition());
        if (playerMoved && isTallGrass(player.getPosition())) {
            Random random = new Random();
            int max = 100;
            int min = 1;
            int chance = random.nextInt(max - min + 1) + min;
            if (chance <= 20) {
                game.setScreen(new BattleScreen(game, player.getBag(), new Trainer(Arrays.asList(getRandomKaijumon())), this));
            }
        }
    }

    /**
     * Check if a given point contains a npc.
     * @param point the point to check
     * @return true if the point contains a npc, false otherwise.
     */
    private boolean tileContainsNpc(Point point) {
        for (Npc npc : npcList) {
            if (npc.getPosition().equals(point)) {
                System.out.println(point + " npc found " + npc.getPosition());
                return true;
            }
        }
        return false;
    }

    public boolean tileContainsCharacter(Point point) {
        return player.getPosition().equals(point) || tileContainsNpc(point);
    }

    /**
     * Check if a given point in the tileMap is blocked.
     * @param point the point to check
     * @return true if the point is blocked, false otherwise.
     */
    public boolean isTileBlocked(Point point) {
        // if outside the map
        if (point.x >= collisionMap.length || point.x < 0)
            return true;
        if (point.y >= collisionMap[0].length || point.y < 0)
            return true;

        // Check if the "BLOCK" layer has a cell at the given position
        return collisionMap[point.x][point.y];

    }

    public Point getMapDimensions(){
        return new Point(collisionMap.length, collisionMap[0].length);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<Npc> getNpcList() {
        return npcList;
    }

    @Override
    public String getTileMapPath() {
        return mapPath;
    }


    @Override
    public void saveModel() {
        saveGame(this.getPlayer(),"core/src/main/java/com/kaijumon/game/savefiles/testing.txt");
    }

    private void createNpcs(){
        NpcFactory npcFactory = new NpcFactory(this);
        npcList.add(npcFactory.getNpc(new ArrayList<>(asList(new Point(5,18))), 2));
        npcList.add(npcFactory.getNpc(new ArrayList<>(asList(new Point(15,26), new Point(9,26), new Point(9,37), new Point(18,37) )), 1));
        npcList.add(npcFactory.getNpc(new Point(5,20), new Point(15,30), 3));
    }

    private boolean isTallGrass(Point point){
        return tallGrassMap[point.x][point.y];
    }

    @Override
    public void playerInteract(){
        Point inFrontOfPlayer = player.getDirection().getNewPosition(player.getPosition());
        for (IInteractable npc : npcList){
            if (npc.getPosition().equals(inFrontOfPlayer)){
                player.interact(npc);
                return;
            }
        }
    }

    private final Kaijumon Charalala = new Kaijumon(
            "Charalala",
            new Stats(100, 10, 10, 5, 5, 10),
            Element.BUG,
            Arrays.asList(Attack.PUNCH, Attack.SLASH),
            Species.CHARALALA
    );

    private final Kaijumon Magdo = new Kaijumon(
            "Magdo",
            new Stats(100, 10, 10, 5, 5, 10),
            Element.ELECTRIC,
            Arrays.asList(Attack.PUNCH, Attack.SLASH),
            Species.MAGDO
    );

    private Kaijumon getRandomKaijumon() {
        Random random = new Random();
        int chance = random.nextInt(0, 2);
        if (chance == 0) {
            return Charalala;
        } else if (chance == 1) {
            return Magdo;
        } else {
            throw new RuntimeException("Random number out of range");
        }
    }

}
