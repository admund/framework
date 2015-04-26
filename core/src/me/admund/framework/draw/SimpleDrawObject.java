package me.admund.framework.draw;

import com.badlogic.gdx.graphics.g2d.Batch;
import me.admund.framework.draw.holders.SimpleSpriteHolder;

/**
 * Created by Adik on 4/26/2015.
 */
public class SimpleDrawObject extends DrawObject {

    public SimpleDrawObject(String textureName) {
        setSpriteHolder(new SimpleSpriteHolder(textureName));
    }

    public SimpleDrawObject init(float posX, float posY, float width, float height) {
        setPosition(posX, posY);
        setSize(width, height);
        return this;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(isVisible()) {
            DrawUtils.draw(batch, getSpriteList());
        }
    }
}
