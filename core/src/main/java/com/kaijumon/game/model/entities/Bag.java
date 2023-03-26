package com.kaijumon.game.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Bag implements ITrainer {
    private final List<Kaijumon> kaijumons;

    public Bag() {
        this.kaijumons = new ArrayList<>();
    }

    public boolean addKaijumon(Kaijumon kaijumon) {
        return kaijumons.add(kaijumon);
    }

    public boolean removeKaijumon(Kaijumon kaijumon){
        return kaijumons.remove(kaijumon);
    }

    public List<Kaijumon> getCrew(){
        return new ArrayList<>(kaijumons);
    }

    public int getCrewSize() {
        return kaijumons.size();
    }

    public Kaijumon getKaijumon(int index) {
        return kaijumons.get(index);
    }
}