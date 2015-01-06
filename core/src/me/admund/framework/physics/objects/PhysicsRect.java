package me.admund.framework.physics.objects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import me.admund.framework.physics.NoneType;
import me.admund.framework.physics.PhysicsObject;
import me.admund.framework.physics.PhysicsUtils;

/**
 * Created by admund on 2015-01-06.
 */
public class PhysicsRect extends PhysicsObject {

    public PhysicsRect() {
        super(new NoneType(true));
    }

    @Override
    public BodyDef getBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.bullet = true;
        return bodyDef;
    }

    @Override
    public FixtureDef getFixtureDef() {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = PhysicsUtils.getDefaultPolygonShape();
        //fixtureDef.isSensor = true;
        return fixtureDef;
    }

    public void init(float x, float y, float width, float height) {
        super.init();
        setSize(width, height);
        setOrigin(Align.center);
        setCurrentPos(x, y);
        PhysicsUtils.updateRectShape(getShape(), width * .5f, height * .5f);
    }
}