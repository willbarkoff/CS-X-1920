package com.williambarkoff.csx.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class TrapezoidRule extends AbstractRiemann {
    @Override
    public double slice(Polynomial poly, double sleft, double sright) {
        double width = Math.abs(sleft - sright);
        double h1 = poly.evaluateWith(sleft);
        double h2 = poly.evaluateWith(sright);
        return (h1 + h2) * (sright - sleft / 2);
    }

    @Override
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        Trail trap = new Trail();
        pframe.addDrawable(trap);
        trap.color = Color.RED;
        trap.addPoint(sleft, 0);
        trap.addPoint(sright, 0);
        trap.addPoint(sright, poly.evaluateWith(sright));
        trap.addPoint(sleft, poly.evaluateWith(sleft));
        trap.addPoint(sleft, 0);
        trap.closeTrail();
    }
}
