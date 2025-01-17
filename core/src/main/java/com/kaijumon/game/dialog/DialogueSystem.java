package com.kaijumon.game.dialog;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.utils.Consts;

import java.io.FileReader;
import java.util.*;

public class DialogueSystem {
    private static DialogueSystem instance;
    private final HashMap<Integer, DialogueNode> nodes;
    private final List<DialogueTraverser> traversers;
    private final List<DialogueTraverser> traversersToRemove;
    private static KaijumonGame game;
    private static boolean wasInitiated = false;


    /**
     * Returns the instance of the DialogueSystem.
     * if the DialogueSystem was not initialized, an IllegalStateException is thrown.
     * @return - The instance of the DialogueSystem
     */
    public static DialogueSystem getInstance() {
        if (!wasInitiated)
            throw new IllegalStateException("DialogueSystem: init() was not called before getInstance()");


        if (DialogueSystem.instance == null) {
            DialogueSystem.instance = new DialogueSystem();
        }
        return instance;
    }

    /**
     * Initializes the DialogueSystem.
     * this method needs to be called. by calling it you load the dialogue nodes from the json file, and pass the game instance to the DialogueSystem.
     */
    public static void init(KaijumonGame game) {
        wasInitiated = true;
        DialogueSystem.game = game;
        DialogueSystem.getInstance();
    }

    /**
     * Creates a new DialogueSystem and loads the nodes from the json file.
     * the constructor is private because this class is a singleton.
     * Use the getInstance() method to get the instance.
     */
    private DialogueSystem() {
        nodes = new HashMap<>();
        loadFromJson();
        validate();
        traversers = new ArrayList<>();
        traversersToRemove = new ArrayList<>();
    }

    /**
     * returns a DialogueNode by its id.
     * @param id - the id of the node
     * @return - the DialogueNode with the given id
     */
    protected DialogueNode getNode(Integer id) {
        return nodes.get(id);
    }

    /**
     * Adds a node to the nodes HashMap.
     */
    private void addNode(DialogueNode node) {
        nodes.put(node.id, node);
    }

    /**
     * Loads the dialogueNodes from a json file and adds them to the nodes HashMap.
     */
    private void loadFromJson() {
        try {
            Gson gson = new Gson();
            FileReader file = new FileReader(Consts.dialogPath);
            JsonReader reader = new JsonReader(file);
            JsonArray jsonList = gson.fromJson(reader, JsonArray.class);
            HashSet<Integer> ids = new HashSet<>();

            for (int i = 0; i < jsonList.size(); i++) {
                JsonObject object = jsonList.get(i).getAsJsonObject();
                DialogueNode node = gson.fromJson(object, DialogueNode.class);
                node.addNewLineToText();
                node.fixParsingMistakes();
                addNode(node);
                if (ids.contains(node.id)) {
                    throw new IllegalStateException("DialogueSystem: Node " + node.id + " is defined more than once");
                }
                ids.add(node.id);
            }
        } catch (Exception e) {
            throw new IllegalStateException("DialogueSystem: Could not load file " + Consts.dialogPath + " [ERROR MSG]: " + e.getMessage());
        }
    }

    /**
     * Validates all the nodes, by checking that they have all the mandatory fields, and by checking that all the options point to legal Dialogue nodes if there are any options.
     */
    private void validate() {
        for (DialogueNode node : nodes.values()) {
            if (node.id == null) {
                throw new IllegalStateException("DialogueSystem: one of the node is missing an id");
            }
            if (node.title == null) {
                throw new IllegalStateException("DialogueSystem: Node " + node.id + " has no title");
            }
            if (node.text == null) {
                throw new IllegalStateException("DialogueSystem: Node " + node.id + " has no text");
            }

            int i = 0;
            for (DialogueOption option : node.getOptions()) {
                i++;
                if (!nodes.containsKey(option.id)) {
                    throw new IllegalStateException("DialogueSystem: Node " + node.id + " has an option (" + i + ") that points to a non-existent node " + option.id);
                }
                if (Objects.equals(node.id, option.id)) {
                    throw new IllegalStateException("DialogueSystem: Node " + node.id + " has an option (" + i + ") that points to itself");
                }
            }
        }
    }

    /**
     * sends an input key code to all the active dialogues.
     * @param keyCode the integer value of the kye pressed.
     */
    public void publishInputEvent(int keyCode) {
        for (DialogueTraverser subscriber : traversers) {
            subscriber.handleINputEvent(keyCode);
        }
        traversers.removeAll(traversersToRemove);
        traversersToRemove.clear();
    }

    /**
     * subscribes a DialogueTraverser to the DialogueSystem, so it can get any keys pressed by the player.
     * @param subscriber - the DialogueTraverser to subscribe.
     */
    protected void registerEventSubscriber(DialogueTraverser subscriber) {
        traversers.add(subscriber);
    }

    /**
     * unsubscribes a DialogueTraverser from the DialogueSystem.
     * @param subscriber - the DialogueTraverser to unsubscribe.
     */
    protected void unregisterEventSubscriber(DialogueTraverser subscriber) {
        traversersToRemove.add(subscriber);
    }

    /**
     * checks if there are any active dialogues.
     * @return - true if there are active dialogues, false otherwise.
     */
    public boolean hasActiveDialogues() {
        return !traversers.isEmpty();
    }

    /**
     * method that renders all the active dialogues.
     * this method also passes the game instance to the DialogueTraverser, so it can render the dialogues.
     */
    public void render(){
        for (DialogueTraverser traverser : traversers) {
            traverser.render(game);
        }
    }

}
