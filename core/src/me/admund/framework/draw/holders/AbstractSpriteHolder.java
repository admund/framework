package me.admund.framework.draw.holders;

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

    @Override
    public void addSprite(Sprite sprite) {
        spriteList.add(sprite);
    }

    @Override
    public SpriteList getSpriteList() {
        return spriteList;
    }

    @Override
    public void updatePosition(float x, float y, float rotation) {
        for(int i=0; i<spriteList.size; i++) {
            spriteList.get(i).setPosition(x, y);
            spriteList.get(i).setRotation(rotation);
        }
    }

    @Override
    public void updateSize(float width, float hight) {
        for(int i=0; i<spriteList.size; i++) {
            spriteList.get(i).setSize(width, hight);
        }
    }

    @Override
    public void updateScale(float scaleX, float scaleY) {
        for(int i=0; i<spriteList.size; i++) {
            spriteList.get(i).setScale(scaleX, scaleX);
        }
    }

    @Override
    public void updateOrigin(float originX, float originY) {
        for(int i=0; i<spriteList.size; i++) {
            spriteList.get(i).setOrigin(originX, originY);
        }
    }

    @Override
    public float getSpriteWidth() {
        if(spriteList.size > 0) {
            return spriteList.get(0).getWidth();
        }
        return 0;
    }

    @Override
    public float getSpriteHeight() {
        if(spriteList.size > 0) {
            return spriteList.get(0).getHeight();
        }
        return 0;
    }
}
