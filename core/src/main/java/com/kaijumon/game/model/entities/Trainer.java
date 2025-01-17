package com.kaijumon.game.model.entities;

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

    /**
     * Add a Kaijumon to the crew.
     * @param kaijumon
     * @return true if the Kaijumon was added, false if the crew is full.
     */
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

    /**
     * Get a Kaijumon from the crew.
     * @param index
     * @return the Kaijumon at the index.
     */
    public Kaijumon getKaijumon(int index) {
        return crew.get(index);
    }

    /**
     * Get the crew.
     * @return the crew.
     */
    public List<Kaijumon> getCrew(){
        return crew;
    }

    @Override
    public List<Kaijumon> getAliveCrew() {
        List<Kaijumon> aliveCrew = new ArrayList<>();
        for (Kaijumon kaijumon : crew) {
            if (!kaijumon.isFainted())
                aliveCrew.add(kaijumon);
        }
        return aliveCrew;
    }


    /**
     * Get the size of the crew.
     * @return the size of the crew.
     */
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
