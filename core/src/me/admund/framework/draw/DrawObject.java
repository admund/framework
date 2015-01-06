package me.admund.framework.draw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by admund on 2015-01-04.
 */
public abstract class DrawObject extends Actor {
    private ITextureHolder textureHolder = null;

    protected void setTextureHolder(ITextureHolder textureHolder) {
        this.textureHolder = textureHolder;
    }

    protected Texture getTexture() {
        return textureHolder.getTexture();
    }
}
