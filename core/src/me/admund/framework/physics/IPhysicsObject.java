package me.admund.framework.me.admund.framework.physics;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Created by admund on 2014-12-23.
 */
public interface IPhysicsObject {
    public PhysicsObjectInfo getInfo();
    public BodyDef getBodyDef();
    public FixtureDef getFixtureDef();
    public void setFixture(Fixture fixture);
}
