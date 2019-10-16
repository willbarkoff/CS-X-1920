package com.williambarkoff.csx.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

/**
 * Class riemann represents a Riemann sum. A rule, such as {@link RightHandRule} or {@link TrapezoidRule}, implements
 * the Riemann class and is used to determine the shape of the slice to calculate the Riemann sum.
 *
 * @author William Barkoff
 */
public abstract class AbstractRiemann {
    /**
     * Calculates Δx based on the endpoints of the Riemann sum and the number of subintervals; in other words, this
     * method finds the width of each rectangle.
     *
     * @param left         the left hand endpoint of the Riemann sum
     * @param right        the right hand endpoint of the Riemann
     * @param subintervals the number of sub-intervals into which to divide the interval
     *
     * @return Δx
     */
    public double calculateDeltaX(double left, double right, int subintervals) {
        return Math.abs((right - left) / (double) subintervals);
    }

    /**
     * Calculates a Riemann sum, using the particular implementation of {@link AbstractRiemann#slice​(org.dalton.polyfun.Polynomial,
     * double, double)} defined in the subclass.
     *
     * @param poly         the polynomial whose Riemann sum is to be calculated
     * @param left         the left hand endpoint of the Riemann sums
     * @param right        the right hand endpoint of the Riemann sum
     * @param subintervals the number of subintervals into which to divide the interval
     *
     * @return An approximation of the Riemann sum over the interval <code>[left, right]</code>.
     */
    public double rs(Polynomial poly, double left, double right, int subintervals) {
        double deltaX = this.calculateDeltaX(left, right, subintervals);
        double area = 0.0;
        for (double i = left; i < right; i += deltaX) {
            area += slice(poly, i, i + deltaX);
        }
        return area;
    }

    /**
     * Graphs the accumulation function corresponding to the input polynomial and the input left hand endpoint. This
     * method uses the specific implementation of {@link AbstractRiemann#slicePlot​(org.opensourcephysics.frames.PlotFrame,
     * org.dalton.polyfun.Polynomial, double, double)} defined in the subclass.
     *
     * @param pframe    the PlotFrame on which the polynomial and the Riemann sum are drawn
     * @param poly      the polynomial whose accumulation function is to be calculated
     * @param precision the difference between the x-coordinates of two adjacent points on the graph of the accumulation
     *                  function
     * @param left      the left hand endpoint of the accumulation function
     * @param right     the right hand endpoint of the accumulation function
     */
    public void rsAcc(PlotFrame pframe, Polynomial poly, double precision, double left, double right) {
        Trail trail = new Trail();
        pframe.addDrawable(trail);

        int j = 0;
        for (double i = left; i <= right; i += precision) {
            j++;
            trail.addPoint(i, rs(poly, 0, right, j));
        }

        pframe.setPreferredMinMax(left, right, trail.getYMin(), trail.getYMax());
    }

    /**
     * Plots a Riemann sum in a given PlotFrame.
     *
     * @param pframe       the PlotFrame on which the polynomial and the Riemann sum are drawn
     * @param poly         the polynomial whose Riemann sum is to be calculated
     * @param precision    the difference between the x-coordinates of two adjacent points on the graph of the function
     * @param left         the left hand endpoint of the Riemann sum
     * @param right        the right hand endpoint of the Riemann sum
     * @param subintervals the number of subintervals into which to divide the interval
     */
    public void rsPlot(PlotFrame pframe, Polynomial poly, double precision, double left, double right, int subintervals) {
        Trail trail = new Trail();
        pframe.addDrawable(trail);

        for (double i = left; i <= right; i += precision) {
            trail.addPoint(i, poly.evaluateWith(i));
        }

        double deltaX = calculateDeltaX(left, right, subintervals);
        for (double i = left; i < right; i += deltaX) {
            slicePlot(pframe, poly, i, i + deltaX);
        }

        pframe.setPreferredMinMax(left, right, trail.getYMin(), trail.getYMax());
    }

    /**
     * Approximates a slice of the (signed) area between the graph of a polynomial and the x-axis, over a given interval
     * on the x-axis. The specific Riemann sum rule used in the calculation is defined by the subclass. This method
     * should be used in {@link AbstractRiemann#rs(Polynomial, double, double, int)} to find the total area.
     *
     * @param poly   the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to
     *               be calculated
     * @param sleft  the left hand endpoint of the interval
     * @param sright the right hand endpoint of the interval
     *
     * @return A slice of the riemann sum
     */
    public abstract double slice(Polynomial poly, double sleft, double sright);

    /**
     * Plots a slice of the (signed) area between the graph of a polynomial and the x-axis, over a given interval on the
     * x-axis. The specific Riemann sum rule used is defined by the subclass. This method should be used in {@link
     * AbstractRiemann#rsPlot(PlotFrame, Polynomial, double, double, double, int)} to find the total area.
     *
     * @param pframe the PlotFrame on which the slicePlot is to be drawn
     * @param poly   the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to
     *               be drawn
     * @param sleft  the left hand endpoint of the interval
     * @param sright the right hand endpoint of the interval
     */
    public abstract void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright);

}
