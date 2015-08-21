package me.admund.framework.draw.holders;

import com.badlogic.gdx.graphics.g2d.Sprite;
import me.admund.framework.GameUtils;
import me.admund.framework.draw.animations.AnimationState;
import me.admund.framework.draw.sprites.OffsetSprite;

/**
 * Created by admund on 2015-02-06.
 */
public class SimpleSpriteHolder extends AbstractSpriteHolder {

    public SimpleSpriteHolder(String textureName) {
        addSprite(new Sprite(GameUtils.assetsManager.getTextureRegionFromMain(textureName)));
    }

    public SimpleSpriteHolder(String textureName, float offsetX, float offsetY) {
        addSprite(new OffsetSprite(GameUtils.assetsManager.getTextureRegionFromMain(textureName),
                offsetX, offsetY));
    }

    @Override
    public void changeAnimationState(AnimationState state) {}

    @Override
    public void act(float delta) {}
}
