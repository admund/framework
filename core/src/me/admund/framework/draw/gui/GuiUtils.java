package me.admund.framework.draw.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import me.admund.framework.draw.TextureRepo;

/**
 * Created by admund on 2015-03-05.
 */
public class GuiUtils {

    public static Drawable createSpriteDrawable(String textureName) {
        Drawable drawable = new SpriteDrawable(new Sprite(TextureRepo.inst().getTexture(textureName)));
        return drawable;
    }

}
