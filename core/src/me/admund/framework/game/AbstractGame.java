package me.admund.framework.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.admund.framework.scenes.ScenesManager;

/**
 * Created by admund on 2014-12-23.
 */
public abstract class AbstractGame extends ApplicationAdapter {
    private SpriteBatch batch = null;

    protected Color clearColor = Color.BLACK;

    @Override
    public void create () {
        batch = new SpriteBatch();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ScenesManager.inst().peek().draw(batch);
    }

    @Override
    public void resize(int width, int height) {
        ScenesManager.inst().peek().resize(width, height);
    }
}
