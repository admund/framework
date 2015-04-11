package me.admund.framework.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by admund on 2015-04-11.
 */
public abstract class FrameworkAssetsManager extends AssetManager {
    protected TextureAtlas mainAtlas = null;

    public abstract void init();

    public TextureRegion getTextureRegion(String fileName) {
        if(mainAtlas == null) throw new RuntimeException("FrameworkAssetsManager: Main Atlas not set");
        return getTextureRegion("Main", mainAtlas, fileName);
    }

    public TextureRegion getTextureRegion(String textureAtlasName, String fileName) {
        TextureAtlas atlas = get(textureAtlasName, TextureAtlas.class);
        if(atlas == null) throw new RuntimeException("FrameworkAssetsManager: Atlas named \""
                + textureAtlasName + "\" not found");
        return getTextureRegion(textureAtlasName, atlas, fileName);
    }

    public Array<? extends TextureRegion> getTextureRegions(String fileName) {
        if(mainAtlas == null) throw new RuntimeException("FrameworkAssetsManager: Main Atlas not set");
        return getTextureRegions("Main", mainAtlas, fileName);
    }

    public Array<? extends TextureRegion> getTextureRegions(String textureAtlasName, String fileName) {
        TextureAtlas atlas = get(textureAtlasName, TextureAtlas.class);
        if(atlas == null) throw new RuntimeException("FrameworkAssetsManager: Atlas named \""
                + textureAtlasName + "\" not found");
        return getTextureRegions(textureAtlasName, atlas, fileName);
    }

    private TextureRegion getTextureRegion(String textureAtlasName, TextureAtlas atlas, String fileName) {
        TextureRegion textureregion = atlas.findRegion(fileName);
        if(textureregion == null) throw new RuntimeException("FrameworkAssetsManager: Can't find \""
                + fileName + "\" in \"" + textureAtlasName + "\" atlas");
        return textureregion;
    }

    private Array<? extends TextureRegion> getTextureRegions(String textureAtlasName, TextureAtlas atlas, String fileName) {
        Array<TextureAtlas.AtlasRegion> textureregion = atlas.findRegions(fileName);
        if(textureregion == null) throw new RuntimeException("FrameworkAssetsManager: Can't find \""
                + fileName + "\" in \"" + textureAtlasName + "\" atlas");
        return textureregion;
    }
}
