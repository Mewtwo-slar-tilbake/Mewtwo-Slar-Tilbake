package com.kaijumon.game.model.battle.event;

public class TextEvent extends Event {

    private String text;

    public TextEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}