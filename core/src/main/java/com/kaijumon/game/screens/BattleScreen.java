package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.screens.ui.BattleButtons;
import com.kaijumon.game.screens.ui.DialogueBox;
import com.kaijumon.game.screens.ui.KaijumonInfo;

public class BattleScreen implements Screen {

    final KaijumonGame game;

    private final Texture kaijumon;
    private final Texture player;
    private final Texture background;

    private Stage stage;
    private TextButton textButton;

    public BattleScreen(final KaijumonGame game) {
        background = new Texture(Gdx.files.internal("background.png"));
        kaijumon = new Texture(Gdx.files.internal("yoda.png"));
        player = new Texture(Gdx.files.internal("yoda.png"));

        this.game = game;
        stage = new Stage();

        final Skin skin = game.skin;

        Table root = new Table();
        root.setFillParent(true);

        Table infoTable = new Table(skin);
        infoTable.setWidth(stage.getWidth());

        KaijumonInfo info = new KaijumonInfo(skin);
        infoTable.add(info).expandX().align(Align.left);
        info = new KaijumonInfo(skin);
        infoTable.add(info).align(Align.right);

        root.add(infoTable).expand().align(Align.top);
        root.row();

        root.add(new DialogueBox("What will Pikachu do?", skin));
        root.add(new BattleButtons(skin)).pad(20);

        stage.addActor(root);
        stage.setDebugAll(true);

        // set the stage as the input processor so it will respond to clicks etc
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        game.batch.begin();

        //game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(kaijumon, Gdx.graphics.getWidth() - 200, 200, 100, 100);
        game.batch.draw(player, 100, 200, 100, 100);

        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
        background.dispose();
        kaijumon.dispose();
        player.dispose();
    }

    private void handleFightButtonEvent() {
        System.out.println("Fight button clicked");
    }

    private void handleBagButtonEvent() {
        System.out.println("Bag button clicked");
    }

    private void handleKijumonButtonEvent(){
        System.out.println("Kaijumon button clicked");
    }

    private void handleRunButtonEvent(){
        System.out.println("Run button clicked");
    }
}
