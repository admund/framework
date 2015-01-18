package me.admund.framework.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by admund on 2015-01-11.
 */
public class FontUtils {
    private static BitmapFont basicFont = null;

    public static BitmapFont getBasicFont() {
        if(basicFont == null) {
            basicFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), Gdx.files.internal("fonts/font_0.png"), false);
        }
        return basicFont;
    }

    public static void dispose() {
        if(basicFont != null) {
            basicFont.dispose();
        }
    }
}
