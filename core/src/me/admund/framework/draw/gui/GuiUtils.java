package me.admund.framework.draw.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import me.admund.framework.GameUtils;

/**
 * Created by admund on 2015-03-05.
 */
public class GuiUtils {

    public static Drawable createSpriteDrawable(String atlasName, String textureName) {
        return new SpriteDrawable(new Sprite(GameUtils.assetsManager.getTextureRegion(atlasName, textureName)));
    }

    public static Drawable createSpriteDrawable(String textureName) {
        return new SpriteDrawable(new Sprite(GameUtils.assetsManager.getTextureRegion(textureName)));
    }

}
