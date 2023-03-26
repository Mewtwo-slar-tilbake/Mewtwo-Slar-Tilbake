package com.kaijumon.game.model.entities;

import java.util.List;

public interface ITrainer {

    boolean addKaijumon(Kaijumon kaijumon);

    boolean removeKaijumon(Kaijumon kaijumon);

    Kaijumon getKaijumon(int index);

    List<Kaijumon> getCrew();

    int getCrewSize();


}