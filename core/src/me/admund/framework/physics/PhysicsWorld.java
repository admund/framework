package me.admund.framework.physics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import me.admund.framework.GameConfig;

/**
 * Created by admund on 2014-12-23.
 */
public class PhysicsWorld implements Disposable {
    public static float WORLD_TO_BOX = 0.1f;
    public static float BOX_TO_WORLD = 10f;

    public static float BOX_SCREEN_WIDTH = WORLD_TO_BOX * GameConfig.GAME_WIDTH;
    public static float BOX_SCREEN_HEIGHT = WORLD_TO_BOX * GameConfig.GAME_HEIGHT;

    private World world = null;
    private ReuseFactory factory = null;
    private Box2DDebugRenderer debugRenderer = null;
    private Array<Body> bodyList = null;

    public PhysicsWorld(ReuseFactory factory) {
        this(new Vector2(0, 0), factory);
    }

    public PhysicsWorld(Vector2 gravity, ReuseFactory factory) {
        world = new World(gravity, true);
        debugRenderer = new Box2DDebugRenderer();
        bodyList = new Array<Body>();
        this.factory = factory;
        this.factory.setWorld(this);
    }

    public void step() {
        step(1f/60);
    }

    public void step(float deltaTime) {
        world.step(deltaTime, 6, 2);
        prepereToReuseBodies();
    }

    public void debugRender(Camera cam) {
        debugRenderer.render(world, cam.combined.cpy().scl(BOX_TO_WORLD));
    }

    public PhysicsObject getPhysicsObject(String className) {
        PhysicsObject obj = factory.get(className);
        return obj;
    }

    public Body createBody(IPhysicsObject obj) {
        Body body = world.createBody(obj.getBodyDef());
        FixtureDef fixtureDef = obj.getFixtureDef();
        Fixture fixture = body.createFixture(fixtureDef);
        fixtureDef.shape.dispose();
        obj.setFixture(fixture);
        return body;
    }

    public void setContactListner(ContactListener listener) {
        world.setContactListener(listener);
    }

    public Joint createJoint(JointDef def) {
        return world.createJoint(def);
    }

    public void destroyJoint(Joint joint) {
        world.destroyJoint(joint);
    }

    @Override
    public void dispose() {
        world.dispose();
        bodyList.clear();
        factory.dispose();
    }

    private void prepereToReuseBodies() {
        bodyList.clear();
        world.getBodies(bodyList);

        System.out.println("Body list size " + bodyList.size);

        for(int i=0; i<bodyList.size; i++) {
            Body body = bodyList.get(i);
            if(isRemove(body)) {
                prepereToReuse(body);
            }
        }
    }

    private boolean isRemove(Body body) {
        if(body.getFixtureList().get(0).getUserData() instanceof PhysicsObjectInfo) {
            PhysicsObjectInfo worldObj = (PhysicsObjectInfo)body.getFixtureList().get(0).getUserData();
            return worldObj.isSetToReuse();
        }
        return false;
    }

    private void prepereToReuse(Body body) {
        if(body.getFixtureList().get(0).getUserData() instanceof PhysicsObjectInfo) {
            PhysicsObjectInfo worldObj = (PhysicsObjectInfo)body.getFixtureList().get(0).getUserData();
            worldObj.prepereToReuse();
        }
    }
}
