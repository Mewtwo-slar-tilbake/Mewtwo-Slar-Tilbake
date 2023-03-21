package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.entities.Kaijumon;

import java.util.ArrayList;
import java.util.List;

public class Trainer {

    private List<Kaijumon> crew;

    public Trainer(Kaijumon kaijumon) {
        crew = new ArrayList<Kaijumon>();
        crew.add(kaijumon);
    }

    public boolean addKaijumon(Kaijumon kaijumon) {
        if (crew.size() >= 6)
            return false;
        crew.add(kaijumon);
        return true;
    }

    public Kaijumon getKaijumon(int index) {
        return crew.get(index);
    }

    public int getCrewSize() {
        return crew.size();
    }
}