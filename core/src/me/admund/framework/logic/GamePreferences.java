package me.admund.framework.logic;

import com.badlogic.gdx.Preferences;

/**
 * Created by admund on 2015-02-23.
 */
public abstract class GamePreferences {
    protected Preferences preferences = null;

    public GamePreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public abstract void init();

    public abstract void save(Score score);
}
