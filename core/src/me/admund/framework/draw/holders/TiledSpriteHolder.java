package me.admund.framework.draw.holders;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.admund.framework.GameUtils;
import me.admund.framework.draw.DrawUtils;
import me.admund.framework.draw.animations.AnimationState;

/**
 * Created by admund on 2015-08-22.
 */
public class TiledSpriteHolder extends AbstractSpriteHolder {

    private float objectWidth = 0;
    private float objectHight = 0;

    private float tileWidth = 0;
    private float tileHight = 0;

    private float tileWidthCnt = 0;
    private float tileHightCnt = 0;

    private float posX = 0;
    private float posY = 0;

    public TiledSpriteHolder(String textureName) {
        if(textureName != null) {
            addSprite(new Sprite(GameUtils.assetsManager.getTextureRegionFromMain(textureName)));
        }
    }

    @Override
    public void draw(Batch batch, float parentalAplha) {
        Sprite sprite = getSpriteList().get(0);
        // TODO hack na niezgrywajace sie czesci
        //sprite.setSize(tileWidth + 2, tileHight + 2);
        sprite.setSize(tileWidth, tileHight);
        for (int i = 0; i < tileWidthCnt; i++) {
            for (int j = 0; j < tileHightCnt; j++) {
                // TODO hack na niezgrywajace sie czesci
                //sprite.setPosition(posX + (i * tileWidth) - 1, posY + (j * tileHight) - 1);
                sprite.setPosition(posX + (i * tileWidth), posY + (j * tileHight));
                if (DrawUtils.isVisible(sprite)) {
                    sprite.draw(batch);
                }
            }
        }
    }

    @Override
    public void changeAnimationState(AnimationState state) {}

    @Override
    public void act(float delta) {}

    @Override
    public void updatePosition(float x, float y, float rotation) {
        super.updatePosition(x, y, rotation);
        posX = x;
        posY = y;
    }

    @Override
    public void updateSize(float width, float hight) {
        super.updateSize(width, hight);
        setObjectSize(width, hight);
    }

    public void setObjectSize(float objectWidth, float objectHight) {
        this.objectWidth = objectWidth;
        this.objectHight = objectHight;
        calculateTileSize();
    }

    private void calculateTileSize() {
        tileWidthCnt = (int) (objectWidth / (getSpriteWidth()*10f));
        tileWidthCnt = tileWidthCnt == 0 ? 1 : tileWidthCnt;
        tileWidth = objectWidth / tileWidthCnt;

        tileHightCnt = (int) (objectHight / (getSpriteHeight()*10f));
        tileHightCnt = tileHightCnt == 0 ? 1 : tileHightCnt;
        tileHight = objectHight / tileHightCnt;

        tileWidth = optimize(tileWidth, tileHight);
        tileWidthCnt = objectWidth / tileWidth;
        tileHight = optimize(tileHight, tileWidth);
        tileHightCnt = objectHight / tileHight;
    }

    private float optimize(float tileSize1, float tileSize2) {
        float ratio = tileSize1/tileSize2;
        if(ratio >= 2) {
            int ratioInt = (int)ratio;
            return tileSize1/ratioInt;
        }
        return tileSize1;
    }
}