package me.admund.framework.draw.particle;

import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;

/**
 * Created by admund on 2015-05-24.
 */
public class ParticleUtils {

    public static ParticleEffectLoader.ParticleEffectParameter createParticleParameter(String atlasName, String path) {
        ParticleEffectLoader.ParticleEffectParameter param = new ParticleEffectLoader.ParticleEffectParameter();
        param.atlasFile = atlasName;
        param.atlasPrefix = path;
        return param;
    }

}
