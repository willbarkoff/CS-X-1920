package com.williambarkoff.csx.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.frames.PlotFrame;

public class MaximumRule extends AbstractRiemann {
    @Override
    public double slice(Polynomial poly, double sleft, double sright) {
        if (poly.evaluateWith(sleft) > poly.evaluateWith(sright)) {
            return new LeftHandRule().slice(poly, sleft, sright);
        } else {
            return new RightHandRule().slice(poly, sleft, sright);
        }
    }

    @Override
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        if (poly.evaluateWith(sleft) > poly.evaluateWith(sright)) {
            new LeftHandRule().slicePlot(pframe, poly, sleft, sright);
        } else {
            new RightHandRule().slicePlot(pframe, poly, sleft, sright);
        }
    }
}
