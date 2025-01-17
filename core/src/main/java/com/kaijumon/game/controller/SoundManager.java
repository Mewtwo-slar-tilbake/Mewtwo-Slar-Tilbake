package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.screens.ScreenType;
import com.kaijumon.game.utils.Consts;

public class SoundManager {

        private Player player;
        private KaijumonGame game;
        private static SoundManager instance = null;
        private final Music worldMusic;

        private final Music battleMusic;

        private final Music mainMenuMusic;

        private final Music walkingSound;

        ScreenType currentScreenType;

        private SoundManager() {
            String basePath = "assets/";
            worldMusic = Gdx.audio.newMusic(Gdx.files.internal(basePath + "September (Master).mp3"));
            battleMusic = Gdx.audio.newMusic(Gdx.files.internal(basePath + "Dream Raid Part I.mp3"));
            mainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal(basePath + "awesomeness.wav"));
            walkingSound = Gdx.audio.newMusic(Gdx.files.internal(basePath + "stepwood_2.wav"));
            update();
        }


        /**

         Returns an instance of the SoundManager class. If no instance exists, a new instance is created and returned.
         @return An instance of the SoundManager class.
         */
        public static SoundManager getInstance() {
            if (instance == null) {
                instance = new SoundManager();
            }
            return instance;
        }

    private void updateBackgroundMusic(ScreenType screenType) {
            battleMusic(screenType);
            worldMusic(screenType);
            mainMenuMusic(screenType);
        }
        private void battleMusic(ScreenType screenType){
            if (screenType == ScreenType.Battle){
                battleMusic.setLooping(true);
                battleMusic.play();
            }
            else {
                battleMusic.stop();
            }
        }

        private void worldMusic(ScreenType screenType){
            if (screenType == ScreenType.World){
                worldMusic.setLooping(true);
                worldMusic.play();
            }
            else {
                worldMusic.stop();
            }

        }

        private void mainMenuMusic(ScreenType screenType){
            if (screenType == ScreenType.MainMenu){
                mainMenuMusic.setLooping(true);
                mainMenuMusic.play();
            }
            else {
                mainMenuMusic.stop();
            }
        }

        /**
         *
         * Updates the sound effects and background music based on the current screen type of the game.
         * If there is no game instance, the method returns without doing anything.
         */
        public void update(){
            if (game == null){
                return;
            }
            ScreenType screenType = game.getScreenType();
            playWalk(screenType);
            if (screenType == currentScreenType){
                return;

            }
            currentScreenType = screenType;
            updateBackgroundMusic(screenType);
        }

        private void playWalk(ScreenType screenType){
            if (screenType != ScreenType.World){
                walkingSound.stop();
                return;
            }

            if (player == null){
                return;
            }
            if (player.getTimeSinceLastMove() < Consts.timeBetweenMovesMillis + 10){
                walkingSound.setLooping(true);
                walkingSound.play();
            }
            else {
                walkingSound.stop();
            }
        }


        /**
         *
         * Sets the game instance to be used by the SoundManager.
         * @param game The KaijumonGame instance to be set.
         */
        public void setGame(KaijumonGame game) {
            this.game = game;
        }

    /**
     * Sets the Player instance to be used by the object.
     * @param player The Player instance to be set.
     */
    public void setPlayer(Player player) {
            this.player = player;
        }

        /**
         * Disposes of all the music and sound effects.
         */
    public void dispose() {
            mainMenuMusic.dispose();
            battleMusic.dispose();
            worldMusic.dispose();
            walkingSound.dispose();
    }
}
