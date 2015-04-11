package me.admund.framework.draw.holders;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import me.admund.framework.GameUtils;
import me.admund.framework.draw.animations.AnimationState;
import me.admund.framework.physics.PhysicsWorld;

/**
 * Created by admund on 2015-02-02.
 */
public class ParallaxaSpriteHolder extends AbstractSpriteHolder {
    private Sprite sampleSprite = null;
    private float sampleSizeX = 0;
    private float sampleSizeY = 0;
    private float posX = 0;
    private float posY = 0;
    private float range = 0;
    private float leftBuffor = 0;
    private float rightBuffor = 0;

    public ParallaxaSpriteHolder(String textureName) {
        this.sampleSprite = new Sprite(GameUtils.assetsManager.getTextureRegion(textureName));
    }

    public void init(float startX, float startY, float sampleSizeX, float sampleSizeY, float range) {
        this.posX = startX;
        this.posY = startY;
        this.range = range;
        this.sampleSizeX = sampleSizeX;
        this.sampleSizeY = sampleSizeY;
        sampleSprite.setSize(sampleSizeX * PhysicsWorld.BOX_TO_SCREEN, sampleSizeY * PhysicsWorld.BOX_TO_SCREEN);
        updateSprites(0);
    }

    @Override
    public void changeAnimationState(AnimationState state) {}

    public void updatePosX(float diff, float cameraTransposition) {
        posX += cameraTransposition;
        leftBuffor += (cameraTransposition - diff);
        rightBuffor -= (cameraTransposition - diff);
        updateSprites(diff);
    }

    @Override
    public void act(float delta) {}

    private void updateSpritesPos(float diff) {
        for(int i=0; i<spriteList.size; i++) {
            Sprite tmp = spriteList.get(i);
            tmp.setPosition(tmp.getX() + (diff * PhysicsWorld.BOX_TO_SCREEN), tmp.getY());
        }
    }

    private void updateSprites(float diff) {
        updateSpritesPos(diff);
        addSprites();
        removeSprites();
    }

    private void addSprites() {
        while(leftBuffor < range) {
            Sprite tmp = new Sprite(sampleSprite);
            tmp.setPosition((posX - leftBuffor - sampleSizeX) * PhysicsWorld.BOX_TO_SCREEN,
                    posY * PhysicsWorld.BOX_TO_SCREEN);
            addSprite(tmp);
            leftBuffor += sampleSizeX;
        }

        while(rightBuffor < range) {
            Sprite tmp = new Sprite(sampleSprite);
            tmp.setPosition((posX + rightBuffor) * PhysicsWorld.BOX_TO_SCREEN, posY * PhysicsWorld.BOX_TO_SCREEN);
            addSprite(tmp);
            rightBuffor += sampleSizeX;
        }
    }

    private void removeSprites() {
        Array<Sprite> removeList= new Array<Sprite>();
        float rangeLeft = (posX - range) * PhysicsWorld.BOX_TO_SCREEN;
        float rangeRight = (posX + range) * PhysicsWorld.BOX_TO_SCREEN;
        for(int i=0; i<spriteList.size; i++) {
            Sprite tmp = spriteList.get(i);
            float spriteLeftEdge = tmp.getX();
            float spriteRightEdge = spriteLeftEdge + tmp.getWidth();
            if(rangeLeft > spriteRightEdge) {
                removeList.add(tmp);
            } else if(rangeRight < spriteLeftEdge) {
                removeList.add(tmp);
            }
        }
        spriteList.removeAll(removeList, true);
    }
}
