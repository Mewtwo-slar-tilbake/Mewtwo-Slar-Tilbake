package com.kaijumon.game.controller;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class SoundManagerShould {

    @Test
    public void haveOneInstance() {
        Gdx.files = new HeadlessFiles();
        Gdx.audio = mock(Audio.class);

        SoundManager first = SoundManager.getInstance();
        SoundManager second = SoundManager.getInstance();
        assert(first.equals(second));
    }

}
