package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.controller.BattleScreenController;
import com.kaijumon.game.model.battle.Battle;
import com.kaijumon.game.model.battle.BattleState;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.battle.event.IEventHandler;
import com.kaijumon.game.model.entities.Trainer;
import com.kaijumon.game.screens.ui.*;

import java.util.ArrayDeque;
import java.util.Queue;

public class BattleScreen implements Screen, IEventHandler {

    private final BattleScreenController controller;

    final KaijumonGame game;
    private final Battle battle;

    private Event currentEvent;
    private Queue<Event> queue = new ArrayDeque<>();

    private final Texture playerKaijumonTexture;
    private final Texture opponentKaijumonTexture;


    private Stage stage;
    private DialogueBox dialogueBox;
    private ActionSelectBox actionSelectBox;
    private OptionBox optionBox;

    public BattleScreen(final KaijumonGame game, Trainer player, Trainer opponent) {
        this.game = game;
        this.battle = new Battle(player, opponent);

        playerKaijumonTexture = new Texture(Gdx.files.internal("yoda.png"));
        opponentKaijumonTexture = new Texture(Gdx.files.internal("yoda.png"));

        initializeUI();

        this.controller = new BattleScreenController(battle, queue, dialogueBox, actionSelectBox, optionBox);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        game.batch.begin();

        game.batch.draw(playerKaijumonTexture, Gdx.graphics.getWidth() - 200, 200, 100, 100);
        game.batch.draw(opponentKaijumonTexture, 100, 200, 100, 100);

        game.batch.end();

        while (queue.size() > 0) {
            currentEvent = queue.poll();
            currentEvent.begin(this);
        }

        if (battle.getState() == BattleState.READY_TO_PROGRESS)
            controller.newTurn();
        else if (battle.getState() == BattleState.WIN || battle.getState() == BattleState.LOSS || battle.getState() == BattleState.FLEE)
            System.out.println("Battle over");

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        playerKaijumonTexture.dispose();
        opponentKaijumonTexture.dispose();
    }

    public DialogueBox getDialogueBox() {
        return this.dialogueBox;
    }

    @Override
    public HealthBar getHealthBar() {
        return stage.getRoot().findActor(battle.getPlayerKaijumon().getName() + "-health");
    }

    private void initializeUI() {
        stage = new Stage();

        final Skin skin = game.skin;

        Table root = new Table();
        root.setFillParent(true);

        Table topTable = new Table(skin);
        topTable.setWidth(stage.getWidth());

        KaijumonInfo info = new KaijumonInfo(battle.getPlayerKaijumon(), skin);
        topTable.add(info).expandX().align(Align.left);
        info = new KaijumonInfo(battle.getOpponentKaijumon(), skin);
        topTable.add(info).align(Align.right);

        root.add(topTable).expand().align(Align.top);
        root.row();

        dialogueBox = new DialogueBox("What will you do?", skin);
        root.add(dialogueBox);

        actionSelectBox = new ActionSelectBox(skin);
        root.add(actionSelectBox);

        optionBox = new OptionBox(skin);
        root.add(optionBox).align(Align.topLeft);

        stage.addActor(root);
        stage.setDebugAll(true);
    }

}
