package me.admund.framework.physics;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by admund on 2015-01-18.
 */
public abstract class ReuseFactory implements Disposable {
    private PhysicsWorld world = null;

    private ObjectMap<String, Array<PhysicsObject>> objectMap = new ObjectMap<String, Array<PhysicsObject>>();

    public abstract PhysicsObject createNewObj(String className);

    public void setWorld(PhysicsWorld world) {
        this.world = world;
    }

    @Override
    public void dispose() {
        objectMap.clear();
    }

    public PhysicsObject get(String className) {
        PhysicsObject obj = null;
        Array<PhysicsObject> tmpArray = objectMap.get(className);
        if(tmpArray == null) {
            tmpArray = new Array<PhysicsObject>();
            objectMap.put(className, tmpArray);
        }

        obj = getReusableObject(tmpArray);
        if(obj == null) {
            obj = createNewObj(className);
            obj.create(world);
            tmpArray.add(obj);
        }

        if(obj == null) {
            throw new RuntimeException("ReuseFactory didn't implement create function for " + className + " class");
        }
        return obj;
    }

    private PhysicsObject getReusableObject(Array<PhysicsObject> list) {
        PhysicsObject result = null;
        for(int i=0; i<list.size; i++) {
            PhysicsObject tmp = list.get(i);
            if(tmp.canBeReuse()) {
                result = tmp;
                break;
            }
        }
        return result;
    }
}
