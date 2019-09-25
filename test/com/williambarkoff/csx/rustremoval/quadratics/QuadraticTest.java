package com.williambarkoff.csx.rustremoval.quadratics;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class QuadraticTest {
    @Test
    public void getA() {
        Quadratic q = new Quadratic(1.0, 2.0, 3.0);
        assert(q.getA() == 1.0);
    }

    @Test
    public void getB() {
        Quadratic q =  new Quadratic(1.0, 2.0, 3.0);
        assert(q.getB() == 2.0);
    }

    @Test
    public void getC() {
        Quadratic q = new Quadratic(1.0, 2.0, 3.0);
        assert(q.getC() == 3.0);
    }

    @Test
    public void hasRealRoots() {
        Quadratic q1 = new Quadratic(1.0, 2.0, 3.0);
        assert(!q1.hasRealRoots());

        Quadratic q2 = new Quadratic(1.0, -4.0, 3.0);
        assert(q2.hasRealRoots());

        Quadratic q3 = new Quadratic(1.0, 0.0, 0.0);
        assert(q3.hasRealRoots());
    }

    @Test
    public void numberOfRoots() {
        Quadratic q1 = new Quadratic(1.0, 2.0, 3.0);
        assertEquals(0, q1.getNumberOfRoots());

        Quadratic q2 = new Quadratic(1.0, -4.0, 3.0);
        assertEquals(2, q2.getNumberOfRoots());

        Quadratic q3 = new Quadratic(1.0, 0.0, 0.0);
        assertEquals(1, q3.getNumberOfRoots());
    }

    @Test
    public void roots() {
        Quadratic q1 = new Quadratic(1.0, 2.0, 3.0);
        assertArrayEquals(new double[]{Double.NaN, Double.NaN}, q1.getRoots(), 0.0);

        Quadratic q2 = new Quadratic(1.0, -4.0, 3.0);
        assertArrayEquals(new double[]{3.0, 1.0}, q2.getRoots(), 0.0); // this one uses toSet() so they can be in any order

        Quadratic q3 = new Quadratic(1.0, 0.0, 0.0);
        assertArrayEquals(new double[]{0.0}, q3.getRoots(), 0.0);
    }

    @Test
    public void axisOfSymmetry() {
        Quadratic q1 = new Quadratic(1.0, 2.0, 3.0);
        assertEquals(-1.0, q1.getAxisOfSymmetry());

        Quadratic q2 = new Quadratic(3.0, -4.0, 3.0);
        assertEquals(2.0/3.0, q2.getAxisOfSymmetry());

        Quadratic q3 = new Quadratic(1.0, 0.0, 0.0);
        assert(0.0 == q3.getAxisOfSymmetry()); // use == rather than assertEquals because assertEquals doesn't think 0.0 and -0.0 are the same
    }

    @Test
    public void extremeValue() {
        Quadratic q1 = new Quadratic(1.0, 2.0, 3.0);
        assertEquals(2.0, q1.getExtremeValue());

        Quadratic q2 = new Quadratic(3.0, -4.0, 3.0);
        assertEquals(5.0/3.0, q2.getExtremeValue());

        Quadratic q3 = new Quadratic(1.0, 0.0, 0.0);
        assert(0.0 == q3.getExtremeValue()); // use == rather than assertEquals because assertEquals doesn't think 0.0 and -0.0 are the same
    }

    @Test
    public void opensUpward() {
        Quadratic q1 = new Quadratic(1.0, 2.0, 3.0);
        assert(q1.opensUpward());

        Quadratic q2 = new Quadratic(0.0, 2.0, 3.0);
        assert(!q2.opensUpward());

        Quadratic q3 = new Quadratic(-1.0, 2.0, 3.0);
        assert(!q3.opensUpward());
    }

    @Test
    public void evaluateAt() {
        Quadratic q1 = new Quadratic(1.0, 2.0, 3.0);
        assertEquals(66.0, q1.evaluateAt(7.0));

        Quadratic q2 = new Quadratic(3.0, -4.0, 3.0);
        assertEquals(122.0, q2.evaluateAt(7.0));

        Quadratic q3 = new Quadratic(1.0, 0.0, 0.0);
        assertEquals(49.0, q3.evaluateAt(7.0));
    }

}
