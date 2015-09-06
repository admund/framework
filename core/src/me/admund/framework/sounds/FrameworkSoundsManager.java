package me.admund.framework.sounds;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import me.admund.framework.GameUtils;

/**
 * Created by admund on 2015-04-18.
 */
public abstract class FrameworkSoundsManager implements Disposable {
    private ObjectMap<String, Music> musicsMap = null;
    private ObjectMap<String, Sound> soundsMap = null;
    private Music mainTheme = null;

    public FrameworkSoundsManager() {
        musicsMap = new ObjectMap<String, Music>();
        soundsMap = new ObjectMap<String, Sound>();
    }

    public abstract void init();

    public void playMain() {
        mainTheme.play();
    }

    public void playSound(String soundName) {
        Sound sound = soundsMap.get(soundName);
        if(sound != null) {
            sound.play();
        }
    }

    protected void setMainTheme(String mainThemeName) {
        if(mainThemeName != null) {
            mainTheme = GameUtils.assetsManager.getMusic(mainThemeName);
            mainTheme.setLooping(true);
        }
    }

    protected void addSound(String soundName) {
        soundsMap.put(soundName, GameUtils.assetsManager.getSound(soundName));
    }

    protected void addMusic(String musicName) {
        musicsMap.put(musicName, GameUtils.assetsManager.getMusic(musicName));
    }

    @Override
    public void dispose() {
        ObjectMap.Values<Music> musicValues = musicsMap.values();
        if(musicValues.hasNext()) {
            musicValues.next().dispose();
        }

        ObjectMap.Values<Sound> soundsValues = soundsMap.values();
        if(soundsValues.hasNext()) {
            soundsValues.next().dispose();
        }
    }
}
