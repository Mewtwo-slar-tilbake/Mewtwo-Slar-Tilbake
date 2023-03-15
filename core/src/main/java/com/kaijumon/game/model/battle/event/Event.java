package com.kaijumon.game.model.battle.event;

public abstract class Event {

    private IEventHandler handler;

    public void begin(IEventHandler handler) {
        this.handler = handler;
    }

    public abstract void update(float delta);

    public abstract boolean isFinished();

    protected IEventHandler getPlayer() {
        return handler;
    }

}
