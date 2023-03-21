package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.entities.Kaijumon;

import java.util.ArrayList;
import java.util.List;

public class Trainer implements ITrainer {

    private List<Kaijumon> crew;

    public Trainer(List<Kaijumon> kaijumon) {
        crew = new ArrayList<Kaijumon>(kaijumon);
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
}
