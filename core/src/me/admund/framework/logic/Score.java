package me.admund.framework.logic;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by admund on 2015-01-11.
 */
public class Score {
    private GamePreferences pref = null;

    private ObjectMap<String, Float> scoreMap = new ObjectMap<String, Float>();

    public void init(GamePreferences pref) {
        this.pref = pref;
        this.pref.init();
    }

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

    public void setScore(String scoreName, float value) {
        Float tmp = scoreMap.get(scoreName);
        if(tmp == null) {
            tmp = new Float(0);
            scoreMap.put(scoreName, tmp);
        }
        scoreMap.put(scoreName, value);
    }

    public void clear() {
        scoreMap.clear();
    }

    public void save() {
        if(pref != null) {
            pref.save(this);
        }
    }
}
