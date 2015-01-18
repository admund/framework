package me.admund.framework.physics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.admund.framework.draw.ITextureHolder;

/**
 * Created by admund on 2014-12-23.
 */
public abstract class PhysicsObject extends Actor implements IPhysicsObject {
    private static final Vector2 ZERO = new Vector2(0, 0);

    private PhysicsObjectInfo info = null;
    private boolean canReuse = true;
    private PhysicsWorld world = null;
    protected Fixture fixture = null;
    protected Body body = null;
    private ITextureHolder textureHolder = null;

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

    public boolean canBeReuse() {
        return canReuse;
    }

    public void prepereToReuse() {
        if(body != null) {
            body.setActive(false);
            body.setTransform(-100f, -100f, 0f);
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
    }

    public void setActive(boolean isActive) {
        body.setActive(isActive);
    }

    public AType getType() {
        return info.getType();
    }

    private void updatePossition() {
        setPosition(getPosition().x, getPosition().y);
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

    protected void setCurrentPos(float x, float y) {
        setCurrentPos(x, y, getRotationRad());
        setPosition(x, y);
    }

    protected void setCurrentPos(float x, float y, float rotation) {
        body.setTransform(x, y, rotation);
    }

    // SIZE
    public void setSize(float width, float height) {
        super.setSize(width, height);
        PhysicsUtils.updateRectShape(getShape(), width * .5f, height * .5f);
    }

    public void setSize(float width, float height, Vector2[] verticles) {
        super.setSize(width, height);
        PhysicsUtils.updateRectShape(getShape(), verticles);
    }

    public void setSize(float radius) {
        super.setSize(radius*2, radius*2);
        PhysicsUtils.updateCircleShape(getShape(), radius);
    }

    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        Vector2 pos = getPosition();
        body.setTransform(pos.x, pos.y, degrees * MathUtils.degRad);
    }

    public abstract void beginContact(Contact contact, boolean isObjectA);

    public abstract void endContact(Contact contact, boolean isObjectA);

    protected PhysicsObject createObject(String className) {
        return world.getPhysicsObject(className);
    }

    protected Joint createJoint(JointDef joint) {
        return world.createJoint(joint);
    }

    protected void destoryJoint(Joint joint) {
        world.destroyJoint(joint);
    }

    protected void setTextureHolder(ITextureHolder textureHolder) {
        this.textureHolder = textureHolder;
    }

    protected Texture getTexture() {
        return textureHolder.getTexture();
    }
}
