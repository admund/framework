package me.admund.framework.draw;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.admund.framework.draw.holders.ISpriteHolder;
import me.admund.framework.physics.PhysicsWorld;

/**
 * Created by admund on 2015-01-04.
 */
public abstract class DrawObject extends Actor {
    protected ISpriteHolder spriteHolder = null;

    protected boolean isSpriteHolder() {
        return spriteHolder != null;
    }

    protected void setSpriteHolder(ISpriteHolder spriteHolder, boolean useSpriteSize) {
        this.spriteHolder = spriteHolder;
        if(useSpriteSize) {
            setSize(spriteHolder.getSpriteWidth() * PhysicsWorld.SCREEN_TO_BOX,
                    spriteHolder.getSpriteHeight() * PhysicsWorld.SCREEN_TO_BOX);
        }
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

    public void setSize(float width, float hight) {
        super.setSize(width, hight);
        updateSpriteHolder();
    }

    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        updateSpriteHolder();
    }

    protected void updateSpriteHolder() {
        if(spriteHolder != null) {
            spriteHolder.updatePosition(getX() * PhysicsWorld.BOX_TO_SCREEN, getY() * PhysicsWorld.BOX_TO_SCREEN,
                    getRotation());
            spriteHolder.updateSize(getWidth() * PhysicsWorld.BOX_TO_SCREEN, getHeight() * PhysicsWorld.BOX_TO_SCREEN);
            spriteHolder.updateScale(getScaleX(), getScaleY());
            spriteHolder.updateOrigin(getOriginX() * PhysicsWorld.BOX_TO_SCREEN, getOriginY() * PhysicsWorld.BOX_TO_SCREEN);
        }
    }

    public abstract void draw(Batch batch, float parentAlpha);
}
