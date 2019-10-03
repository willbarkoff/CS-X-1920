package com.williambarkoff.csx.riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.frames.PlotFrame;

public class RightHandRule extends Riemann {
    @Override
    public double slice​(Polynomial poly, double sleft, double sright) {
        return 0;
    }

    @Override
    public void slicePlot​(PlotFrame pframe, Polynomial poly, double sleft, double sright) {

    }
}
