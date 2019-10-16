package com.williambarkoff.csx.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

/**
 * Trapezoid rule uses a trapezoidal area to calculate the Riemann sum
 *
 * @author William Barkoff
 */
public class TrapezoidRule extends AbstractRiemann {
    /**
     * Approximates the (signed) area of the slice using the trapezoid rule
     *
     * @param poly   the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to
     *               be calculated
     * @param sleft  the left hand endpoint of the interval
     * @param sright the right hand endpoint of the interval
     *
     * @return The area of the slice of the riemann sum using the left hand rule
     */
    @Override
    public double slice(Polynomial poly, double sleft, double sright) {
        double width = Math.abs(sleft - sright);
        double h1 = poly.evaluateWith(sleft);
        double h2 = poly.evaluateWith(sright);
        return (h1 + h2) * (sright - sleft / 2);
    }

    /**
     * Plots a slice of the trapezoid rule on the specified pframe
     * <p>
     * <b>Note:</b> The interior of the trapezoid is not shaded.
     * </p>
     *
     * @param pframe the PlotFrame on which the slicePlot is to be drawn
     * @param poly   the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to
     *               be drawn
     * @param sleft  the left hand endpoint of the interval
     * @param sright the right hand endpoint of the interval
     */
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
