package me.admund.framework.physics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import me.admund.framework.draw.SpriteList;
import me.admund.framework.draw.holders.ISpriteHolder;

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
    private ISpriteHolder spriteHolder = null;
    private int aligment = Align.center;

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
        setPosition(getPosition().x, getPosition().y, aligment);
        updateSpriteHolder();
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

    public void setAligment(int aligment) {
        this.aligment = aligment;
    }

    protected void setCurrentPos(float x, float y) {
        setCurrentPos(x, y, getRotationRad());
    }

    protected void setCurrentPos(float x, float y, float rotation) {
        super.setPosition(x, y, aligment);
        body.setTransform(x, y, rotation);
        updateSpriteHolder();
    }

    // SIZE
    public void setSize(float width, float height) {
        super.setSize(width, height);
        PhysicsUtils.updateRectShape(getShape(), width * .5f, height * .5f);
        updateSpriteHolder();
    }

    public void setSize(float width, float height, Vector2[] verticles) {
        super.setSize(width, height);
        PhysicsUtils.updateRectShape(getShape(), verticles);
        updateSpriteHolder();
    }

    public void setSize(float radius) {
        super.setSize(radius * 2, radius * 2);
        PhysicsUtils.updateCircleShape(getShape(), radius);
        updateSpriteHolder();
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

    protected boolean hasSpriteHolder() {
        return spriteHolder != null;
    }

    protected void setSpriteHolder(ISpriteHolder spriteHolder) {
        this.spriteHolder = spriteHolder;
        updateSpriteHolder();
    }

    protected SpriteList getSpriteList() {
        return spriteHolder.getSpriteList();
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
}
