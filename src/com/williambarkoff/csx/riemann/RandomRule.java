package com.williambarkoff.csx.riemann;

import com.williambarkoff.csx.util.DrawableShapeUtils;
import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

public class RandomRule extends AbstractRiemann {
    @Override
    public double slice(Polynomial poly, double sleft, double sright) {
        double rand = sleft + Math.random() * (sright - sleft);

        double width = Math.abs(sleft - sright);
        double height = poly.evaluateWith(rand);
        return width * height;
    }

    @Override
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        double rand = sleft + Math.random() * (sright - sleft);

        DrawableShape rect = DrawableShapeUtils.createRectangle(sleft, 0, sright - sleft, poly.evaluateWith(rand));
        pframe.addDrawable(rect);
    }
}
