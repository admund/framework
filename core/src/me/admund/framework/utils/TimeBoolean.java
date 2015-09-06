package me.admund.framework.utils;

/**
 * Created by admund on 2015-01-11.
 */
public class TimeBoolean {
    private static float DELAY_TIME = 3f;
    private float maxDelayTime = 0;
    private float delayTime = 0;
    private boolean value = false;

    public TimeBoolean(boolean value) {
        this.value = value;
        this.maxDelayTime = DELAY_TIME;
    }

    public TimeBoolean(boolean value, float delayTime) {
        this(value);
        this.maxDelayTime = delayTime;
    }

    public void act(float deltaTime) {
        if(delayTime != 0f) {
            delayTime -= deltaTime;

            if(delayTime < 0f) {
                delayTime = 0f;
            }
        }
    }

    public void change() {
        if(delayTime == 0f) {
            delayTime = maxDelayTime;
        }
    }

    public float getPercent() {
        return delayTime/maxDelayTime;
    }

    public void forceChange() {
        delayTime = maxDelayTime;
    }

    public void reset() {
        delayTime = 0;
    }

    public boolean getValue() {
        return delayTime == 0f ? value : !value;
    }
}
