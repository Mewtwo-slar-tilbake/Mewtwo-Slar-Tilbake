package com.kaijumon.game.dialog;

public class DialogueOption {
    public final String text;
    public final Integer id;
    public final boolean battle;
    public final Integer win; // NB the spotbug tool is wrong. this is not always null. it will be set to a value by gson when it loads the dialogue.json file.
    public final Integer loss; // NB the spotbug tool is wrong. this is not always null. it will be set to a value by gson when it loads the dialogue.json file.

    /**
     * Constructor - This is private because we only create this object by parsing the JSON file with GSON.
     * @param text - The text of the option
     * @param id - The id of the option
     */
    private DialogueOption(String text, Integer id) {
        this.text = text;
        this.id = id;
        this.battle = false;
        this.win = null;
        this.loss = null;
    }
}
