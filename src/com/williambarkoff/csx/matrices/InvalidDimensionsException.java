package com.williambarkoff.csx.matrices;

public class InvalidDimensionsException extends Exception {
    public InvalidDimensionsException(int expectedRows, int expectedCols, int actualRows, int actualCols) {
        super("Expected size " + expectedRows + "x" + expectedCols + ", got size " + actualRows + "x" + actualCols + ".");
    }

    public InvalidDimensionsException(int expected, int actual, String dimension) {
        super("Expected " + expected + " " + dimension + ", got " + actual + ".");
    }
}
