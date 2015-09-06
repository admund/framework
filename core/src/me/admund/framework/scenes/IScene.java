package me.admund.framework.scenes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by admund on 2014-12-23.
 */
public interface IScene extends Disposable {
    void act(float deltaTime);
    void draw(Batch batch);
    void resize(int width, int height);
    void refresh();
    void create();
}
