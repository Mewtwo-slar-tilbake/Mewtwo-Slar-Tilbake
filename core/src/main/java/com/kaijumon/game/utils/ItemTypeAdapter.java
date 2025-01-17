package com.kaijumon.game.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.kaijumon.game.model.items.Item;

import java.io.IOException;

public class ItemTypeAdapter extends TypeAdapter<Item> {

    public ItemTypeAdapter(Gson gson) {
    }

    @Override
    public void write(JsonWriter out, Item item) throws IOException {
        out.beginObject();
        out.name("type").value(item.getClass().getName());
        out.name("data");
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(item);
        out.jsonValue(element.toString());
        out.endObject();
    }

    @Override
    public Item read(JsonReader in) throws IOException {
        in.beginObject();
        in.nextName(); // Discard the "type" field name
        String typeName = in.nextString();
        in.nextName(); // Discard the "data" field name
        Gson gson = new Gson();
        Item item = gson.fromJson(in, getClassForName(typeName));
        in.endObject();
        return item;
    }

    private Class<?> getClassForName(String typeName) {
        try {
            return Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
