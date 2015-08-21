package me.admund.framework.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.admund.framework.GameConfig;
import me.admund.framework.GameUtils;
import me.admund.framework.draw.gui.GuiUtils;

/**
 * Created by admund on 2015-04-11.
 */
public class LoadingScene extends AbstractScene {
    private static float GAME_LOGO_WIDTH = Gdx.graphics.getWidth() * .5f;
    private static float GAME_LOGO_HEIGHT = Gdx.graphics.getHeight() * .5f;

    private static float PROGRESSBAR_WIDTH = Gdx.graphics.getWidth() * .5f;
    private static float PROGRESSBAR_HEIGHT = Gdx.graphics.getHeight() * .2f;

    private static float TEAM_LOGO_WIDTH = Gdx.graphics.getWidth() * .25f;
    private static float TEAM_LOGO_HEIGHT = Gdx.graphics.getHeight() * .25f;

    private ProgressBar progressBar = null;

    @Override
    public void create() {
        createGameLogo();
        createTeamLogo();
        createProgressBar();
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void updateProgress() {
        progressBar.setValue(GameUtils.assetsManager.getProgress());
    }

    protected Drawable getGameLogoUtils() {
        return GuiUtils.createSpriteDrawable("loading.atlas", "game_logo");
    }

    protected Drawable getTeamLogoUtils() {
        return GuiUtils.createSpriteDrawable("loading.atlas", "team_logo");
    }

    protected ProgressBar.ProgressBarStyle createProgressBarStyle() {
        ProgressBar.ProgressBarStyle style = new ProgressBar.ProgressBarStyle();
        style.background = GuiUtils.createSpriteDrawable("loading.atlas", "progressbar_back");
        style.knob = GuiUtils.createSpriteDrawable("loading.atlas", "knob");
        style.knobBefore = GuiUtils.createSpriteDrawable("loading.atlas", "progressbar_front");
        return style;
    }

    private void createGameLogo() {
        Image gameLogo = new Image();
        gameLogo.setDrawable(getGameLogoUtils());
        gameLogo.setPosition((GameConfig.GAME_WIDTH - GAME_LOGO_WIDTH) * .5f,
                GameConfig.GAME_HEIGHT - GAME_LOGO_HEIGHT);
        gameLogo.setSize(GAME_LOGO_WIDTH, GAME_LOGO_HEIGHT);
        guiStage.addActor(gameLogo);
    }

    private void createTeamLogo() {
        Image teamLogo = new Image();
        teamLogo.setDrawable(getTeamLogoUtils());
        teamLogo.setPosition((GameConfig.GAME_WIDTH - TEAM_LOGO_WIDTH) * .5f, 0);
        teamLogo.setSize(TEAM_LOGO_WIDTH, TEAM_LOGO_HEIGHT);
        guiStage.addActor(teamLogo);
    }

    private void createProgressBar() {
        progressBar = new ProgressBar(0, 1, 0.1f, false, createProgressBarStyle());
        progressBar.setSize(PROGRESSBAR_WIDTH, PROGRESSBAR_HEIGHT);
        progressBar.setPosition((GameConfig.GAME_WIDTH - GAME_LOGO_WIDTH) * .5f,
                (GameConfig.GAME_HEIGHT - GAME_LOGO_HEIGHT - PROGRESSBAR_HEIGHT));
        guiStage.addActor(progressBar);
    }
}
