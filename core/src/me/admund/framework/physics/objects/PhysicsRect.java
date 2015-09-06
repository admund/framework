package me.admund.framework.physics.objects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Align;
import me.admund.framework.physics.PhysicsObject;
import me.admund.framework.physics.PhysicsUtils;

/**
 * Created by admund on 2015-01-06.
 */
public class PhysicsRect extends PhysicsObject {

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
        return fixtureDef;
    }

    public void init(float x, float y, float width, float height) {
        super.init();
        setSize(width, height);
        setOrigin(Align.center);
        setCurrentPos(x, y);
        PhysicsUtils.updateRectShape(getShape(), width * .5f, height * .5f);
    }

    @Override
    public void beginContact(Contact contact, boolean isObjectA) {}

    @Override
    public void endContact(Contact contact, boolean isObjectA) {}
}
