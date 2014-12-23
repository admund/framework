package me.admund.framework.physics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by admund on 2014-12-23.
 */
public abstract class PhysicsObject extends Actor implements IPhysicsObject {
    private PhysicsObjectInfo info = null;
    private boolean canReuse = true;
    private PhysicsWorld world = null;
    protected Fixture fixture = null;
    protected Body body = null;

    public PhysicsObject(AType type) {
        info = new PhysicsObjectInfo().setType(type).setObj(this);
    }

    public void create(PhysicsWorld world) {
        this.world = world;
        world.createBody(this);
    }

    public void init() {
        body.setActive(true);
        canReuse = false;
    }

    @Override
    public void setFixture(Fixture fixture) {
        fixture.setUserData(getInfo());
        this.fixture = fixture;
        this.body = fixture.getBody();
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public float getRotationRad() {
        return body.getAngle();
    }

    public float getRotationDeg() {
        return getRotationRad() * MathUtils.radDeg;
    }

    public Body getBody() {
        return body;
    }

    public Shape getShape() {
        return body.getFixtureList().get(0).getShape();
    }

    @Override
    public PhysicsObjectInfo getInfo() {
        return info;
    }

    public boolean canReUse() {
        return canReuse;
    }

    public void prepereToReuse() {
        if(body != null) {
            body.setActive(false);
            body.setTransform(-100f, -100f, 0f);
        }
        canReuse = true;
    }

    public void setActive(boolean isActive) {
        body.setActive(isActive);
    }

    public AType getType() {
        return info.getType();
    }

    protected void setCurrentPos(Vector2 pos) {
        setCurrentPos(pos.x, pos.y);
    }

    protected void setCurrentPos(Vector2 pos, float rotation) {
        setCurrentPos(pos.x, pos.y, rotation);
    }

    protected void setCurrentPos(float x, float y) {
        setCurrentPos(x, y, getRotationRad());
        setPosition(x, y);
    }

    protected void setCurrentPos(float x, float y, float rotation) {
        body.setTransform(x, y, rotation);
    }

    protected void destoryJoint(Joint joint) {
        world.destroyJoint(joint);
    }
}
