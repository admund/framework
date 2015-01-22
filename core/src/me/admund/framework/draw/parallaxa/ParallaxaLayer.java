package me.admund.framework.draw.parallaxa;

import com.badlogic.gdx.graphics.g2d.Batch;
import me.admund.framework.draw.DrawObject;
import me.admund.framework.draw.DrawUtils;
import me.admund.framework.draw.SimpleTextureHolder;

/**
 * Created by admund on 2015-01-22.
 */
public class ParallaxaLayer extends DrawObject {
    private static float SIZE_X = 150;
    private static float SIZE_Y = 30;

    private float value = 0f;

    public ParallaxaLayer(float value, String textureName) {
        setTextureHolder(new SimpleTextureHolder(textureName));
        this.value = value;
    }

    public void init(float posX, float posY) {
        setPosition(posX, posY);
        setSize(SIZE_X, SIZE_Y);
    }

    public void updatePos(float cameraTransition) {
        setX(getX() + value * cameraTransition);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        DrawUtils.drawActor(batch, getTexture(), this);
    }
}
