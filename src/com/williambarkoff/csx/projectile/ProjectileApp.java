package com.williambarkoff.csx.projectile;

import com.williambarkoff.csx.util.ColorFun;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

public class ProjectileApp extends AbstractSimulation {
    private double step;
    private double floor;
    private PlotFrame pframe = new PlotFrame("", "", "Simulation");
    private Particle particle;
    private Circle circle = new Circle();
    private Trail trail = new Trail();

    public static void main(String[] args) {
        SimulationControl.createApp(new ProjectileApp());
    }


    @Override
    public void reset() {
        control.setValue("θ", 45.0);
        control.setValue("θ in radians", false);
        control.setValue("x position", 0.0);
        control.setValue("y position", 0.0);
        control.setValue("initial velocity", 10.0);
        control.setValue("β (x)", 1.0);
        control.setValue("β (y)", 1.0);
        control.setValue("x acceleration", 0.0);
        control.setValue("y acceleration", -9.81);
        control.setValue("mass", 1.0);
        control.setValue("particle color", "#123456");
        control.setValue("trail color", "#FF0000");
        control.setValue("simulation step", 0.1);
        control.setValue("y floor", 0.0);
    }

    @Override
    public void initialize() {
        double theta = control.getDouble("θ");
        boolean thetaIsRad = control.getBoolean("θ in radians");
        double x0 = control.getDouble("x position");
        double y0 = control.getDouble("y position");
        double v0 = control.getDouble("initial velocity");
        double bx = control.getDouble("β (x)");
        double by = control.getDouble("β (y)");
        double xa = control.getDouble("x acceleration");
        double ya = control.getDouble("y acceleration");
        double ma = control.getDouble("mass");
        step = control.getDouble("simulation step");
        floor = control.getDouble("y floor");

        if (!thetaIsRad) {
            theta *= Math.PI / 180.0;
        }

        particle = new Particle(x0, y0, v0 * Math.cos(theta), v0 * Math.sin(theta), xa, ya, bx, by, ma);

        try {
            circle.color = ColorFun.parseHex(control.getString("particle color"));
            trail.color = ColorFun.parseHex(control.getString("trail color"));
        } catch (ColorFun.InvalidHexException e) {
            circle.color = ColorFun.Red;
            trail.color = ColorFun.Blue;
            control.println("Invalid color specified. Color must be in 6 character hex format.");
        }

        circle.setX(x0);
        circle.setY(y0);

        trail.clear();
        trail.addPoint(x0, y0);

        pframe.addDrawable(circle);
        pframe.addDrawable(trail);
        pframe.setPreferredMinMax(x0 - 5, x0 + 5, y0 - 5, y0 + 5);
    }

    @Override
    protected void doStep() {
        double deltaT = step;
        particle.step(deltaT);

        double x = particle.getX();
        double y = particle.getY();

        trail.addPoint(x, y);

        if (y <= floor) {
            stopSimulation();
            return;
        }

        circle.setX(x);
        circle.setY(y);

        pframe.setPreferredMinMax(trail.getXMin(), trail.getXMax(), trail.getYMin(), trail.getYMax());
    }
}
