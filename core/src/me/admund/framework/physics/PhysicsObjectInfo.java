package me.admund.framework.physics;

/**
 * Created by admund on 2014-12-23.
 */
public class PhysicsObjectInfo {
    private PhysicsObject obj = null;
    private boolean toReuse = false;

    public PhysicsObjectInfo() {}

    public PhysicsObjectInfo setObj(PhysicsObject obj) {
        this.obj = obj;
        return this;
    }

    public PhysicsObject getObj() {
        return obj;
    }

    public void prepereToReuse() {
        getObj().prepereToReuse();
        toReuse = false;
    }

    public void setToReuse() {
        this.toReuse = true;
    }

    public boolean isSetToReuse() {
        return toReuse;
    }
}
