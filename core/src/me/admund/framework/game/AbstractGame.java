package me.admund.framework.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.admund.framework.GameUtils;
import me.admund.framework.achievements.IAchievementsProvider;
import me.admund.framework.scenes.IScene;
import me.admund.framework.scenes.LoadingScene;
import me.admund.framework.scenes.ScenesManager;

/**
 * Created by admund on 2014-12-23.
 */
public abstract class AbstractGame extends ApplicationAdapter {
    private SpriteBatch batch = null;
    private boolean loadingAssets = true;

    protected Color clearColor = Color.BLACK;

    protected IAchievementsProvider achievementsProvider = null;

    public AbstractGame(IAchievementsProvider achievementsProvider) {
        this.achievementsProvider = achievementsProvider;
    }

    public abstract void load();

    @Override
    public void create () {
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);

        load();
        ScenesManager.inst().push(getLoadingScene(), true);
        batch = new SpriteBatch();
    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ScenesManager.inst().peek().act(Math.min(Gdx.graphics.getDeltaTime(), 1f/30f));
        ScenesManager.inst().peek().draw(batch);

        if(loadingAssets && GameUtils.assetsManager.update()) {
            loadingAssets = false;
            GameUtils.assetsManager.init();
            ScenesManager.inst().push(getFirstScene(), true);
        }
        Gdx.gl.glFlush();
    }

    @Override
    public void resize(int width, int height) {
        ScenesManager.inst().peek().resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        ScenesManager.inst().dispose();
        GameUtils.dispose();
    }

    protected abstract IScene getFirstScene();
    protected IScene getLoadingScene() {
        return new LoadingScene();
    }
}
