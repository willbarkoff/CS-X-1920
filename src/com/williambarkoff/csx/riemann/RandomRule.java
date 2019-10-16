package com.williambarkoff.csx.riemann;

import com.williambarkoff.csx.util.DrawableShapeUtils;
import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

public class RandomRule extends AbstractRiemann {
    /**
     * Approximates the (signed) area of the slice using the random rule
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
        double rand = sleft + Math.random() * (sright - sleft);

        double width = Math.abs(sleft - sright);
        double height = poly.evaluateWith(rand);
        return width * height;
    }

    /**
     * Plots a slice of the random rule on the specified pframe
     *
     * @param pframe the PlotFrame on which the slicePlot is to be drawn
     * @param poly   the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to
     *               be drawn
     * @param sleft  the left hand endpoint of the interval
     * @param sright the right hand endpoint of the interval
     */
    @Override
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        double rand = sleft + Math.random() * (sright - sleft);

        DrawableShape rect = DrawableShapeUtils.createRectangle(sleft, 0, sright - sleft, poly.evaluateWith(rand));
        pframe.addDrawable(rect);
    }
}
