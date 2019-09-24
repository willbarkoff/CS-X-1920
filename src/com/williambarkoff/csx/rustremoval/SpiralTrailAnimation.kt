package com.williambarkoff.csx.rustremoval

import org.opensourcephysics.controls.AbstractSimulation
import org.opensourcephysics.controls.SimulationControl
import org.opensourcephysics.display.Circle
import org.opensourcephysics.frames.PlotFrame
import org.opensourcephysics.display.DrawableShape
import org.opensourcephysics.display.Trail
import java.awt.Color


fun main() {
    SimulationControl.createApp(SpiralTrailAnimation())
}

class SpiralTrailAnimation : AbstractSimulation() {
    internal var plotFrame = PlotFrame("x", "y", "Spiral Trail")
    internal var trail = Trail()
    internal var points = arrayOf(
            arrayOf(4, 3),
            arrayOf(4, 4),
            arrayOf(3, 4),
            arrayOf(2, 4),
            arrayOf(2, 3),
            arrayOf(2, 2),
            arrayOf(3, 2),
            arrayOf(4, 2),
            arrayOf(5, 2),
            arrayOf(5, 3),
            arrayOf(5, 4),
            arrayOf(5, 5),
            arrayOf(4, 5),
            arrayOf(3, 5),
            arrayOf(2, 5),
            arrayOf(1, 5),
            arrayOf(1, 4),
            arrayOf(1, 3),
            arrayOf(1, 2),
            arrayOf(1, 1),
            arrayOf(2, 1),
            arrayOf(3, 1),
            arrayOf(4, 1),
            arrayOf(5, 1)
    )

    internal var counter = 0


    override fun doStep() {
        try {
            trail.addPoint(points[counter][0].toDouble(), points[counter][1].toDouble())
        } catch (e: ArrayIndexOutOfBoundsException) {}
        counter++
    }

    override fun initialize() {
        for(i in 1..5) {
            for(j in 1..5) {
                val circle = Circle()
                circle.x = i.toDouble()
                circle.y = j.toDouble()
                plotFrame.addDrawable(circle)
            }
        }

        val rect = DrawableShape.createRectangle(3.0, 3.0, 4.0, 4.0)
        plotFrame.addDrawable(rect)

        trail.color = Color.BLACK
        trail.addPoint(3.0,3.0)
        plotFrame.addDrawable(trail)

        plotFrame.setPreferredMinMax(0.0, 6.0, 0.0, 6.0)
    }
}