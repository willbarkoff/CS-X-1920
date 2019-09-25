package com.williambarkoff.csx.rustremoval;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class SpiralTrailAnimation extends AbstractSimulation {
    public static void main(String[] args) {
        SimulationControl.createApp(new SpiralTrailAnimation());
    }

    PlotFrame plotFrame = new PlotFrame("x", "y", "Spiral Trail");
    Trail trail = new Trail();
    int[][] points = new int[][]{
            new int[]{3, 3},
            new int[]{4, 3},
            new int[]{4, 4},
            new int[]{3, 4},
            new int[]{2, 4},
            new int[]{2, 3},
            new int[]{2, 2},
            new int[]{3, 2},
            new int[]{4, 2},
            new int[]{5, 2},
            new int[]{5, 3},
            new int[]{5, 4},
            new int[]{5, 5},
            new int[]{4, 5},
            new int[]{3, 5},
            new int[]{2, 5},
            new int[]{1, 5},
            new int[]{1, 4},
            new int[]{1, 3},
            new int[]{1, 2},
            new int[]{1, 1},
            new int[]{2, 1},
            new int[]{3, 1},
            new int[]{4, 1},
            new int[]{5, 1}
        };

    int counter = 0;


    @Override
    public void doStep() {
        try {
            trail.addPoint(points[counter][0], points[counter][1]);
            counter++;
        } catch (ArrayIndexOutOfBoundsException e) {
            trail.clear();
            counter = 0;
        }
    }

    @Override
    public void initialize() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                Circle circle = new Circle();
                circle.setX(i);
                circle.setY(j);
                plotFrame.addDrawable(circle);

            }
        }


        DrawableShape rect = DrawableShape.createRectangle(3.0, 3.0, 4.0, 4.0);
        plotFrame.addDrawable(rect);

        plotFrame.addDrawable(trail);

        plotFrame.setPreferredMinMax(0.0, 6.0, 0.0, 6.0);
    }
}
