package com.williambarkoff.csx.projectile.cannonbattle;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.PlotFrame;

import javax.swing.*;
import java.util.Arrays;

public class CannonBattle extends AbstractSimulation {
    private PlotFrame pframe = new PlotFrame("", "", "CannonBattle");

    private Cannon CSA;
    private Cannon USA;

    private double CSARange;
    private double USARange;

    private int precision;
    private double simulationStep;

    public static void main(String[] args) {
        SimulationControl.createApp(new CannonBattle());
    }


    @Override
    public void reset() {
        // Preload CSA data for 10-pounder Parrott rifle
        control.setValue("[CSA] muzzle velocity",374.904);
        control.setValue("[CSA] β (x)", 1.0);
        control.setValue("[CSA] β (y)", 1.0);
        control.setValue("[CSA] projectile mass", 4.309127515);

        // Preload USA data for 12-pounder Howitzer
        control.setValue("[USA] muzzle velocity",321.2592);
        control.setValue("[USA] β (x)", 1.0);
        control.setValue("[USA] β (y)", 1.0);
        control.setValue("[USA] projectile mass", 4.036972093);

        control.setValue("gravity", -9.81);
        control.setValue("simulation step", 0.1);
        control.setValue("angle precision", 90);
    }

    @Override
    public void initialize() {
        RangeTester.testRange(new Cannon(10, 1, 1, 5, -9.81), 90, 0.1);

        CSA = new Cannon(
                control.getDouble("[CSA] muzzle velocity"),
                control.getDouble("[CSA] β (x)"),
                control.getDouble("[CSA] β (y)"),
                control.getDouble("[CSA] projectile mass"),
                control.getDouble("gravity")
        );
        USA = new Cannon(
                control.getDouble("[USA] muzzle velocity"),
                control.getDouble("[USA] β (x)"),
                control.getDouble("[USA] β (y)"),
                control.getDouble("[USA] projectile mass"),
                control.getDouble("gravity")
        );

        precision = control.getInt("angle precision");
        simulationStep = control.getDouble("simulation step");

        JOptionPane.showMessageDialog(null, "Testing CSA range");
        CSARange = RangeTester.testRange(CSA, precision, simulationStep);
        JOptionPane.showMessageDialog(null, "Testing USA range");
        USARange = RangeTester.testRange(USA, precision, simulationStep);
        control.println("CSA Range:\t" + CSARange);
        control.println("USA Range:\t" + USARange);
        JOptionPane.showMessageDialog(null, "Testing USA range");

//        double CSADist = RangeTester.testRange("CSA", CSA, precision, simulationStep);
//        double USADist = RangeTester.testRange("USA", USA, precision, simulationStep);
//
//        System.out.println("USADist = " + USADist);
//        System.out.println("CSADist = " + CSADist);
    }

    @Override
    protected void doStep() {
    }
}
