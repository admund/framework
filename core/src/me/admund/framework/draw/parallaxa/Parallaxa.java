package me.admund.framework.draw.parallaxa;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import me.admund.framework.draw.DrawObject;
import me.admund.framework.physics.PhysicsWorld;

/**
 * Created by admund on 2015-01-22.
 */
public class Parallaxa extends DrawObject {
    private Array<ParallaxaLayer> layerList = new Array<ParallaxaLayer>();

    public void addLayer(ParallaxaLayer layer) {
        layerList.add(layer);
        layer.init(0, 0);
    }

    public void updatePos(Vector3 cameraTransition) {
        for(int i=0; i<layerList.size; i++) {
            ParallaxaLayer layer = layerList.get(i);
            layer.updatePos(cameraTransition.x * PhysicsWorld.WORLD_TO_BOX);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //System.out.println(layerList.size);
        for(int i=0; i<layerList.size; i++) {
            ParallaxaLayer layer = layerList.get(i);
            layer.draw(batch, parentAlpha);
        }
    }
}
