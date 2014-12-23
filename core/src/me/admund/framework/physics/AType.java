package me.admund.framework.me.admund.framework.physics;

/**
 * Created by admund on 2014-12-23.
 */
public abstract class AType {

    protected String typeName = "";

    AType(boolean fake) {}

    public boolean equals(AType type) {
        return typeCode().contains(type.typeCode());
    }

    public String typeCode() {
        return typeName;
    }
}
