package me.admund.framework.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import me.admund.framework.draw.model.BodyEditorLoader;

/**
 * Created by admund on 2015-08-10.
 */
public abstract class ModelPhysicsObject extends PhysicsObject {

    protected void loadModel(String modelName) {
        getBody().destroyFixture(this.getBody().getFixtureList().first());
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal(modelName));
        loader.attachFixture(getBody(), null, getFixtureDef(), getWidth());
        Vector2 origin = loader.getOrigin(null, 1);//getWidth());
        setOrigin(origin.x * getWidth(), origin.y * getWidth());
        setAligment(Aligment.create(origin.x, origin.y*(getWidth()/getHeight())));
    }
}
