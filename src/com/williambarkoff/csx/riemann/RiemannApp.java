package com.williambarkoff.csx.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.OSPLayout;
import org.opensourcephysics.frames.PlotFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Riemann app is a runnable OpenSourcePhysics app that plots and computes multiple Riemann sums.
 *
 * @author William Barkoff
 */
public class RiemannApp {
    public static void main(String[] args) {
        Polynomial p = new Polynomial(new double[]{-2, 0, 1});
//        Polynomial p = new Function(Math::sin, "sin(x)");
//        Polynomial p = new Function(x -> Math.sqrt(1 - Math.pow(x, 2)), "√1̅-̅X̅^̅2̅");
//        System.out.println(p.toString());

        double left = -1;
        double right = 1;
//        int subs = Integer.MAX_VALUE;

        int subs = 20;

        AbstractRiemann[] rules = new AbstractRiemann[]{
                new LeftHandRule(),
                new RightHandRule(),
                new MidpointRule(),
                new MaximumRule(),
                new MinimumRule(),
                new RandomRule(),
                new TrapezoidRule()
        };

        double sca = new LeftHandRule().rs(p, left, right, subs);

        System.out.format("%32s", "semi-circle area=");
        System.out.println(sca);
        System.out.format("%32s", "π ≈ ");
        System.out.println(sca * 2);
        System.out.format("%32s", "π (NASA) = ");
        System.out.println(Math.PI);

        double MPR = new RightHandRule().rs(p, left, right, subs);
        System.out.println("MPR = " + MPR);

        for (AbstractRiemann rule : rules) {
            PlotFrame pframe = new PlotFrame("x", "f(x)", rule.getClass().getSimpleName());
            pframe.setSize(400, 400); // window size
            pframe.setPreferredMinMax(0, subs, 0, 15); // x and y ranges
            pframe.setConnected(true); // if you want to connect the dots
            pframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  // if you want closing the graph to end the program
            pframe.setVisible(true); // need this to show the graph, it is false by default

            rule.rsPlot(pframe, p, 0.01, left, right, subs);
            pframe.add(new Label("f(x) = " + p.toString()), OSPLayout.PAGE_START);
            pframe.add(new Label("Result: " + rule.rs(p, left, right, subs)), OSPLayout.PAGE_END);
        }
    }
}
