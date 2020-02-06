package com.williambarkoff.csx.projectile;

import com.williambarkoff.csx.util.ColorFun;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

public class ProjectileAppVertical extends AbstractSimulation {
    private Particle red;
    private Particle green;
    private Particle blue;

    private PlotFrame pframe = new PlotFrame("", "", "Simulation");
    private PlotFrame acceleration;
    private PlotFrame velocities;
    private PlotFrame positions;

    private Trail redA = new Trail();
    private Trail redV = new Trail();
    private Trail redY = new Trail();

    private Trail greenA = new Trail();
    private Trail greenV = new Trail();
    private Trail greenY = new Trail();

    private Trail blueA = new Trail();
    private Trail blueV = new Trail();
    private Trail blueY = new Trail();

    private Circle redC = new Circle();
    private Circle greenC = new Circle();
    private Circle blueC = new Circle();

    private double time = 0.0;

    public static void main(String[] args) {
        SimulationControl.createApp(new ProjectileAppVertical());
    }

    @Override
    public void reset() {
        control.setValue("y0", 10.0);
        control.setValue("vy0", 0.0);
        control.setValue("ay0", -9.81);
        control.setValue("mass", 1.0);
        control.setValue("red beta", 0.0);
        control.setValue("green beta", 0.0);
        control.setValue("blue beta", 1.0);

        redV = new Trail();
        redY = new Trail();
        redA = new Trail();

        greenA = new Trail();
        greenV = new Trail();
        greenY = new Trail();

        blueA = new Trail();
        blueV = new Trail();
        blueY = new Trail();

        acceleration = new PlotFrame("time (s)", "acceleration (m/s/s)", "Acceleration");
        velocities = new PlotFrame("time (s)", "velocity (m/s)", "Velocity");
        positions = new PlotFrame("time (s)", "position (m)", "Position");
    }

    @Override
    public void initialize() {
        double y0 = control.getDouble("y0");
        double vy0 = control.getDouble("vy0");
        double ay0 = control.getDouble("ay0");
        double mass = control.getDouble("mass");
        double redBeta = control.getDouble("red beta");
        double greenBeta = control.getDouble("green beta");
        double blueBeta = control.getDouble("blue beta");

        red = new Particle(0, y0, 0, vy0, 0, ay0, 0, redBeta, mass);
        green = new Particle(0, y0, 0, vy0, 0, ay0, 0, greenBeta, mass);
        blue = new Particle(0, y0, 0, vy0, 0, ay0, 0, blueBeta, mass);

        redC.color = ColorFun.Red;
        redA.color = ColorFun.Red;
        redV.color = ColorFun.Red;
        redY.color = ColorFun.Red;
        greenC.color = ColorFun.Green;
        greenA.color = ColorFun.Green;
        greenV.color = ColorFun.Green;
        greenY.color = ColorFun.Green;
        blueC.color = ColorFun.Blue;
        blueA.color = ColorFun.Blue;
        blueV.color = ColorFun.Blue;
        blueY.color = ColorFun.Blue;

        redC.setX(1);
        greenC.setX(2);
        blueC.setX(3);

        redC.setY(y0);
        greenC.setY(y0);
        blueC.setY(y0);

        pframe.addDrawable(redC);
        pframe.addDrawable(greenC);
        pframe.addDrawable(blueC);

        pframe.setPreferredMinMax(0, 4, 0, y0);

        acceleration.addDrawable(redA);
        acceleration.addDrawable(greenA);
        acceleration.addDrawable(blueA);

        velocities.addDrawable(redV);
        velocities.addDrawable(greenV);
        velocities.addDrawable(blueV);

        positions.addDrawable(redY);
        positions.addDrawable(greenY);
        positions.addDrawable(blueY);

        positions.setPreferredMinMaxY(0, y0);
    }

    @Override
    protected void doStep() {
        double deltaT = 0.1;

        time += deltaT;

        green.setyBeta(Math.abs(green.getVy()));

        red.step(deltaT);
        blue.step(deltaT);
        green.step(deltaT);

        redC.setY(red.getY());
        blueC.setY(blue.getY());
        greenC.setY(green.getY());

        redA.addPoint(time, red.getAy());
        greenA.addPoint(time, green.getAy());
        blueA.addPoint(time, blue.getAy());
        redV.addPoint(time, red.getVy());
        greenV.addPoint(time, green.getVy());
        blueV.addPoint(time, blue.getVy());
        redY.addPoint(time, red.getY());
        greenY.addPoint(time, green.getY());
        blueY.addPoint(time, blue.getY());

        acceleration.setPreferredMinMaxX(0, time);
        velocities.setPreferredMinMaxX(0, time);
        positions.setPreferredMinMaxX(0, time);
    }
}
