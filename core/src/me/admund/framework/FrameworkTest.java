package me.admund.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import me.admund.framework.draw.DrawUtils;
import me.admund.framework.draw.SpriteList;
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
			world = new PhysicsWorld(new FrameworkTestReuseFactory());

			PhysicsRect rect1 = (PhysicsRect)world.getPhysicsObject(PhysicsRect.class.toString());
			rect1.init(35, 20);
			stage.addActor(rect1);
		}

		@Override
		public void draw(Batch batch) {
			super.draw(batch);
			world.debugRender(stage.getCamera());
		}
	}

	class FrameworkTestReuseFactory extends ReuseFactory {
		@Override
		public PhysicsObject createNewObj(String className) {
			PhysicsObject obj = null;
			if(className.equals(PhysicsRect.class.toString())) {
				obj = new PhysicsRect();
			}
			return obj;
		}
	}

	class PhysicsRect extends PhysicsObject {
		private SpriteList spriteList = new SpriteList();

		public PhysicsRect() {
			super(new NoneType(true));
			spriteList.add(new Sprite(new Texture(Gdx.files.internal("badlogic.jpg"))));
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
			DrawUtils.draw(batch, spriteList);
		}

		@Override
		public void beginContact(Contact contact, boolean isObjectA) {}

		@Override
		public void endContact(Contact contact, boolean isObjectA) {}
	}
}
