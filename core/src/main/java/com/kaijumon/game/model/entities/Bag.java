package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Bag implements ITrainer {
    private final List<Kaijumon> kaijumons;
    private final List<Item> items;

    public Bag() {
        this.kaijumons = new ArrayList<>();
        this.items = new ArrayList<>();
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

    public void addItem(Item item) {
        items.add(item);
    }

    public Item takeItem(int index) {
        return items.remove(index);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
}
