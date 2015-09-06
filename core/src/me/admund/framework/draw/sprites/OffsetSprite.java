package me.admund.framework.draw.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.admund.framework.physics.PhysicsWorld;

/**
 * Created by admund on 2015-04-06.
 */
public class OffsetSprite extends Sprite {
    private float offsetX = 0;
    private float offsetY = 0;

    public OffsetSprite(float offsetX, float offsetY) {
        setOffset(offsetX, offsetY);
    }

    public OffsetSprite(TextureRegion texture, float offsetX, float offsetY) {
        super(texture);
        setOffset(offsetX, offsetY);
    }

    public OffsetSprite(TextureRegion texture) {
        this(texture, 0f, 0f);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(offsetX + x, offsetY + y);
    }

    public void setOffset(float offsetX, float offsetY) {
        this.offsetX = offsetX * PhysicsWorld.BOX_TO_SCREEN;
        this.offsetY = offsetY * PhysicsWorld.BOX_TO_SCREEN;
    }
}
