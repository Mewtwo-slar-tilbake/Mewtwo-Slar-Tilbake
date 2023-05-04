package com.kaijumon.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.battle.BattleModel;
import com.kaijumon.game.model.battle.BattleParty;
import com.kaijumon.game.screens.ui.ActionSelectBox;
import com.kaijumon.game.screens.ui.KaijumonInfo;
import com.kaijumon.game.screens.ui.OptionBox;
import com.kaijumon.game.utils.Consts;
import com.kaijumon.game.utils.KaijumonTextureMap;

/**
 * Represents the view logic for the battle screen.
 */
public class BattleView {

    private final KaijumonGame game;
    private final BattleModel model;

    private Texture playerTexture;
    private final Texture opponentTexture;

    private OrthographicCamera camera;
    private Stage stage;
    private Label label;
    private ActionSelectBox actionSelectBox;
    private OptionBox optionBox;
    private KaijumonInfo playerInfo;
    private KaijumonInfo opponentInfo;


    public BattleView(final KaijumonGame game, BattleModel model) {
        this.game = game;
        this.model = model;

        playerTexture = KaijumonTextureMap.getTexture(
                model.getPlayerKaijumon().getSpecies()
        );
        opponentTexture = KaijumonTextureMap.getTexture(
                model.getOpponentKaijumon().getSpecies()
        );

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        initializeUI();
    }

    /**
     * Initialize the UI elements for the battle screen.
     */
    public void render(float delta) {
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        ScreenUtils.clear(Color.WHITE);

        game.getBatch().begin();
        game.getBatch().draw(playerTexture, 100, 200, 100, 100);
        game.getBatch().draw(opponentTexture, Gdx.graphics.getWidth() - 200, 200, 100, 100);
        game.getBatch().end();

        stage.draw();
        stage.act(delta);
    }

    /**
     * Get the stage for the battle screen.
     * @return the stage for the battle screen.
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Get the info of the Kaijumon for the given party.
     * @param party the party to get the Kaijumon info for.
     * @return the info of the Kaijumon for the given party.
     */
    public KaijumonInfo getKaijumonInfo(BattleParty party) {
        return party == BattleParty.PLAYER
                ? playerInfo
                : opponentInfo;
    }

    /**
     * Get the stage for the battle screen.
     * @return  the stage for the battle screen.
     */
    public ActionSelectBox getActionSelectBox() {
        return actionSelectBox;
    }

    /**
     * Gets the option box for the battle screen.
     * @return the option box for the battle screen.
     */
    public OptionBox getOptionBox() {
        return optionBox;
    }

    /**
     * Resize the battle screen.
     * @param width the new width.
     * @param height the new height.
     */
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Dispose of the battle screen.
     */
    public void dispose() {
        stage.dispose();
        playerTexture.dispose();
        opponentTexture.dispose();
    }

    private void initializeUI() {
        stage = new Stage();

        Table root = new Table();
        root.setFillParent(true);

        Table topTable = new Table(game.getSkin());
        topTable.setWidth(stage.getWidth());
        Table topTableTest = new Table(game.getSkin());
        topTableTest.setWidth(stage.getWidth());

        playerInfo = new KaijumonInfo(model.getPlayerKaijumon(), game.getSkin());
        topTable.add(playerInfo).expandX().align(Align.left);
        //topTableTest.add(playerInfo).expandX().align(Align.left);
        opponentInfo = new KaijumonInfo(model.getOpponentKaijumon(), game.getSkin());
        topTable.add(opponentInfo).align(Align.right);
        //topTableTest.add(opponentInfo).align(Align.right);

        root.add(topTable).expand().align(Align.top);
        root.row();

        label = new Label("What will you do?", game.getSkin());
        root.add(label);

        actionSelectBox = new ActionSelectBox(game.getSkin());
        root.add(actionSelectBox).align(Align.topLeft);

        optionBox = new OptionBox(game.getSkin());
        root.add(optionBox).align(Align.topLeft);

        stage.addActor(root);
        stage.setDebugAll(Consts.DEBUG);
    }

    /**
     * Set the texture for the player.
     * @param texture the texture for the player.
     */
    public void setPlayerTexture(Texture texture) {
        this.playerTexture = texture;
    }

    /**
     * Set the Kaijumon info for the player.
     * @param kaijumonInfo the Kaijumon info for the player.
     */
    public void setKaijumonInfo(KaijumonInfo kaijumonInfo){
        this.playerInfo = kaijumonInfo;
    }
}
