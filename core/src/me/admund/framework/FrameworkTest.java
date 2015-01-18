package me.admund.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import me.admund.framework.draw.DrawUtils;
import me.admund.framework.game.AbstractGame;
import me.admund.framework.physics.*;
import me.admund.framework.scenes.AbstractScene;
import me.admund.framework.scenes.ScenesManager;

public class FrameworkTest extends AbstractGame {

	@Override
	public void create () {
		ScenesManager.inst().push(new TestScene(), true);
	}

	@Override
	public void render() {
		super.render();
	}

	class TestScene extends AbstractScene {
		private PhysicsWorld world = null;

		@Override
		public void create() {
			world = new PhysicsWorld();

			PhysicsRect rect1 = new PhysicsRect();
			rect1.create(world);
			rect1.init(35, 20);
			stage.addActor(rect1);
		}

		@Override
		public void draw(Batch batch) {
			super.draw(batch);
			world.debugRender(stage.getCamera());
		}
	}

	class PhysicsRect extends PhysicsObject {
		private Texture texture = null;

		public PhysicsRect() {
			super(new NoneType(true));

			texture = new Texture(Gdx.files.internal("badlogic.jpg"));
		}

		@Override
		public BodyDef getBodyDef() {
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.DynamicBody;
			bodyDef.bullet = true;
			return bodyDef;
		}

		@Override
		public FixtureDef getFixtureDef() {
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = PhysicsUtils.getDefaultPolygonShape();
			fixtureDef.isSensor = true;
			return fixtureDef;
		}

		public void init(int x, int y) {
			super.init();
			setSize(5f * 2, 4f * 2);
			setOrigin(Align.center);
			setCurrentPos(x, y);
			PhysicsUtils.updateRectShape(getShape(), 5f, 4f);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			DrawUtils.drawActor(batch, texture, this);
		}

		@Override
		public void beginContact(Contact contact, boolean isObjectA) {

		}

		@Override
		public void endContact(Contact contact, boolean isObjectA) {

		}
	}
}
