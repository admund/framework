package me.admund.framework.draw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.admund.framework.draw.holders.ISpriteHolder;

/**
 * Created by admund on 2015-01-04.
 */
public abstract class DrawObject extends Actor {
    protected ISpriteHolder textureHolder = null;

    protected void setTextureHolder(ISpriteHolder textureHolder) {
        this.textureHolder = textureHolder;
    }

    protected Texture getTexture() {
        return textureHolder.getTexture();
    }

    protected SpriteList getSpriteList() {
        return textureHolder.getSpriteList();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(textureHolder != null) {
            textureHolder.act(delta);
        }
    }
}
