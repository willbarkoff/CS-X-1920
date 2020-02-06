package com.williambarkoff.csx.projectile;

import com.williambarkoff.csx.util.ColorFun;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;
import java.util.ArrayList;

public class GreenMonster extends AbstractSimulation {
    private PlotFrame pframe = new PlotFrame("", "", "Green Monster");
    private Attempt[] attempts;
    private double y0;
    private double x0;
    private double v0;
    private double bx;
    private double by;
    private double xa;
    private double ya;
    private double ma;
    private double step;
    private double vStep;
    private double wallHeight;
    private double wallDist;
    private int precision;
    private double maxHeight;

    public static void main(String[] args) {
        SimulationControl.createApp(new GreenMonster());
    }


    @Override
    public void reset() {
        control.setValue("x position", 1.0);
        control.setValue("y position", 0.0);
        control.setValue("initial velocity", 20);
        control.setValue("velocity step", 1.0);
        control.setValue("β (x)", 1.0);
        control.setValue("β (y)", 1.0);
        control.setValue("x acceleration", 0.0);
        control.setValue("y acceleration", -9.81);
        control.setValue("mass", 1.0);
        control.setValue("simulation step", 0.1);
        control.setValue("wall height", 10.0);
        control.setValue("wall distance", 10.0);
        control.setValue("precision", 90);
    }

    @Override
    public void initialize() {
        y0 = control.getDouble("y position");
        x0 = control.getDouble("x position");
        v0 = control.getDouble("initial velocity");
        bx = control.getDouble("β (x)");
        by = control.getDouble("β (y)");
        xa = control.getDouble("x acceleration");
        ya = control.getDouble("y acceleration");
        ma = control.getDouble("mass");
        step = control.getDouble("simulation step");
        vStep = control.getDouble("velocity step");
        wallHeight = control.getDouble("wall height");
        wallDist = control.getDouble("wall distance");
        precision = control.getInt("precision");
        setupExperiment();
    }

    private void setupExperiment() {
        attempts = null;
        pframe.clearDrawables();

        double distBetweenBalls = (Math.PI / 2) / (double) precision;

        attempts = new Attempt[precision];

        for (int i = 0; i < 90; i++) {
            double theta = (double) i * distBetweenBalls;
            attempts[i] = new Attempt(x0, y0, theta, xa, ya, bx, by, ma);
            pframe.addDrawable(attempts[i].getCircle());
            pframe.addDrawable(attempts[i].getTrail());
        }

        pframe.setPreferredMinMax(0, wallDist + 10, 0, wallHeight + 30);
        maxHeight = wallHeight;

        Trail wallDrawable = new Trail();
        wallDrawable.color = ColorFun.DarkOliveGreen;
        wallDrawable.setStroke(new BasicStroke(5));
        wallDrawable.addPoint(wallDist, 0);
        wallDrawable.addPoint(wallDist, wallHeight);
        wallDrawable.closeTrail();

        pframe.addDrawable(wallDrawable);
        control.clearMessages();
        control.println("Currently running velocity " + v0 + "m/s");

    }

    @Override
    protected void doStep() {
        double deltaT = step;
        for (Attempt particle : attempts) {
            particle.step(deltaT);
        }

        for (Attempt particle : attempts) {
            if (!particle.stopped) {
                return;
            }
        }

        // all particles are done

        ArrayList<Attempt> goodAttempts = new ArrayList<>();
        for (Attempt particle : attempts) {
            if (particle.isGood) {
                goodAttempts.add(particle);
            }
        }

        if (goodAttempts.size() == 0) {
            v0 += vStep;
            setupExperiment();
        } else {
            control.println("Initial Velocity: " + v0);
            control.println("Angles that worked (rad):");
            goodAttempts.forEach((a) -> control.println(a.theta + ""));
            stopSimulation();
        }
    }

    class Attempt extends Particle {
        boolean isGood = false;
        boolean stopped = false;
        double theta;
        private Circle circle = new Circle();
        private Trail trail = new Trail();

        Attempt(double x0, double y0, double theta, double ax0, double ay0, double xBeta, double yBeta, double mass) {
            super(x0, y0, v0 * Math.cos(theta), v0 * Math.sin(theta), ax0, ay0, xBeta, yBeta, mass);
            this.theta = theta;
            circle.color = Color.MAGENTA;
            trail.color = Color.GRAY;
            trail.addPoint(x0, y0);
        }

        @Override
        public void step(double deltaT) {
            if (!stopped) {
                super.step(deltaT);
                double x = super.getX();
                double y = super.getY();
                circle.setX(x);
                circle.setY(y);

                if (maxHeight < y) {
//                    pframe.setPreferredMinMaxY(0, y);
                    maxHeight = y;
                }

                if (y < 0) {
                    stopped = true;
                    isGood = false;
                    circle.color = Color.RED;
                    circle.setY(0);
                    y = 0;
                }
                if (x > wallDist) {
                    stopped = true;
                    if (y < wallHeight) {
                        isGood = false;
                        circle.color = Color.RED;
                    } else {
                        isGood = true;
                        circle.color = Color.GREEN;
                        trail.color = Color.GREEN;
                        trail.setStroke(new BasicStroke(2));
                    }
                    circle.setX(wallDist);
                    x = wallDist;
                }
                trail.addPoint(x, y);
            }
        }

        Circle getCircle() {
            return circle;
        }

        Trail getTrail() {
            return trail;
        }
    }
}
