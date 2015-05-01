package me.admund.framework.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import me.admund.framework.GameConfig;

/**
 * Created by admund on 2014-12-23.
 */
public abstract class AbstractScene implements IScene {
    protected Stage stage = null;
    protected Stage guiStage = null;
    //protected IGamepadMenuElement firstElement = null; TODO

    public AbstractScene() {
        stage = new Stage(new ExtendViewport(GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT));
        guiStage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        setInputProcessor();
    }

    @Override
    public void resize (int width, int height) {
        stage.getViewport().update(width, height, false);
        guiStage.getViewport().update(width, height, false);
    }

    @Override
    public void act(float deltaTime) {
        stage.act(deltaTime);
        guiStage.act(deltaTime);
    }

    @Override
    public void draw(Batch batch) {
        stage.draw();
        guiStage.draw();
    }

    @Override
    public void refresh() {
        setInputProcessor();
    }

    @Override
    public void dispose() {
        stage.dispose();
        guiStage.dispose();
    }

    public Camera getCurrentCamera() {
        return stage.getCamera();
    }

    protected void setInputProcessor() {
        InputMultiplexer input = new InputMultiplexer();
        input.addProcessor(guiStage);
        input.addProcessor(stage);
        Gdx.input.setInputProcessor(input);
    }
}
