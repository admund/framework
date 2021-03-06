package me.admund.framework.draw;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.admund.framework.draw.animations.AnimationState;
import me.admund.framework.draw.holders.ISpriteHolder;
import me.admund.framework.physics.PhysicsWorld;
import me.admund.framework.utils.UpdateType;

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
        updateSpriteHolder(UpdateType.ALL);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(spriteHolder != null) {
            spriteHolder.act(delta);
        }
    }

    @Override
    public void setSize(float width, float hight) {
        super.setSize(width, hight);
        updateSpriteHolder(UpdateType.SIZE);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        updateSpriteHolder(UpdateType.POSSITION);
    }

    @Override
    public void setOrigin(int alignment) {
        super.setOrigin(alignment);
        updateSpriteHolder(UpdateType.ALL);
    }

    @Override
    public void setOrigin(float originX, float originY) {
        super.setOrigin(originX, originY);
        updateSpriteHolder(UpdateType.ALL);
    }

    @Override
    public void setScale(float scaleX, float scaleY) {
        super.setScale(scaleX, scaleY);
        updateSpriteHolder(UpdateType.ALL);
    }

    @Override
    public void moveBy(float x, float y) {
        super.moveBy(x, y);
        updateSpriteHolder(UpdateType.POSSITION);
    }

    public void changeAnimationState(AnimationState state) {
        spriteHolder.changeAnimationState(state);
    }

    protected void updateSpriteHolder(UpdateType updateType) {
        if(spriteHolder != null) {
            if(updateType == UpdateType.ALL) {
                spriteHolder.updateScale(getScaleX(), getScaleY());
            }
            if(updateType == UpdateType.ALL || updateType == UpdateType.POSSITION) {
                spriteHolder.updatePosition(getX() * PhysicsWorld.BOX_TO_SCREEN, getY() * PhysicsWorld.BOX_TO_SCREEN,
                        getRotation());
            }
            if(updateType == UpdateType.ALL || updateType == UpdateType.SIZE) {
                spriteHolder.updateSize(getWidth() * PhysicsWorld.BOX_TO_SCREEN, getHeight() * PhysicsWorld.BOX_TO_SCREEN);
            }
            if(updateType == UpdateType.ALL) {
                spriteHolder.updateOrigin(getOriginX() * PhysicsWorld.BOX_TO_SCREEN, getOriginY() * PhysicsWorld.BOX_TO_SCREEN);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(isVisible() && isSpriteHolder()) {
            spriteHolder.draw(batch, parentAlpha);
        }
    }

    public float getScreenWidth() {
        return getWidth() * PhysicsWorld.BOX_TO_SCREEN;
    }

    public float getScreenHeight() {
        return getHeight() * PhysicsWorld.BOX_TO_SCREEN;
    }

    public float getScreenPosX() {
        return getX() * PhysicsWorld.BOX_TO_SCREEN;
    }

    public float getScreenPosY() {
        return getY() * PhysicsWorld.BOX_TO_SCREEN;
    }
}
