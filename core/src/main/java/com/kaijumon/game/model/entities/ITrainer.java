package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.items.Item;

import java.util.List;

public interface ITrainer {

    boolean addKaijumon(Kaijumon kaijumon);

    boolean removeKaijumon(Kaijumon kaijumon);

    Kaijumon getKaijumon(int index);

    List<Kaijumon> getCrew();

    List<Kaijumon> getAliveCrew();

    int getCrewSize();

    List<Item> getItems();

    Item takeItem(int index);

    TrainerType getType();


}
