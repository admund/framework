package me.admund.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import me.admund.framework.achievements.IAchievementsProvider;
import me.admund.framework.assets.FrameworkAssetsManager;
import me.admund.framework.draw.DrawUtils;
import me.admund.framework.draw.SpriteList;
import me.admund.framework.draw.particle.FrameworkParticleManager;
import me.admund.framework.game.AbstractGame;
import me.admund.framework.physics.*;
import me.admund.framework.scenes.AbstractScene;
import me.admund.framework.scenes.IScene;
import me.admund.framework.scenes.ScenesManager;
import me.admund.framework.sounds.FrameworkSoundsManager;

public class FrameworkTest extends AbstractGame {

	public FrameworkTest(IAchievementsProvider achievementsProvider) {
		super(achievementsProvider);
	}

	@Override
	protected FrameworkAssetsManager createAssetManager() {
		return new FrameworkAssetsManager() {
			@Override
			public void init() {}
			@Override
			public void load() {}
		};
	}

	@Override
	protected FrameworkSoundsManager createSoundsManager() {
		return new FrameworkSoundsManager() {
			@Override
			public void init() {}
		};
	}

	@Override
	protected FrameworkParticleManager createParticleManager() {
		return new FrameworkParticleManager() {
			@Override
			public void init() {}
		};
	}

	@Override
	public void create () {
		ScenesManager.inst().push(new TestScene(), true);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	protected IScene createFirstScene() {
		return new TestScene();
	}

	class TestScene extends AbstractScene {
		private PhysicsWorld world = null;

		@Override
		public void create() {
			createGroups(new String[]{"MAIN"});
			world = new PhysicsWorld(new FrameworkTestReuseFactory());

			PhysicsRect rect1 = (PhysicsRect)world.getPhysicsObject(PhysicsRect.class.toString());
			rect1.init(35, 20);
			addActor("MAIN", rect1);
		}

		@Override
		public void draw(Batch batch) {
			super.draw(batch);
			world.debugRender(getCurrentCamera());
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
