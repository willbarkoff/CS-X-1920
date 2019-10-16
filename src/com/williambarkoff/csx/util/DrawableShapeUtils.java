package com.williambarkoff.csx.util;

import org.opensourcephysics.display.DrawableShape;

import java.awt.*;

public class DrawableShapeUtils {
    public static DrawableShape createRectangle(double x, double y, double width, double height) {
        return DrawableShape.createRectangle(x + (width/2), y + (height/2), width, height);
    }

    public static DrawableShape createCircle(double x, double y, double diameter) {
        return DrawableShape.createCircle(x, y, diameter);
    }
}
