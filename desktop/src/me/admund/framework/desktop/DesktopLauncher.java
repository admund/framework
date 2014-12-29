package me.admund.framework.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.admund.framework.FrameworkTest;
import me.admund.framework.GameConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameConfig.GAME_WIDTH;
		config.height = GameConfig.GAME_HEIGHT;
		new LwjglApplication(new FrameworkTest(), config);
	}
}
