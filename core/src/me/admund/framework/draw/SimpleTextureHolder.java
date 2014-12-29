package me.admund.framework.draw;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by admund on 2014-12-29.
 */
public class SimpleTextureHolder implements ITextureHolder {
    private Texture texture = null;
    private String textureName = null;

    public SimpleTextureHolder(String textureName) {
        this.textureName = textureName;
    }

    @Override
    public Texture getTexture() {
        if(texture == null) {
            texture = TextureRepo.inst().getTexture(textureName);
        }
        return texture;
    }
}
