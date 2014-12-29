package me.admund.framework.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import me.admund.framework.FrameworkTest;
import me.admund.framework.GameConfig;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new FrameworkTest();
        }
}