package me.admund.framework.draw.holders;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ObjectMap;
import me.admund.framework.draw.animations.AnimationState;
import me.admund.framework.draw.sprites.OffsetSprite;

/**
 * Created by admund on 2015-04-11.
 */
public class AnimationSpriteHolder extends AbstractSpriteHolder {
    private ObjectMap<AnimationState, Animation> aniamationMap = new ObjectMap<AnimationState, Animation>();
    private float stateTime;
    private Animation currentAnimation = null;
    private AnimationState currentAnimationState = null;

    private Sprite sprite = null;

    public AnimationSpriteHolder() {
        sprite = new Sprite();
        addSprite(sprite);
    }

    public AnimationSpriteHolder(float offsetX, float offsetY) {
        sprite = new OffsetSprite(offsetX, offsetY);
        addSprite(sprite);
    }

    public ISpriteHolder addAnimation(AnimationState state, Animation animation) {
        aniamationMap.put(state, animation);
        if(currentAnimation == null) {
            currentAnimation = animation;
            currentAnimationState = state;
            sprite.setRegion(currentAnimation.getKeyFrame(stateTime));
        }
        return this;
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        sprite.setRegion(currentAnimation.getKeyFrame(stateTime));
    }

    @Override
    public void changeAnimationState(AnimationState state) {
        if(currentAnimationState != state) {
            Animation anim = aniamationMap.get(state);
            if (anim != null) {
                currentAnimation = anim;
                currentAnimationState = state;
                stateTime = 0;
            }
        }
    }
}
