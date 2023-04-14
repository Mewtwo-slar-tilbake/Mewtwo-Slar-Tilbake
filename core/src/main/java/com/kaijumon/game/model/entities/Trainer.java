package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.model.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Trainer implements ITrainer {

    private List<Kaijumon> crew;
    private final TrainerType type;

    public Trainer(List<Kaijumon> kaijumon, TrainerType type) {
        crew = new ArrayList<Kaijumon>(kaijumon);
        this.type = type;
    }

    public boolean addKaijumon(Kaijumon kaijumon) {
        if (crew.size() >= 6)
            return false;
        crew.add(kaijumon);
        return true;
    }

    @Override
    public boolean removeKaijumon(Kaijumon kaijumon) {
        return false;
    }

    public Kaijumon getKaijumon(int index) {
        return crew.get(index);
    }

    public List<Kaijumon> getCrew(){
        return crew;
    }



    public int getCrewSize() {
        return crew.size();
    }

    @Override
    public List<Item> getItems() {
        return new ArrayList<>();
    }

    @Override
    public Item takeItem(int index) {
        return null; // TODO maybe not needed
    }

    @Override
    public TrainerType getType() {
        return type;
    }

}
