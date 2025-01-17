package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.items.Item;
import com.kaijumon.game.model.items.KaijuBall;

import java.util.ArrayList;
import java.util.List;

public class Bag implements ITrainer {
    private final List<Kaijumon> kaijumons;
    private final List<Item> items;
    private final TrainerType type;

    public Bag() {
        this.kaijumons = new ArrayList<>();
        this.items = new ArrayList<>();
        this.type = TrainerType.Player;
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

    @Override
    public List<Kaijumon> getAliveCrew() {
        List<Kaijumon> aliveCrew = new ArrayList<>();
        for (Kaijumon kaijumon : kaijumons) {
            if (!kaijumon.isFainted())
                aliveCrew.add(kaijumon);
        }
        return aliveCrew;
    }

    public int getCrewSize() {
        return kaijumons.size();
    }

    public Kaijumon getKaijumon(int index) {
        return kaijumons.get(index);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item takeItem(int index) {
        if (items.get(index) instanceof KaijuBall) {
            return items.get(index);
        }
        return items.remove(index);
    }

    @Override
    public TrainerType getType() {
        return type;
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
}
