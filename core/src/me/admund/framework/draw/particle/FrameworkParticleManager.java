package me.admund.framework.draw.particle;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by admund on 2015-05-24.
 */
public abstract class FrameworkParticleManager extends ObjectMap<String, ParticleEffectPool> implements Disposable {

    public abstract void init();

    public ParticleEffectPool.PooledEffect getEffect(String effectName) {
        return get(effectName).obtain();
    }

    @Override
    public void dispose() {
        ObjectMap.Values<ParticleEffectPool> soundsValues = this.values();
        if(soundsValues.hasNext()) {
            ;// TODO soundsValues.next().dispose();
        }
    }
}
