package com.williambarkoff.csx.quadratics

import org.junit.Test
import kotlin.test.assertEquals

class QuadraticTest {
    @Test
    fun getA() {
        val q = Quadratic(1.0, 2.0, 3.0)
        assert(q.a == 1.0)
    }

    @Test
    fun getB() {
        val q = Quadratic(1.0, 2.0, 3.0)
        assert(q.b == 2.0)
    }

    @Test
    fun getC() {
        val q = Quadratic(1.0, 2.0, 3.0)
        assert(q.c == 3.0)
    }

    @Test
    fun hasRealRoots() {
        val q1 = Quadratic(1.0, 2.0, 3.0)
        assert(!q1.hasRealRoots)

        val q2 = Quadratic(1.0, -4.0, 3.0)
        assert(q2.hasRealRoots)

        val q3 = Quadratic(1.0, 0.0, 0.0)
        assert(q3.hasRealRoots)
    }

    @Test
    fun numberOfRoots() {
        val q1 = Quadratic(1.0, 2.0, 3.0)
        assertEquals(0, q1.numberOfRoots, "Discriminant > 0")

        val q2 = Quadratic(1.0, -4.0, 3.0)
        assertEquals(2, q2.numberOfRoots, "Discriminant == 0")

        val q3 = Quadratic(1.0, 0.0, 0.0)
        assertEquals(1, q3.numberOfRoots, "Discriminant < 0")
    }

    @Test
    fun roots() {
        val q1 = Quadratic(1.0, 2.0, 3.0)
        assertEquals(listOf(), q1.roots, "Discriminant > 0")

        val q2 = Quadratic(1.0, -4.0, 3.0)
        assertEquals(setOf(1.0, 3.0), q2.roots.toSet(), "Discriminant == 0") // this one uses toSet() so they can be in any order

        val q3 = Quadratic(1.0, 0.0, 0.0)
        assertEquals(listOf(0.0), q3.roots, "Discriminant < 0")
    }

    @Test
    fun axisOfSymmetry() {
        val q1 = Quadratic(1.0, 2.0, 3.0)
        assertEquals(-1.0, q1.axisOfSymmetry)

        val q2 = Quadratic(3.0, -4.0, 3.0)
        assertEquals(2.0/3.0, q2.axisOfSymmetry)

        val q3 = Quadratic(1.0, 0.0, 0.0)
        assert(0.0 == q3.axisOfSymmetry) // use == rather than assertEquals because assertEquals doesn't think 0.0 and -0.0 are the same
    }

    @Test
    fun extremeValue() {
        val q1 = Quadratic(1.0, 2.0, 3.0)
        assertEquals(2.0, q1.extremeValue)

        val q2 = Quadratic(3.0, -4.0, 3.0)
        assertEquals(5.0/3.0, q2.extremeValue)

        val q3 = Quadratic(1.0, 0.0, 0.0)
        assert(0.0 == q3.extremeValue) // use == rather than assertEquals because assertEquals doesn't think 0.0 and -0.0 are the same
    }

    @Test
    fun opensUpward() {
        val q1 = Quadratic(1.0, 2.0, 3.0)
        assert(q1.opensUpward)

        val q2 = Quadratic(0.0, 2.0, 3.0)
        assert(!q2.opensUpward)

        val q3 = Quadratic(-1.0, 2.0, 3.0)
        assert(!q3.opensUpward)
    }

    @Test
    fun evaluateAt() {
        val q1 = Quadratic(1.0, 2.0, 3.0)
        assertEquals(66.0, q1.evaluateAt(7.0))

        val q2 = Quadratic(3.0, -4.0, 3.0)
        assertEquals(122.0, q2.evaluateAt(7.0))

        val q3 = Quadratic(1.0, 0.0, 0.0)
        assertEquals(49.0, q3.evaluateAt(7.0))
    }
}