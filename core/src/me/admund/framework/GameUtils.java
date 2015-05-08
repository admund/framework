package me.admund.framework;

import com.badlogic.gdx.graphics.Camera;
import me.admund.framework.assets.FrameworkAssetsManager;
import me.admund.framework.sounds.FrameworkSoundsManager;

/**
 * Created by admund on 2015-04-11.
 */
public class GameUtils {
    public static Camera currentCamera = null;
    public static FrameworkAssetsManager assetsManager = null;
    public static FrameworkSoundsManager soundsManager = null;

    public static void dispose() {
        if(assetsManager != null) assetsManager.dispose();
    }
}
