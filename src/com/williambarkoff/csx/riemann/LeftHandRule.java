package com.williambarkoff.csx.riemann;

import com.williambarkoff.csx.util.DrawableShapeUtils;
import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

public class LeftHandRule extends AbstractRiemann {
    @Override
    public double slice(Polynomial poly, double sleft, double sright) {
        double width = Math.abs(sleft - sright);
        double height = poly.evaluateWith(sleft);
        return width * height;
    }

    @Override
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        DrawableShape rect = DrawableShapeUtils.createRectangle(sleft, 0, sright - sleft, poly.evaluateWith(sleft));
        pframe.addDrawable(rect);
    }
}
