package com.williambarkoff.csx.matrices;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatrixTest {

    @Test
    public void multiplicationTest() throws InvalidDimensionsException {
        Matrix m1 = new Matrix(5, 3);
        m1.setEntry(0, 0, 1);
        m1.setEntry(0, 1, 2);
        m1.setEntry(0, 2, 3);
        m1.setEntry(1, 0, 4);
        m1.setEntry(1, 1, 5);
        m1.setEntry(1, 2, 6);
        m1.setEntry(2, 0, 7);
        m1.setEntry(2, 1, 8);
        m1.setEntry(2, 2, 9);
        m1.setEntry(3, 0, 10);
        m1.setEntry(3, 1, 11);
        m1.setEntry(3, 2, 12);
        m1.setEntry(4, 0, 13);
        m1.setEntry(4, 1, 14);
        m1.setEntry(4, 2, 15);

        System.out.println(m1.getWolframAlphaString());

        Matrix m2 = new Matrix(3, 4);
        m2.setEntry(0, 0, 1);
        m2.setEntry(0, 1, 2);
        m2.setEntry(0, 2, 3);
        m2.setEntry(0, 3, 4);
        m2.setEntry(1, 0, 5);
        m2.setEntry(1, 1, 6);
        m2.setEntry(1, 2, 7);
        m2.setEntry(1, 3, 8);
        m2.setEntry(2, 0, 9);
        m2.setEntry(2, 1, 10);
        m2.setEntry(2, 2, 11);
        m2.setEntry(2, 3, 12);

        System.out.println(m2.getWolframAlphaString());

        Matrix result = m1.times(m2);

        System.out.println(result.getWolframAlphaString());
    }

    @Test
    public void toStringTest() {
        Matrix m = new Matrix(3, 2);
        m.setEntry(0, 0, 1.0);
        m.setEntry(0, 1, 2.0);
        m.setEntry(1, 0, 3.0);
        m.setEntry(1, 1, 4.0);
        m.setEntry(2, 0, 5.0);
        m.setEntry(2, 1, 6.0);

        String[] s = m.toString().split("\n");
        assertEquals("⌈\t1.0\t2.0\t⌉", s[0]);
        assertEquals("|\t3.0\t4.0\t|", s[1]);
        assertEquals("⌊\t5.0\t6.0\t⌋", s[2]);
    }

    @Test
    public void addTest() throws InvalidDimensionsException {
        Matrix m2 = new Matrix(2, 2);
        Matrix m1 = new Matrix(2, 2);

        m1.setEntry(0, 0, 1.0);
        m1.setEntry(1, 0, 1.0);
        m1.setEntry(0, 1, 1.0);
        m1.setEntry(1, 1, 1.0);

        m2.setEntry(0, 0, 1.0);
        m2.setEntry(1, 0, 1.0);
        m2.setEntry(0, 1, 1.0);
        m2.setEntry(1, 1, 1.0);

        Matrix m3 = m1.plus(m2);

        assert (m3.getEntry(0, 0) == 2.0);
        assert (m3.getEntry(1, 0) == 2.0);
        assert (m3.getEntry(0, 1) == 2.0);
        assert (m3.getEntry(1, 1) == 2.0);
    }

    @Test
    public void rowReduceTest() throws PivotNotFoundException {
        Matrix matrix = new Matrix(new double[][]{
                new double[]{1, 2, 3, 4},
                new double[]{5, 1, 7, 8},
                new double[]{9, 10, 11, 12},
                new double[]{5, 6, 7, 8},
        });
//        System.out.println(matrix.zeroDown(1, 1));
        System.out.println(matrix);
        System.out.println(matrix.getWolframAlphaString());
        System.out.println();

        Matrix reduced = matrix.rowReduce();
        System.out.println(reduced);
    }

    @Test
    public void excerptTest() {
        Matrix matrix = new Matrix(4, 4, 0, 10);
        System.out.println(matrix);
        System.out.println(matrix.excerpt(1, 2));
    }

    @Test
    public void insertExcerptTest() {
        Matrix matrix = new Matrix(4, 4, 0, 10);
        System.out.println(matrix);
        System.out.println(matrix.insertExcerpt(new Matrix(3, 3, 0, 0), 1, 1));
    }

    @Test(expected = InvalidDimensionsException.class)
    public void addInvalidDimensionsTest() throws InvalidDimensionsException {
        new Matrix(2, 2, 0, 10).plus(new Matrix(1, 1, 0, 10));
    }

    @Test(expected = InvalidDimensionsException.class)
    public void multiplyInvalidDimensionsTest() throws InvalidDimensionsException {
        new Matrix(2, 2, 0, 10).times(new Matrix(1, 1, 0, 10));
    }
}
