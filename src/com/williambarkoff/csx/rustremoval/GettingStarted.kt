package com.williambarkoff.csx.rustremoval

import org.dalton.polyfun.Polynomial

import org.opensourcephysics.display.Trail
import org.opensourcephysics.frames.PlotFrame

import java.awt.Color

/**
 * GettingStarted.GettingStarted class to show off Polynomials, Open Source Physics and JUnit test.
 */
object GettingStarted {
    @JvmStatic
    fun main(args: Array<String>) {
        /*
             Polynomial examples
         */
        val fx = Polynomial(2)
        val vx = Polynomial(doubleArrayOf(1.0, 2.0, 3.0))
        val gx = Polynomial(doubleArrayOf(4.0, 0.0, 0.0, 0.0, 5.0))

        println("g(x) = $gx")
        println("f(x) = $fx")
        println("v(x) = $vx")
        println("f(x) * v(x) = " + fx * vx)

        println()
        println("f(2) = " + fx.evaluateWith(2.0))
        println("g(2) = " + gx.evaluateWith(2.0))
        println("f(x) + v(x) = " + (fx + vx))

        println()
        println(vx.getCoefficientAtTerm(2))
        println(vx.getCoefficientAtTerm(1))
        println(vx.coefficientArray[2])

        println()

        vx.coefficientArray.forEach { println(it) }

        /*
              Open Source Physics (OSP) Example
         */
        // Setup the graph
        val plotFrame = PlotFrame("x", "y", "Getting Started")
        plotFrame.setSize(400, 400) // window size
        plotFrame.setPreferredMinMax(0.0, 10.0, 0.0, 15.0) // x and y ranges
        plotFrame.setConnected(true) // if you want to connect the dots
        plotFrame.defaultCloseOperation = 3  // if you want closing the graph to end the program
        plotFrame.isVisible = true // need this to show the graph, it is false by default

        // Data set 0 (red line)
        plotFrame.setLineColor(0, Color.RED)
        for (i in 0..10) plotFrame.append(0, i.toDouble(), fx.evaluateWith(i.toDouble()))


        // Data set 1 (green line)
        plotFrame.setLineColor(1, Color.GREEN)
        for (i in 0..10) plotFrame.append(0, i.toDouble(), vx.evaluateWith(i.toDouble()))

        // Example of a Trail.
        val trail = Trail()
        trail.color = Color.ORANGE
        plotFrame.addDrawable(trail) // add the trail to the plot frame
        for(i in 0..10) trail.addPoint(i.toDouble(), gx.evaluateWith(i.toDouble()))
    }

    /**
     * Go to /test/GettingStartedTest.java to see how to test this method with Junit.
     *
     * @param a first num to add
     * @param b second num to add
     * @return the sum
     */
    fun add(a: Int, b: Int): Int {
        return a + b
    }
}
