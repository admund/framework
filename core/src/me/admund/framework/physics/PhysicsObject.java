package me.admund.framework.physics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import me.admund.framework.draw.DrawObject;
import me.admund.framework.utils.UpdateType;

/**
 * Created by admund on 2014-12-23.
 */
public abstract class PhysicsObject extends DrawObject implements IPhysicsObject {
    private static final Vector2 ZERO = new Vector2(0, 0);

    protected Fixture fixture = null;
    protected Body body = null;
    protected boolean hasPhysicModel = false;

    private Aligment aligment = Aligment.CENTER;
    private PhysicsObjectInfo info = null;
    private boolean canReuse = true;
    private PhysicsWorld world = null;

    public PhysicsObject(AType type) {
        info = new PhysicsObjectInfo().setType(type).setObj(this);
    }

    public void create(PhysicsWorld world) {
        this.world = world;
        world.createBody(this);
    }

    public void init() {
        body.setActive(true);
        body.setLinearVelocity(ZERO);
        body.setAngularVelocity(0);
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

    public boolean canBeReuse() {
        return canReuse;
    }

    public void prepereToReuse() {
        if(body != null) {
            body.setActive(false);
            body.setTransform(-10000f, -10000f, 0f);
            body.setLinearVelocity(ZERO);
            body.setAngularVelocity(0);
        }
        canReuse = true;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updatePossition();
        updateRotation();
        if(isSpriteHolder()) spriteHolder.act(delta);
    }

    public void setActive(boolean isActive) {
        body.setActive(isActive);
    }

    public AType getType() {
        return info.getType();
    }

    private void updatePossition() {
        setPosition(getPosition().x, getPosition().y, aligment);
        updateSpriteHolder(UpdateType.POSSITION);
    }

    private void updateRotation() {
        super.setRotation(body.getAngle() * MathUtils.radDeg);
    }

    // POSSITION
    protected void setCurrentPos(Vector2 pos) {
        setCurrentPos(pos.x, pos.y);
    }

    protected void setCurrentPos(Vector2 pos, float rotation) {
        setCurrentPos(pos.x, pos.y, rotation);
    }

    public void setAligment(Aligment aligment) {
        this.aligment = aligment;
    }

    protected void setCurrentPos(float x, float y) {
        setCurrentPos(x, y, getRotationRad());
    }

    protected void setCurrentPos(float x, float y, float rotation) {
        setPosition(x, y, aligment);
        setRotation(rotation * MathUtils.radDeg);
        body.setTransform(x, y, rotation);
        updateSpriteHolder(UpdateType.POSSITION);
    }

    // SIZE
    public void setSize(float width, float height) {
        super.setSize(width, height);
        if(!hasPhysicModel) {
            PhysicsUtils.updateRectShape(getShape(), width * .5f, height * .5f);
            updateSpriteHolder(UpdateType.SIZE);
        }
    }

    public void setSize(float width, float height, Vector2[] verticles) {
        super.setSize(width, height);
        if(!hasPhysicModel) {
            PhysicsUtils.updateRectShape(getShape(), verticles);
            updateSpriteHolder(UpdateType.SIZE);
        }
    }

    public void setSize(float radius) {
        super.setSize(radius * 2, radius * 2);
        if(!hasPhysicModel) {
            PhysicsUtils.updateCircleShape(getShape(), radius);
            updateSpriteHolder(UpdateType.SIZE);
        }
    }

    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        Vector2 pos = getPosition();
        body.setTransform(pos.x, pos.y, degrees * MathUtils.degRad);
    }

    public void beginContact(Contact contact, boolean isObjectA) {
    }

    public void endContact(Contact contact, boolean isObjectA) {
    }

    public void preSolve(Contact contact, Manifold oldManifold, boolean isObjectA) {
    }

    public void postSolve(Contact contact, ContactImpulse impulse, boolean isObjectA) {
    }

    protected PhysicsObject createObject(String className) {
        return world.getPhysicsObject(className);
    }

    protected Joint createJoint(JointDef joint) {
        return world.createJoint(joint);
    }

    protected void destoryJoint(Joint joint) {
        world.destroyJoint(joint);
    }

    private void setPosition (float x, float y, Aligment alignment) {
        x -= getWidth() * alignment.getAligmentX();
        y -= getHeight() * alignment.getAligmentY();

        if (getX() != x || getY() != y) {
            setX(x);
            setY(y);
            positionChanged();
        }
    }
}
