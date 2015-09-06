package me.admund.framework;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Align;
import me.admund.framework.achievements.IAchievementsProvider;
import me.admund.framework.assets.FrameworkAssetsManager;
import me.admund.framework.draw.holders.SimpleSpriteHolder;
import me.admund.framework.draw.particle.FrameworkParticleManager;
import me.admund.framework.game.AbstractGame;
import me.admund.framework.physics.PhysicsObject;
import me.admund.framework.physics.PhysicsWorld;
import me.admund.framework.physics.ReuseFactory;
import me.admund.framework.physics.objects.PhysicsRect;
import me.admund.framework.scenes.AbstractScene;
import me.admund.framework.scenes.IScene;
import me.admund.framework.sounds.FrameworkSoundsManager;

public class FrameworkTest extends AbstractGame {
	private final static String MAIN_ATLAS_NAME = "all.atlas";

	public FrameworkTest(IAchievementsProvider achievementsProvider) {
		super(achievementsProvider);
	}

	@Override
	protected FrameworkAssetsManager createAssetManager() {
		return new FrameworkAssetsManager() {
			@Override
			public void init() {
				mainAtlas = get(MAIN_ATLAS_NAME, TextureAtlas.class);
			}
			@Override
			public void load() {
				load(MAIN_ATLAS_NAME, TextureAtlas.class);
			}
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
	protected IScene createFirstScene() {
		return new TestScene();
	}

	class TestScene extends AbstractScene {
		private PhysicsWorld world = null;

		@Override
		public void create() {
			createGroups(new String[]{"MAIN"});
			world = new PhysicsWorld(/*new Vector2(0, 9.8f), */new FrameworkTestReuseFactory());

			MrPotato rect1 = (MrPotato)world.getPhysicsObject(MrPotato.class.toString());
			rect1.init(35f, 20f);
			addActor("MAIN", rect1);
		}

		@Override
		public void act(float deltaTime) {
			world.step(deltaTime);
			super.act(deltaTime);
		}

		@Override
		public void draw(Batch batch) {
			super.draw(batch);
			world.debugRender(getCurrentCamera());
		}
	}

	// TEST REUSE FACTORY
	class FrameworkTestReuseFactory extends ReuseFactory {
		@Override
		public PhysicsObject createNewObj(String className) {
			PhysicsObject obj = null;
			if(className.equals(MrPotato.class.toString())) {
				obj = new MrPotato();
			}
			return obj;
		}
	}

	// TEST OBJECT
	class MrPotato extends PhysicsRect {
		public void init(float x, float y) {
			super.init(x, y, 0, 0);
			setSpriteHolder(new SimpleSpriteHolder("mr_potato"), true);
			setOrigin(Align.center);
			getBody().setAngularVelocity(-3);
		}

		@Override
		public BodyDef getBodyDef() {
			BodyDef bodyDef = super.getBodyDef();
			bodyDef.type = BodyDef.BodyType.DynamicBody;
			return bodyDef;
		}
	}
}
