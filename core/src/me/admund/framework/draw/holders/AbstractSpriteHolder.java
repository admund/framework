package me.admund.framework.draw.holders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.admund.framework.draw.SpriteList;

/**
 * Created by admund on 2015-02-02.
 */
public abstract class AbstractSpriteHolder implements ISpriteHolder {
    protected SpriteList spriteList = new SpriteList();

    public AbstractSpriteHolder() {
        spriteList = new SpriteList();
    }

    public void addSprite(Sprite sprite) {
        spriteList.add(sprite);
    }

    @Override
    public SpriteList getSpriteList() {
        return spriteList;
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
