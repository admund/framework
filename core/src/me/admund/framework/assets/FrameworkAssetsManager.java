package me.admund.framework.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by admund on 2015-04-11.
 */
public abstract class FrameworkAssetsManager extends AssetManager {
    protected TextureAtlas mainAtlas = null;

    public abstract void init();
    public abstract void load();

    public BitmapFont getBitmapFont(String fontName) {
        BitmapFont font = get(fontName, BitmapFont.class);
        if(font == null) throw new RuntimeException("FrameworkAssetsManager: BitmapFont named \""
                + fontName + "\" not found");
        return font;
    }

    public Sound getSound(String soundName) {
        Sound sound = get(soundName, Sound.class);
        if(sound == null) throw new RuntimeException("FrameworkAssetsManager: Sound named \""
                + soundName + "\" not found");
        return sound;
    }

    public Music getMusic(String musicName) {
        Music music = get(musicName, Music.class);
        if(music == null) throw new RuntimeException("FrameworkAssetsManager: Music named \""
                + musicName + "\" not found");
        return music;
    }

    public TextureRegion getTextureRegionFromMain(String fileName) {
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

    public ParticleEffect getParticleEffect(String particleName) {
        ParticleEffect particleEffect = get(particleName, ParticleEffect.class);
        if(particleEffect == null) throw new RuntimeException("FrameworkAssetsManager: ParticleEffect named \""
                + particleName + "\" not found");
        return particleEffect;
    }

    private Texture getTexture(String textureName) {
        Texture texture = get(textureName, Texture.class);
        if(texture == null) throw new RuntimeException("FrameworkAssetsManager: Texture named \""
                + textureName + "\" not found");
        return texture;
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
