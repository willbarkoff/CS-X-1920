package com.williambarkoff.csx.util;

public abstract class MathFun {
    public static double PercentError(double expected, double actual) {
        return Math.abs(expected - actual) / expected;
    }
}
