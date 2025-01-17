package com.kaijumon.game.model.items;

import com.kaijumon.game.model.entities.ITrainer;
import com.kaijumon.game.model.entities.Kaijumon;

public class KaijuBall extends Item{
    public KaijuBall() {
        super("KaijuBall", "A ball that can be used to capture wild Kaijumon");
    }


    /**
     * Capture a wild Kaijumon.
     * @param target
     * @param playerBag
     */
    public void capture(Kaijumon target, ITrainer playerBag) {
        Kaijumon targetClone = target.clone();
        targetClone.setHp(100);
        playerBag.addKaijumon(targetClone);
    }

    @Override
    public void use(Kaijumon stats) {
        throw new IllegalStateException("kaijuball can't be used on kaijumon. use the KaijuBall.capture() instead.");
    }
}
