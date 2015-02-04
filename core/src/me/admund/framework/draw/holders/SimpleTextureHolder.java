package me.admund.framework.draw.holders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.admund.framework.draw.SpriteList;
import me.admund.framework.draw.TextureRepo;
import me.admund.framework.draw.animations.AnimationState;

/**
 * Created by admund on 2014-12-29.
 */
public class SimpleTextureHolder extends AbstractSpriteHolder {
    private Texture texture = null;
    private String textureName = null;

    public SimpleTextureHolder(String textureName) {
        this.textureName = textureName;
    }

    @Override
    public SpriteList getSpriteList() {
        return null;
    }

    @Override
    public Texture getTexture() {
        if(texture == null) {
            texture = TextureRepo.inst().getTexture(textureName);
        }
        return texture;
    }

    @Override
    public void changeAnimationState(AnimationState state) {}

    @Override
    public void act(float delta) {}
}
