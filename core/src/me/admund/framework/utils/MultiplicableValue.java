package me.admund.framework.utils;

/**
 * Created by admund on 2015-01-06.
 */
public class MultiplicableValue {
    private float EFFECT_TIME = 3f;
    private float DELAY_TIME = 0f;

    private float value;
    private float multiplier;
    private float effectTime = 0;
    private float delayTime = 0;

    public MultiplicableValue(float value, float multiplier) {
        this.value = value;
        this.multiplier = multiplier;
    }

    public MultiplicableValue(float value, float multiplier, float effectTime) {
        this(value, multiplier);
        this.EFFECT_TIME = effectTime;
    }

    public MultiplicableValue(float value, float multiplier, float effectTime, float delayTime) {
        this(value, multiplier, effectTime);
        this.DELAY_TIME = delayTime;
    }

    public boolean canMultiply() {
        return effectTime == 0f;
    }

    public boolean isMultiply() {
        return effectTime != 0f;
    }

    public void multiply() {
        this.effectTime = EFFECT_TIME;
        this.delayTime = EFFECT_TIME + DELAY_TIME;
    }

    public void act(float deltaTime) {
        if(!canMultiply()) {
            delayTime -= delayTime;
            if(delayTime < 0f) {
                delayTime = 0f;
            }

            if(isMultiply()) {
                effectTime -= deltaTime;
                if (effectTime < 0f) {
                    effectTime = 0f;
                }
            }
        }
    }

    public void changeBaseValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return effectTime == 0f ? value : value * multiplier;
    }
}
