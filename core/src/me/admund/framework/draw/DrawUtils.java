package me.admund.framework.draw;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import me.admund.framework.GameUtils;

/**
 * Created by admund on 2014-12-23.
 */
public class DrawUtils {

    public static void draw(Batch batch, SpriteList spriteList) {
        for(int i=0; i<spriteList.size; i++) {
            Sprite tmp = spriteList.get(i);
            tmp.draw(batch);
        }
    }

    public static TextureRegion getRandTextureFromTab(String[] stringTab) {
        return GameUtils.assetsManager.getTextureRegionFromMain(stringTab[MathUtils.random(stringTab.length - 1)]);
    }
}
