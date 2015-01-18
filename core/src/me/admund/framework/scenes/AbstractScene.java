package me.admund.framework.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import me.admund.framework.GameConfig;

/**
 * Created by admund on 2014-12-23.
 */
public abstract class AbstractScene implements IScene {
    protected Stage stage = null;
    //protected IGamepadMenuElement firstElement = null; TODO

    public AbstractScene() {
        stage = new Stage(new StretchViewport(GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT));
        setInputProcessor();
    }

    @Override
    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void act(float deltaTime) {
        stage.act(deltaTime);
    }

    @Override
    public void draw(Batch batch) {
        stage.draw();
    }

    @Override
    public void refresh() {
        setInputProcessor();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Camera getCurrentCamera() {
        return stage.getCamera();
    }

    protected void setInputProcessor() {
        InputMultiplexer input = new InputMultiplexer();
        input.addProcessor(stage);
        Gdx.input.setInputProcessor(input);
    }
}
