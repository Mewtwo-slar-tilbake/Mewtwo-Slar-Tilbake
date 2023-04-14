package com.kaijumon.game.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DialogueNode {
    public final String title;
    public String text;
    public final Integer id;
    private NodeType nodeType;

    private List<DialogueOption> options;


    /**
     * Constructor - This is private because we only create this object by parsing the JSON file with GSON.
     * @param title - The title of the dialogue node
     * @param text - The text of the dialogue node
     * @param id - The id of the dialogue node
     */
    private DialogueNode(String title, String text, Integer id) {
        this.title = title;
        this.text = text;
        this.id = id;
        this.options = new ArrayList<>();
    }

    /**
     * Gets the options for this node.
     * @return - The options for this node
     */
    public List<DialogueOption> getOptions() {
        if (options == null) {
            return new ArrayList<>();
        } else {
            return options;
        }
    }

    public void fixParsingMistakes() {
        if (options == null) {
            this.options = new ArrayList<>();
        }
    }

    public NodeType getNodeType(){
        if (this.nodeType != null) {
            return this.nodeType;
        }

        if (options == null || options.size() == 0) {
            this.nodeType = NodeType.END_NODE;
        } else if (options.size() == 1) {
            this.nodeType = NodeType.LINEAR_NODE;
        } else {
            this.nodeType = NodeType.BRANCHING_NODE;
        }
        return this.nodeType;
    }

    public void addNewLineToText() {
        int maxWordsPerLine = 10;

        String[] words = this.text.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            if ((i + 1) % maxWordsPerLine == 0) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }

        this.text = sb.toString();
    }
}
