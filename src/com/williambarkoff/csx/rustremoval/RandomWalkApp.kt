package com.williambarkoff.csx.rustremoval

import org.opensourcephysics.controls.AbstractSimulation
import org.opensourcephysics.controls.SimulationControl
import org.opensourcephysics.display.Circle
import org.opensourcephysics.frames.PlotFrame
import kotlin.random.Random

fun main() {
    SimulationControl.createApp(RandomWalkApp())
}

class RandomWalkApp : AbstractSimulation() {
    internal var plotFrame = PlotFrame("x", "y", "Random Walk")
    internal var circles = ArrayList<MovingCircle>()

    override fun doStep() {
        circles.forEach { it.move() }
    }

    override fun initialize() {
        for(i in 1..50) {
            val circle = MovingCircle()
            circles.add(circle)
            plotFrame.addDrawable(circle)
        }
        plotFrame.setPreferredMinMax(-25.0, 25.0, -25.0, 25.0)
    }
}

class MovingCircle: Circle() {
    fun move() {
        val randX = Random.nextInt(0, 2)
        when(randX) {
            0 -> this.x--
            1 -> this.x++
        }

        val randY = Random.nextInt(0, 2)
        when(randY) {
            0 -> this.y--
            1 -> this.y++
        }

    }
}