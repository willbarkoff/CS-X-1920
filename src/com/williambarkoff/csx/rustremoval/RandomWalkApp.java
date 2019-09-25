package com.williambarkoff.csx.rustremoval;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.frames.PlotFrame;

import java.util.ArrayList;
import java.util.Random;

public class RandomWalkApp extends AbstractSimulation {
    private PlotFrame plotFrame = new PlotFrame("x", "y", "Random Walk");
    private ArrayList<MovingCircle> circles = new ArrayList<MovingCircle>();

    public static void main(String[] args) {
        SimulationControl.createApp(new RandomWalkApp());
    }

    @Override
    public void doStep() {
        circles.forEach(MovingCircle::move);
    }

    @Override
    public void initialize() {
        for(int i = 1; i <= 50; i++) {
            MovingCircle circle = new MovingCircle();
            circles.add(circle);
            plotFrame.addDrawable(circle);
        }
        plotFrame.setPreferredMinMax(-25.0, 25.0, -25.0, 25.0);
    }


    private class MovingCircle extends Circle {
        Random gen = new Random();

        public void move() {
            int randX = gen.nextInt(2);
            if(randX == 0) {
                this.x--;
            } else if(randX == 1) {
                this.x++;
            }

            int randY = gen.nextInt(2);
            if(randY == 0) {
                this.y--;
            } else if(randY == 1) {
                this.y++;
            }
        }
    }
}
