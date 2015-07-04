package me.admund.framework.draw;

import me.admund.framework.draw.holders.SimpleSpriteHolder;

/**
 * Created by Adik on 4/26/2015.
 */
public class SimpleDrawObject extends DrawObject {

    public SimpleDrawObject(String textureName) {
        setSpriteHolder(new SimpleSpriteHolder(textureName), true);
    }

    public SimpleDrawObject init(float posX, float posY) {
        setPosition(posX, posY);
        return this;
    }

    public SimpleDrawObject init(float posX, float posY, float width, float height) {
        setPosition(posX, posY);
        setSize(width, height);
        return this;
    }
}
