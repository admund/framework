package me.admund.framework.utils;

/**
 * Created by admund on 2015-01-11.
 */
public class TimeBoolean {
    private static float DELAY_TIME = 3f;
    private float delayTime = 0;
    private boolean value = false;

    public TimeBoolean(boolean value) {
        this.value = value;
    }

    public TimeBoolean(boolean value, float delayTime) {
        this(value);
        this.DELAY_TIME = delayTime;
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
            delayTime = DELAY_TIME;
        }
    }

    public boolean getValue() {
        return delayTime == 0f ? value : !value;
    }
}
