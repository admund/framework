package me.admund.framework.draw.animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import me.admund.framework.GameUtils;

/**
 * Created by admund on 2015-04-11.
 */
public class AnimationUtils {

    public static Animation createAnimation(float frameDuration, String fileName, PlayMode playMode) {
        return new Animation(frameDuration, GameUtils.assetsManager.getTextureRegions(fileName), playMode);
    }

    public static Animation createAnimation(float frameDuration, String atlasName, String fileName, PlayMode playMode) {
        return new Animation(frameDuration, GameUtils.assetsManager.getTextureRegions(atlasName, fileName), playMode);
    }

}
