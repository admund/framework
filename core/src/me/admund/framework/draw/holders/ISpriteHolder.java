package me.admund.framework.draw.holders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.admund.framework.draw.SpriteList;
import me.admund.framework.draw.animations.AnimationState;

/**
 * Created by admund on 2014-12-29.
 */
public interface ISpriteHolder {
    public SpriteList getSpriteList();
    public Texture getTexture();
    public void addSprite(Sprite sprite);
    public void changeAnimationState(AnimationState state);
    public void act(float delta);
}
