package me.admund.framework.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by admund on 2014-12-23.
 */
public class PhysicsUtils {

    // SHAPES
    public static CircleShape getDefaultCircleShape() {
        CircleShape shape = new CircleShape();
        shape.setRadius(1f);
        return shape;
    }

    public static PolygonShape getDefaultPolygonShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f, 1f);
        return shape;
    }

    public static void updateCircleShape(Shape shape, float radius) {
        CircleShape shapeCircle = (CircleShape)shape;
        shapeCircle.setRadius(radius);
    }

    public static void updateRectShape(Shape shape, float sizeX, float sizeY) {
        PolygonShape shapeRect = (PolygonShape)shape;
        shapeRect.setAsBox(sizeX, sizeY);
    }

    public static void updateRectShape(Shape shape, Vector2[] verticles) {
        PolygonShape shapeRect = (PolygonShape)shape;
        shapeRect.set(verticles);
    }

    // ANGLES
    public static float getAngleDiffAbs(float firstAngle, float secondAngle) {
        if(firstAngle > 360f) {
            firstAngle = firstAngle - (int)(firstAngle/360f)*360f;
        }

        if(secondAngle > 360f) {
            firstAngle = firstAngle - (int)(firstAngle/360f)*360f;
        }

        float result = Math.abs(firstAngle - secondAngle);
        if(result > 180f) {
            result = 360f - result;
        }
        return Math.abs(result);
    }
}
