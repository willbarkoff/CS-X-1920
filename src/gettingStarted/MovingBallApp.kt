package gettingStarted

import org.opensourcephysics.controls.AbstractSimulation
import org.opensourcephysics.controls.SimulationControl
import org.opensourcephysics.display.Circle
import org.opensourcephysics.frames.PlotFrame

/***
 * GettingStarted.MovingBallApp is an extension of AbstractSimulation (an abstract class).
 *
 * An abstract class is a class that almost works except that the author intentionally left some methods empty. It is up
 * to the programmer who uses it to complete those empty methods for the class to fully work.
 *
 * In AbstractSimulation, these methods need to be completed:
 * 1. reset (adds options to the Control Panel)
 * 2. initialize (gets data from the Control Panel and sets up the graph)
 * 3. doStep (actions to take to do the animation, invoked every 1/10 second)
 *
 * You also need a main method to run the simulation (scroll to bottom).
 */
class MovingBallApp : AbstractSimulation() {
    // Set up global variables.
    internal var plotFrame = PlotFrame("x", "meters from ground", "Moving Ball")
    internal var circle = Circle()
    internal var totalTime: Double = 0.toDouble()

    /**
     * Technically optional, but the simulation won't work without it.
     * Adds options to the Control Panel.
     */
    override fun reset() {
        control.setValue("Starting Y position", 100)
    }

    /**
     * Technically optional, but the simulation won't work without it.
     * Tied to the "Initialize" button. Gets information from the Control Panel and
     * configures the plot frame.
     */
    override fun initialize() {
        // Get information from the control panel.
        val startingY = control.getDouble("Starting Y position")
        circle.y = startingY

        // Instead of appending x, y coordinates to plot frame,
        //    add the Circle which maintains its own x, y.
        plotFrame.addDrawable(circle)

        // Configure plot frame
        plotFrame.setPreferredMinMax(-25.0, 25.0, 0.0, startingY) // Scale of graph.
        plotFrame.defaultCloseOperation = 3 // Make it so x'ing out of the graph stops the program.
        plotFrame.isVisible = true // Required to show plot frame.
    }

    /**
     * Required method, invoked once every 1/10 second until Stop is pressed.
     * The doStep method is also called when the Step button is pressed.
     */
    public override fun doStep() {
        // Change y. (It will re-draw itself.)
        circle.y = circle.y - 1

        totalTime++
    }

    /**
     * Optional method, tied to Stop button.
     */
    override fun stop() {
        println((totalTime / 10).toString() + " secs to travel "
                + Math.abs(control.getDouble("Starting Y position") - circle.y) + " meters.")
    }

    companion object {

        /**
         * Required main method, runs the simulation.
         * @param args
         */
        @JvmStatic
        fun main(args: Array<String>) {
            SimulationControl.createApp(MovingBallApp())
        }
    }
}
