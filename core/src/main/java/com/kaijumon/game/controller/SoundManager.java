package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.screens.ScreenType;

public class SoundManager {

        private KaijumonGame game;
        private static SoundManager instance = null;
        private final Music rainMusic;

        ScreenType currentScreenType;

        private SoundManager() {
            rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
            update();
        }

        public static SoundManager getInstance() {
            if (instance == null) {
                instance = new SoundManager();
            }
            return instance;
        }

        private void updateBackgroundMusic(ScreenType screenType) {
            if (screenType == ScreenType.World){
                rainMusic.setLooping(true);
                rainMusic.play();
            }
            else {
                rainMusic.stop();
            }
        }

        public void update(){
            if (game == null){
                return;
            }
            ScreenType screenType = game.getScreenType();
            if (screenType == currentScreenType){
                return;

            }
            currentScreenType = screenType;
            updateBackgroundMusic(screenType);
        }

        public void playSound(String sound) {
            // TODO implement sound manager
        }
        public void setGame(KaijumonGame game) {
            this.game = game;
        }
}
