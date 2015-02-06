package me.admund.framework.draw.holders;

import com.badlogic.gdx.graphics.g2d.Sprite;
import me.admund.framework.draw.TextureRepo;
import me.admund.framework.draw.animations.AnimationState;

/**
 * Created by admund on 2015-02-06.
 */
public class SimpleSpriteHolder extends AbstractSpriteHolder {

    public SimpleSpriteHolder(String textureName) {
        addSprite(new Sprite(TextureRepo.inst().getTexture(textureName)));
    }

    @Override
    public void changeAnimationState(AnimationState state) {}

    @Override
    public void act(float delta) {}
}
