package me.admund.framework.scenes;

import com.badlogic.gdx.utils.Disposable;

import java.util.Stack;

/**
 * Created by admund on 2014-12-23.
 */
public class ScenesManager implements Disposable {

    public static ScenesManager inst = new ScenesManager();

    private ScenesManager() {}

    public static ScenesManager inst() {
        return inst;
    }

    private Stack<IScene> sceneStack = new Stack<IScene>();

    public void push(IScene scene, boolean isNew) {
        sceneStack.push(scene);
        if(isNew) {
            scene.create();
        }
    }

    public IScene peek() {
        return sceneStack.peek();
    }

    public void pop() {
        sceneStack.pop().dispose();
        if(!sceneStack.isEmpty()) {
            sceneStack.peek().refresh();
        }
    }

    @Override
    public void dispose() {
        while(!sceneStack.isEmpty()) {
            sceneStack.pop().dispose();
        }
    }
}
