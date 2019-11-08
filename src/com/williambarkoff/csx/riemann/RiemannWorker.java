package com.williambarkoff.csx.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.OSPLayout;
import org.opensourcephysics.frames.PlotFrame;

import javax.swing.*;
import java.awt.*;

public class RiemannWorker implements Runnable {
    private AbstractRiemann rule;
    private int subs;
    private Polynomial p;
    private double left;
    private double right;
    private double expected;

    private double result;

    public RiemannWorker(AbstractRiemann rule, int subs, Polynomial p, double left, double right, double expected) {
        this.rule = rule;
        this.subs = subs;
        this.p = p;
        this.left = left;
        this.right = right;
        this.expected = expected;
    }

    public double getResult() {
        return result;
    }

    public AbstractRiemann getRule() {
        return rule;
    }

    @Override
    public void run() {
        PlotFrame pframe = new PlotFrame("x", "f(x)", rule.getClass().getSimpleName());
        pframe.setSize(400, 400); // window size
        pframe.setPreferredMinMax(0, subs, 0, 15); // x and y ranges
        pframe.setConnected(true); // if you want to connect the dots
        pframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  // if you want closing the graph to end the program
        pframe.setVisible(true); // need this to show the graph, it is false by default

        double result = rule.rs(p, left, right, subs);

        rule.rsPlot(pframe, p, 0.01, left, right, subs);
        pframe.add(new Label("f(x) = " + p.toString()), OSPLayout.PAGE_START);
        pframe.add(new Label("Result: " + result), OSPLayout.PAGE_END);

        this.result = result;

        PlotFrame accframe = new PlotFrame("x", "f(x)", rule.getClass().getSimpleName() + "acc");

        accframe.setSize(400, 400); // window size
        accframe.setPreferredMinMax(0, subs, 0, 15); // x and y ranges
        accframe.setConnected(true); // if you want to connect the dots
        accframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  // if you want closing the graph to end the program
        accframe.setVisible(true); // need this to show the graph, it is false by default

        rule.rsAcc(accframe, p, 0.01, left, right);
        accframe.add(new Label("f(x) = " + p.toString()), OSPLayout.PAGE_START);
        accframe.add(new Label("Result: " + rule.rs(p, left, right, subs)), OSPLayout.PAGE_END);
    }
}
