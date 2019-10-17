package com.williambarkoff.csx.util;

import org.opensourcephysics.display.DrawableShape;

public class DrawableShapeUtils {
    public static DrawableShape createRectangle(double x, double y, double width, double height) {
        if (height < 0) {
            height = -height;
            y -= height;
        }

        if (width < 0) {
            width = -width;
            x -= width;
        }
        return DrawableShape.createRectangle(x + (width / 2), y + (height / 2), width, height);
    }

    public static DrawableShape createCircle(double x, double y, double diameter) {
        return DrawableShape.createCircle(x, y, diameter);
    }
}
