package me.admund.framework.draw;

import com.badlogic.gdx.scenes.scene2d.Actor;
import me.admund.framework.draw.holders.ISpriteHolder;
import me.admund.framework.physics.PhysicsWorld;

/**
 * Created by admund on 2015-01-04.
 */
public abstract class DrawObject extends Actor {
    protected ISpriteHolder spriteHolder = null;

    protected void setTextureHolder(ISpriteHolder textureHolder) {
        this.spriteHolder = textureHolder;
        updateSpriteHolder();
    }

    protected SpriteList getSpriteList() {
        return spriteHolder.getSpriteList();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(spriteHolder != null) {
            spriteHolder.act(delta);
        }
    }

    public void setScaledSize(float width, float hight) {
        setSize(width * PhysicsWorld.BOX_TO_SCREEN, hight * PhysicsWorld.BOX_TO_SCREEN);
        updateSpriteHolder();
    }

    public void setScaledPosition(float x, float y) {
        setPosition(x * PhysicsWorld.BOX_TO_SCREEN, y * PhysicsWorld.BOX_TO_SCREEN);
        updateSpriteHolder();
    }

    private void updateSpriteHolder() {
        if(spriteHolder != null) {
            spriteHolder.updatePosition(getX(), getY(), getRotation());
            spriteHolder.updateSize(getWidth(), getHeight());
            spriteHolder.updateScale(getScaleX(), getScaleY());
            spriteHolder.updateOrigin(getOriginX(), getOriginY());

        }
    }
}
