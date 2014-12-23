package me.admund.framework.me.admund.framework.physics;

/**
 * Created by admund on 2014-12-23.
 */
public class PhysicsObjectInfo {
    private PhysicsObject obj = null;
    private AType type = null;
    private boolean toReuse = false;

    public PhysicsObjectInfo() {}

    public PhysicsObjectInfo setType(AType type) {
        this.type = type;
        return this;
    }

    public AType getType() {
        return type;
    }

    public PhysicsObjectInfo setObj(PhysicsObject obj) {
        this.obj = obj;
        return this;
    }

    public PhysicsObject getObj() {
        return obj;
    }

    public void prepereToReuse() {
        getObj().prepereToReuse();
    }

    public void setToReuse() {
        this.toReuse = true;
    }

    public boolean isSetToReuse() {
        return toReuse;
    }
}
