package com.kaijumon.game.model.battle.event;

import com.kaijumon.game.screens.ui.HealthBar;

public class HpChangeEvent extends Event {

    private HealthBar healthBar;
    private int newHp;

    public HpChangeEvent(int newHp) {
        this.newHp = newHp;
    }

    @Override
    public void begin(IEventHandler handler) {
        super.begin(handler);
        healthBar = handler.getHealthBar();
        healthBar.setValue(newHp);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public boolean isFinished() {
        return healthBar.getValue() == newHp;
    }
}
