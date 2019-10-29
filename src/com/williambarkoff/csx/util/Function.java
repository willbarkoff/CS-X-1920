package com.williambarkoff.csx.util;

import org.dalton.polyfun.Polynomial;

public class Function extends Polynomial {
    private EvaluableFunction func;
    private String fx;

    public Function(EvaluableFunction func) {
        this.func = func;
        this.fx = func.toString();
    }

    public Function(EvaluableFunction func, String fx) {
        this.func = func;
        this.fx = fx;
    }

    @Override
    public String toString() {
        return fx;
    }

    @Override
    public double evaluateWith(double x) {
        return func.f(x);
    }

    public interface EvaluableFunction {
        double f(double x);
    }
}
