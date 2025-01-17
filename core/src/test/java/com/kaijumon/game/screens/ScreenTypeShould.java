package com.kaijumon.game.screens;

import org.junit.jupiter.api.Test;

public class ScreenTypeShould {

    @Test
    public void retainOrdinals() {
        assert(ScreenType.MainMenu.ordinal() == 0);
        assert(ScreenType.Battle.ordinal() == 1);
        assert(ScreenType.World.ordinal() == 2);
        assert(ScreenType.Help.ordinal() == 3);
        assert(ScreenType.Pause.ordinal() == 4);
    }

}
