package com.williambarkoff.csx.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.frames.PlotFrame;

public class MinimumRule extends AbstractRiemann {
    /**
     * Approximates the (signed) area of the slice using the minimum rule
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
        if (poly.evaluateWith(sleft) < poly.evaluateWith(sright)) {
            return new LeftHandRule().slice(poly, sleft, sright);
        } else {
            return new RightHandRule().slice(poly, sleft, sright);
        }
    }

    /**
     * Plots a slice of the minimum rule on the specified pframe
     *
     * @param pframe the PlotFrame on which the slicePlot is to be drawn
     * @param poly   the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to
     *               be drawn
     * @param sleft  the left hand endpoint of the interval
     * @param sright the right hand endpoint of the interval
     */
    @Override
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        if (poly.evaluateWith(sleft) < poly.evaluateWith(sright)) {
            new LeftHandRule().slicePlot(pframe, poly, sleft, sright);
        } else {
            new RightHandRule().slicePlot(pframe, poly, sleft, sright);
        }
    }
}
