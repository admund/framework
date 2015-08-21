package me.admund.framework.physics;

/**
 * Created by admund on 2015-08-10.
 */
public class Aligment {
    public static final Aligment CENTER = new Aligment(.5f, .5f);
    public static final Aligment LEFT_TOP = new Aligment(0f, 1f);
    public static final Aligment LEFT_BOTTOM = new Aligment(0f, 0f);
    public static final Aligment RIGHT_TOP = new Aligment(1f, 1f);
    public static final Aligment RIGHT_BOTTOM = new Aligment(1f, 0f);
    public static final Aligment TOP = new Aligment(.5f, 1f);
    public static final Aligment BOTTOM = new Aligment(.5f, 0f);
    public static final Aligment LEFT = new Aligment(0f, .5f);
    public static final Aligment RIGHT = new Aligment(1f, .5f);

    private float aligmentX = 0;
    private float aligmentY = 0;

    public static Aligment create(float aligmentX, float aligmentY) {
        return new Aligment(aligmentX, aligmentY);
    }

    public Aligment(float aligmentX, float aligmentY) {
        this.aligmentX = aligmentX;
        this.aligmentY = aligmentY;
    }

    public float getAligmentX() {
        return aligmentX;
    }

    public float getAligmentY() {
        return aligmentY;
    }
}
