package me.admund.framework.draw.parallaxa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import me.admund.framework.physics.PhysicsWorld;

/**
 * Created by admund on 2015-01-22.
 */
public abstract class ParallaxLayer {
    protected float regionSizeX = 0;
    protected float regionSizeY = 0;
    protected float posY = 0;

    private float value = 0f;
    private float screenSize = 0;

    private float currentLayerPosX = 0;
    private float layerLeftBuffor = 0;
    private float layerRightBuffor = 0;

    private Array<ParallaxaLayerRegion> regionList = null;

    public ParallaxLayer(float regionSizeX, float regionSizeY, float value) {
        this(regionSizeX, regionSizeY, 0, value);
    }

    public ParallaxLayer(float regionSizeX, float regionSizeY, float posY, float value) {
        this.value = value;
        this.posY = posY;
        this.regionSizeX = regionSizeX;
        this.regionSizeY = regionSizeY;
        this.screenSize = Gdx.graphics.getWidth() * PhysicsWorld.SCREEN_TO_BOX;

        this.regionList = new Array<ParallaxaLayerRegion>();
    }

    public abstract ParallaxaLayerRegion create(float startPosX);

    public void updatePos(float cameraTransposition) {
        float layerTansposiotion = cameraTransposition * value;
        currentLayerPosX += cameraTransposition;
        layerLeftBuffor += (cameraTransposition - layerTansposiotion);
        layerRightBuffor -= (cameraTransposition - layerTansposiotion);
        updateRegions(layerTansposiotion);
        addRegion();
        removeRegion();
    }

    public void draw(Batch batch, float parentAlpha) {
        for(int i=0; i< regionList.size; i++) {
            regionList.get(i).draw(batch, parentAlpha);
        }
    }

    private void updateRegions(float cameraTransposition) {
        for(int i=0; i<regionList.size; i++) {
            regionList.get(i).updatePos(cameraTransposition);
        }
    }

    private void addRegion() {
        while (layerLeftBuffor < screenSize) {
            regionList.add(create(currentLayerPosX - layerLeftBuffor - regionSizeX));

            System.out.println("addRegion 1 " + currentLayerPosX + " " + (currentLayerPosX - layerLeftBuffor - regionSizeX));

            layerLeftBuffor += regionSizeX;
        }

        while(layerRightBuffor < screenSize) {
            regionList.add(create(currentLayerPosX + layerRightBuffor));

            System.out.println("addRegion 2 " + currentLayerPosX + " " + (currentLayerPosX + layerRightBuffor));

            layerRightBuffor += regionSizeX;
        }
    }

    private void removeRegion() {
        Array<ParallaxaLayerRegion> removeList = new Array<ParallaxaLayerRegion>();
        float rangeLeft = currentLayerPosX - screenSize;
        float rangeRight = currentLayerPosX + screenSize;

        for(int i=0; i< regionList.size; i++) {
            ParallaxaLayerRegion tmpRegion = regionList.get(i);
            float spriteLeftEdge = tmpRegion.getX();
            float spriteRightEdge = spriteLeftEdge + tmpRegion.getWidth();
            if(rangeLeft > spriteRightEdge) {
                removeList.add(tmpRegion);

                System.out.println("removeRegion 2");
            } else if(rangeRight < spriteLeftEdge) {
                removeList.add(tmpRegion);

                System.out.println("removeRegion 1");
            }
        }
        regionList.removeAll(removeList, true);
    }
}
