package me.admund.framework.scenes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by admund on 2014-12-23.
 */
public interface IScene extends Disposable {
    public void act(float deltaTime);
    public void draw(Batch batch);
    public void resize(int width, int height);
    public void refresh();
    public void create();
}
