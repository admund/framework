package me.admund.framework.draw.parallaxa;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import me.admund.framework.draw.DrawObject;

/**
 * Created by Adik on 4/25/2015.
 */
public class ParallaxaLayerRegion extends DrawObject {

    private Array<DrawObject> drawObjectList = null;

    public ParallaxaLayerRegion() {
        this.drawObjectList = new Array<DrawObject>();
    }

    public void init(float posX, float posY, float width, float height) {
        setPosition(posX, posY);
        setSize(width, height);
    }

    public void updatePos(float layerTransposition) {
        setPosition(getX() + layerTransposition, getY());
        for(int i=0; i<drawObjectList.size; i++) {
            DrawObject tmp = drawObjectList.get(i);
            tmp.setPosition(tmp.getX() + layerTransposition, tmp.getY());
        }
    }

    public void add(DrawObject object) {
        if(!drawObjectList.contains(object, true)) {
            drawObjectList.add(object);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(int i=0; i<drawObjectList.size; i++) {
            drawObjectList.get(i).draw(batch, parentAlpha);
        }
    }
}
