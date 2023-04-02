package com.kaijumon.game.dialog;

public class DialogueOption {
    public final String text;
    public final Integer id;

    /**
     * Constructor - This is private because we only create this object by parsing the JSON file with GSON.
     * @param text - The text of the option
     * @param id - The id of the option
     */
    private DialogueOption(String text, Integer id) {
        this.text = text;
        this.id = id;
    }
}
