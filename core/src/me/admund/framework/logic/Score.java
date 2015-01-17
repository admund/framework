package me.admund.framework.logic;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by admund on 2015-01-11.
 */
public class Score {
    private static final Score inst = new Score();
    private Score(){}
    public static Score inst(){
        return inst;
    }

    private ObjectMap<String, Float> scoreMap = new ObjectMap<String, Float>();

    public float getScore(String scoreName) {
        Float tmp = scoreMap.get(scoreName);
        if(tmp == null) {
            tmp = new Float(0);
            scoreMap.put(scoreName, tmp);
        }
        return tmp.floatValue();
    }

    public void addScore(String scoreName, float value) {
        Float tmp = scoreMap.get(scoreName);
        if(tmp == null) {
            tmp = new Float(0);
            scoreMap.put(scoreName, tmp);
        }
        scoreMap.put(scoreName, tmp.floatValue() + value);
    }

    public void clear() {
        scoreMap.clear();
    }
}
