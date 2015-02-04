package me.admund.framework.draw.parallaxa;

import com.badlogic.gdx.graphics.g2d.Batch;
import me.admund.framework.GameConfig;
import me.admund.framework.draw.DrawObject;
import me.admund.framework.draw.DrawUtils;
import me.admund.framework.draw.holders.ParallaxaSpriteHolder;
import me.admund.framework.physics.PhysicsWorld;

/**
 * Created by admund on 2015-01-22.
 */
public class ParallaxaLayer extends DrawObject {
    private static float SIZE_X = 1500;//150
    private static float SIZE_Y = 300; //30

    private float value = 0f;

    public ParallaxaLayer(float value, String textureName) {
        setTextureHolder(new ParallaxaSpriteHolder(textureName));
        this.value = value;
    }

    public void init(float posX, float posY) {
        setPosition(posX, posY);
        getParallaxaSpriteHolder().init(posX, posY, SIZE_X, SIZE_Y, GameConfig.GAME_WIDTH);
    }

    public void updatePos(float cameraTransition) {
        setX(getX() + value * cameraTransition);
        getParallaxaSpriteHolder().updatePosX(value * cameraTransition, cameraTransition);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        DrawUtils.drawActor(batch, getSpriteList());
    }

    private ParallaxaSpriteHolder getParallaxaSpriteHolder() {
        if(textureHolder instanceof ParallaxaSpriteHolder) {
            return (ParallaxaSpriteHolder)textureHolder;
        }
        throw new NullPointerException(this + " class need ParallaxaSpriteHolder as SpriteHolder");
    }
}
