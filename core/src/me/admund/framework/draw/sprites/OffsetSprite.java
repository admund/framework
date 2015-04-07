package me.admund.framework.draw.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.admund.framework.physics.PhysicsWorld;

/**
 * Created by admund on 2015-04-06.
 */
public class OffsetSprite extends Sprite {
    private float offsetX = 0;
    private float offsetY = 0;

    public OffsetSprite(Texture texture) {
        super(texture);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(offsetX + x, offsetY + y);
    }

    public void setOffset(float offsetX, float offsetY) {
        this.offsetX = offsetX * PhysicsWorld.BOX_TO_SCREEN;
        this.offsetY = offsetY * PhysicsWorld.BOX_TO_SCREEN;
    }

    @Override
    public void setSize(float width, float height) {
        // TODO cos tu trzeba podkombinowac
    }

    public void initSize(float width, float height) {
        super.setSize(width * PhysicsWorld.BOX_TO_SCREEN, height * PhysicsWorld.BOX_TO_SCREEN);
    }
}
