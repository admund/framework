package me.admund.framework.sounds;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ObjectMap;
import me.admund.framework.GameUtils;

/**
 * Created by admund on 2015-04-18.
 */
public class FrameworkSoundsManager {
    private ObjectMap<String, Music> musicsMap = null;
    private ObjectMap<String, Sound> soundsMap = null;
    private Music mainTheme = null;

    public void init(String mainThemeName) {
        mainTheme = GameUtils.assetsManager.getMusic(mainThemeName);
        mainTheme.setLooping(true);
    }

    public void playMain() {
        mainTheme.play();
    }

    protected void addSound(String soundName) {
        soundsMap.put(soundName, GameUtils.assetsManager.getSound(soundName));
    }

    protected void addMusic(String musicName) {
        musicsMap.put(musicName, GameUtils.assetsManager.getMusic(musicName));
    }
}
