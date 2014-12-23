package me.admund.framework.draw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.admund.framework.physics.PhysicsWorld;

/**
 * Created by admund on 2014-12-23.
 */
public class DrawUtils {

    public static void drawActor(Batch batch, Texture texture, Actor actor) {
        batch.draw(texture,
                (actor.getX()-actor.getOriginX())*PhysicsWorld.BOX_TO_WORLD,
                (actor.getY()-actor.getOriginY())*PhysicsWorld.BOX_TO_WORLD,
                actor.getOriginX()*PhysicsWorld.BOX_TO_WORLD,
                actor.getOriginY()*PhysicsWorld.BOX_TO_WORLD,
                actor.getWidth()*PhysicsWorld.BOX_TO_WORLD,
                actor.getHeight()*PhysicsWorld.BOX_TO_WORLD,
                actor.getScaleX(),
                actor.getScaleY(),
                actor.getRotation(),
                0, 0, texture.getWidth(), texture.getHeight(),
                false, false);
    }

}
