package com.williambarkoff.csx.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.frames.PlotFrame;

import javax.swing.*;

public class RiemannApp {
    public static void main(String[] args) {
        PlotFrame rhr = new PlotFrame("x", "y", "Right Hand Rule");
        rhr.setSize(400, 400); // window size
        rhr.setPreferredMinMax(0, 10, 0, 15); // x and y ranges
        rhr.setConnected(true); // if you want to connect the dots
        rhr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // if you want closing the graph to end the program
        rhr.setVisible(true); // need this to show the graph, it is false by default

        System.out.println(new RightHandRule().rs(new Polynomial(2), 0, 1, 200000000));

        new RightHandRule().rsPlot(rhr, new Polynomial(2), 0, 0.01, 0, 10, 10);

        PlotFrame lhr = new PlotFrame("x", "y", "Left Hand Rule");
        lhr.setSize(400, 400); // window size
        lhr.setPreferredMinMax(0, 10, 0, 15); // x and y ranges
        lhr.setConnected(true); // if you want to connect the dots
        lhr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // if you want closing the graph to end the program
        lhr.setVisible(true); // need this to show the graph, it is false by default

        new LeftHandRule().rsPlot(lhr, new Polynomial(2), 0, 0.01, 0, 10, 10);

        PlotFrame mr = new PlotFrame("x", "y", "Midpoint Rule");
        mr.setSize(400, 400); // window size
        mr.setPreferredMinMax(0, 10, 0, 15); // x and y ranges
        mr.setConnected(true); // if you want to connect the dots
        mr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // if you want closing the graph to end the program
        mr.setVisible(true); // need this to show the graph, it is false by default

        new MidpointRule().rsPlot(mr, new Polynomial(2), 0, 0.01, 0, 10, 10);

        PlotFrame tr = new PlotFrame("x", "y", "Trapezoidal Rule");
        tr.setSize(400, 400); // window size
        tr.setPreferredMinMax(0, 10, 0, 15); // x and y ranges
        tr.setConnected(true); // if you want to connect the dots
        tr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // if you want closing the graph to end the program
        tr.setVisible(true); // need this to show the graph, it is false by default

        new TrapezoidRule().rsPlot(tr, new Polynomial(2), 0, 0.02, 0, 20, 10);

        PlotFrame mxr = new PlotFrame("x", "y", "Maximum Rule");
        mxr.setSize(400, 400); // window size
        mxr.setPreferredMinMax(0, 10, 0, 15); // x and y ranges
        mxr.setConnected(true); // if you want to connect the dots
        mxr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // if you want closing the graph to end the program
        mxr.setVisible(true); // need this to show the graph, it is false by default

        new MaximumRule().rsPlot(mxr, new Polynomial(2), 0, 0.01, 0, 10, 10);

        PlotFrame mnr = new PlotFrame("x", "y", "Minimum Rule");
        mnr.setSize(400, 400); // window size
        mnr.setPreferredMinMax(0, 10, 0, 15); // x and y ranges
        mnr.setConnected(true); // if you want to connect the dots
        mnr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // if you want closing the graph to end the program
        mnr.setVisible(true); // need this to show the graph, it is false by default

        new MinimumRule().rsPlot(mnr, new Polynomial(2), 0, 0.01, 0, 10, 10);

        PlotFrame rr = new PlotFrame("x", "y", "Random Rule");
        rr.setSize(400, 400); // window size
        rr.setPreferredMinMax(0, 10, 0, 15); // x and y ranges
        rr.setConnected(true); // if you want to connect the dots
        rr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // if you want closing the graph to end the program
        rr.setVisible(true); // need this to show the graph, it is false by default

        new RandomRule().rsPlot(rr, new Polynomial(2), 0, 0.01, 0, 10, 10);

        PlotFrame acc = new PlotFrame("x", "y", "Accumulation Function");
        acc.setSize(400, 400); // window size
        acc.setPreferredMinMax(0, 10, 0, 15); // x and y ranges
        acc.setConnected(true); // if you want to connect the dots
        acc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // if you want closing the graph to end the program
        acc.setVisible(true); // need this to show the graph, it is false by default

        new LeftHandRule().rsAcc(acc, new Polynomial(new double[]{1.0, -1.0, 0.0, 0.0}), 0, 0.01, 0, 2);


    }
}
