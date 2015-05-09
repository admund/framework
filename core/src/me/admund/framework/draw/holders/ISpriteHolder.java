package me.admund.framework.draw.holders;

import com.badlogic.gdx.graphics.g2d.Sprite;
import me.admund.framework.draw.SpriteList;
import me.admund.framework.draw.animations.AnimationState;

/**
 * Created by admund on 2014-12-29.
 */
public interface ISpriteHolder {
    SpriteList getSpriteList();
    void addSprite(Sprite sprite);
    void changeAnimationState(AnimationState state);
    void act(float delta);
    void updateSize(float width, float hight);
    void updatePosition(float x, float y, float rotation);
    void updateScale(float scaleX, float scaleY);
    void updateOrigin(float originX, float originY);
    float getSpriteWidth();
    float getSpriteHeight();
}
