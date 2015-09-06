package me.admund.framework.draw.holders;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.admund.framework.draw.animations.AnimationState;

/**
 * Created by admund on 2015-08-22.
 */
public class AnimationTiledSpriteHolder extends TiledSpriteHolder {

    private float stateTime = 0;
    private Animation currentAnimation = null;
    private AnimationState currentAnimationState = null;

    public AnimationTiledSpriteHolder(Animation animation) {
        super(null);
        currentAnimation = animation;
        Sprite sprite = new Sprite();
        sprite.setRegion(currentAnimation.getKeyFrame(stateTime));
        addSprite(sprite);
    }

    @Override
    public void changeAnimationState(AnimationState state) {
// TODO nie wiem czemu to bylo zakomentowane :/ teoretycznie powinno dzialac :)
//        if(currentAnimationState != state) {
//            Animation anim = aniamationMap.get(state);
//            if (anim != null) {
//                currentAnimation = anim;
//                currentAnimationState = state;
//                stateTime = 0;
//            }
//        }
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        getSpriteList().get(0).setRegion(currentAnimation.getKeyFrame(stateTime));
    }
}
