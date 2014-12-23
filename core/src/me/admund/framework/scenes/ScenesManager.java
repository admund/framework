package me.admund.framework.scenes;

import java.util.Stack;

/**
 * Created by admund on 2014-12-23.
 */
public class ScenesManager {

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
        sceneStack.peek().refresh();
    }
}
