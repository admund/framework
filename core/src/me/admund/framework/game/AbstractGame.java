package me.admund.framework.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import me.admund.framework.GameUtils;
import me.admund.framework.achievements.IAchievementsProvider;
import me.admund.framework.assets.FrameworkAssetsManager;
import me.admund.framework.scenes.IScene;
import me.admund.framework.scenes.LoadingScene;
import me.admund.framework.scenes.ScenesManager;

/**
 * Created by admund on 2014-12-23.
 */
public abstract class AbstractGame extends ApplicationAdapter {
    private SpriteBatch batch = null;
    private boolean loadingAssets = true;
    private boolean lastUpdate = true;
    private LoadingScene loadingScane = null;

    protected Color clearColor = Color.BLACK;

    protected IAchievementsProvider achievementsProvider = null;

    public AbstractGame(IAchievementsProvider achievementsProvider) {
        this.achievementsProvider = achievementsProvider;
    }

    protected abstract FrameworkAssetsManager createAssetManager();

    @Override
    public void create () {
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);

        FrameworkAssetsManager assetsManager = createAssetManager();
        assetsManager.load("loading.atlas", TextureAtlas.class);
        assetsManager.finishLoading();

        assetsManager.load();
        GameUtils.assetsManager = assetsManager;

        ScenesManager.inst().push(createLoadingScene(), true);
        batch = new SpriteBatch();
    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ScenesManager.inst().peek().act(Math.min(Gdx.graphics.getDeltaTime(), 1f/30f));
        ScenesManager.inst().peek().draw(batch);

        if(loadingAssets) {
            loadingScane.updateProgress();
            if (GameUtils.assetsManager.update()) {
                if (lastUpdate) {
                    loadingScane.updateProgress();
                    lastUpdate = false;
                    GameUtils.assetsManager.init();
                } else {
                    loadingAssets = false;
                    ScenesManager.inst().push(createFirstScene(), true);
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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

    protected abstract IScene createFirstScene();

    protected IScene createLoadingScene() {
        if(loadingScane == null) {
            loadingScane = new LoadingScene();
        }
        return loadingScane;
    }
}
