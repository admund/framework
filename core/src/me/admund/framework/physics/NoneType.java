package me.admund.framework.physics;

/**
 * Created by admund on 2014-12-23.
 */
public class NoneType extends AType {
    public static String className = ":NoneType";
    public NoneType(boolean fake) {
        super(fake);
        typeName += className;
    }

}
