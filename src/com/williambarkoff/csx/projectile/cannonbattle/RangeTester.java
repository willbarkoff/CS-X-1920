package com.williambarkoff.csx.projectile.cannonbattle;

import com.williambarkoff.csx.projectile.Particle;
import com.williambarkoff.csx.util.ColorFun;
import com.williambarkoff.csx.util.ShapeFun;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import javax.swing.*;
import java.awt.*;

public class RangeTester extends AbstractSimulation {
    private Attempt[] attempts;
    private Cannon cannon;
    private int precision;
    private double deltaTStep;
    private PlotFrame pframe = new PlotFrame("", "", "Range Tester");
    private double dist;

    public static void main(String[] args) {
        try {
            JOptionPane.showMessageDialog(null, "First, input the simulation settings");
            int precision = Integer.parseInt(JOptionPane.showInputDialog(null, "Simulation Precision", 90));
            double simulationStep = Double.parseDouble(JOptionPane.showInputDialog(null, "Simulation Step", 0.1));
            double gravity = Double.parseDouble(JOptionPane.showInputDialog(null, "Gravity", -9.81));
            JOptionPane.showMessageDialog(null, "Now, input the CSA cannon details.\nPreloaded values are for a 10-pounder Parrott rifle");
            Cannon CSA = new Cannon(
                    Double.parseDouble(JOptionPane.showInputDialog(null, "Muzzle Velocity", 374.904)),
                    Double.parseDouble(JOptionPane.showInputDialog(null, "X Beta", 1.0)),
                    Double.parseDouble(JOptionPane.showInputDialog(null, "Y Beta", 1.0)),
                    Double.parseDouble(JOptionPane.showInputDialog(null, "Projectile mass", 4.309127515)),
                    gravity);
            JOptionPane.showMessageDialog(null, "Now, input the USA cannon details.\nPreloaded values are for a 12-pounder Howitzer");
            Cannon USA = new Cannon(
                    Double.parseDouble(JOptionPane.showInputDialog(null, "Muzzle Velocity", 321.2592)),
                    Double.parseDouble(JOptionPane.showInputDialog(null, "X Beta", 1.0)),
                    Double.parseDouble(JOptionPane.showInputDialog(null, "Y Beta", 1.0)),
                    Double.parseDouble(JOptionPane.showInputDialog(null, "Projectile mass", 4.036972093)),
                    gravity);
            JOptionPane.showMessageDialog(null, "Next, we'll test the CSA cannon's range.");
            double CSARange = testRange(CSA, precision, simulationStep);
            JOptionPane.showMessageDialog(null, "Now the USA's range.");
            double USARange = testRange(USA, precision, simulationStep);
            JOptionPane.showMessageDialog(null, "Here are the results:\nCSA\t" + CSARange + "\nUSA\t" + USARange);
            PlotFrame pframe = new PlotFrame("", "", "Cannons");
            double maxRange = Math.max(CSARange, USARange);
            pframe.setPreferredMinMaxY(0, 10);
            pframe.setPreferredMinMaxX(-0.75*maxRange, 0.75*maxRange);
            DrawableShape midline = ShapeFun.createRectangle(0,0,1,10);
            midline.edgeColor = ColorFun.Black;
            DrawableShape csa = ShapeFun.createRectangle(-CSARange/2,0,1,10);
            csa.edgeColor = ColorFun.Gray;
            DrawableShape usa = ShapeFun.createRectangle(USARange/2,0,1,10);
            usa.edgeColor = ColorFun.Blue;
            DrawableShape usa2 = ShapeFun.createRectangle(-USARange/2,0,1,5);
            usa2.edgeColor = ColorFun.Blue;
            DrawableShape csa2 = ShapeFun.createRectangle(CSARange/2,0,1,5);
            csa2.edgeColor = ColorFun.Gray;
            pframe.addDrawable(midline);
            pframe.addDrawable(csa);
            pframe.addDrawable(usa);
            pframe.addDrawable(csa2);
            pframe.addDrawable(usa2);
            pframe.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "You must input numbers.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
    }


    public RangeTester(Cannon cannon, int precision, double deltaTStep) {
        this.cannon = cannon;
        this.precision = precision;
        this.deltaTStep = deltaTStep;
    }

    public static double testRange(Cannon cannon, int precision, double deltaTStep) {
        RangeTester rt = new RangeTester(cannon, precision, deltaTStep);
        rt.initialize();
        rt.startSimulation();
        while (rt.isRunning()) {
            //
        }
        return rt.dist;
    }


    @Override
    public void initialize() {
//        control.println("Testing " + cannonName);

        attempts = null;
        pframe.clearDrawables();

        double distBetweenBalls = (Math.PI / 2) / (double) precision;

        attempts = new Attempt[precision];

        for (int i = 0; i < 90; i++) {
            double theta = (double) i * distBetweenBalls;
            attempts[i] = new Attempt(theta);
            pframe.addDrawable(attempts[i].getCircle());
            pframe.addDrawable(attempts[i].getTrail());
        }

        pframe.setPreferredMinMax(-100,1500,0,1500);

        pframe.setVisible(true);

    }

    @Override
    protected void doStep() {
        double deltaT = deltaTStep;
        for (Attempt particle : attempts) {
            particle.step(deltaT);
        }

        double farthestParticle = 0;
        for (Attempt particle : attempts) {
            if (!particle.stopped) {
                return;
            }
            if(particle.getX() > farthestParticle) {
                farthestParticle = particle.getX();
            }
        }

        // all the particles are done.

        dist = farthestParticle;
        stopSimulation();
    }

    private class Attempt extends Particle {
        boolean isGood = false;
        boolean stopped = false;
        double theta;
        private Circle circle = new Circle();
        private Trail trail = new Trail();

        Attempt(double theta) {
            super(
                    0,
                    0,
                    cannon.getMuzzleVelocity() * Math.cos(theta),
                    cannon.getMuzzleVelocity() * Math.sin(theta),
                    0,
                    cannon.getGravity(),
                    cannon.getxBeta(),
                    cannon.getyBeta(),
                    cannon.getProjectileMass()
            );
            this.theta = theta;
            circle.color = Color.MAGENTA;
            trail.color = Color.GRAY;
            trail.addPoint(0, 0);
        }

        @Override
        public void step(double deltaT) {
            if (!stopped) {
                super.step(deltaT);
                double x = super.getX();
                double y = super.getY();
                circle.setX(x);
                circle.setY(y);

                if (y < 0) {
                    stopped = true;
                    circle.setY(0);
                    y = 0;
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
