package com.williambarkoff.csx.quadratics

import kotlin.math.pow
import kotlin.math.sqrt

class Quadratic(val a: Double, val b: Double, val c: Double) {

    val discriminant = b.pow(2.0) - (4*a*c)

    val hasRealRoots: Boolean
        get() {
            return discriminant >= 0
        }

    val numberOfRoots: Int
        get() {
            if(discriminant == 0.0) {
                return 1
            } else if (discriminant < 0.0) {
                return 0
            } else {
                return 2
            }
        }

    val roots: List<Double>
        get() {
            val root1 = (-b + sqrt(b.pow(2.0) - 4*a*c))/(2*a)
            val root2 = (-b - sqrt(b.pow(2.0) - 4*a*c))/(2*a)

            if(root1 != root2) {
                return arrayOf(root1, root2).filter{ !it.isNaN() }
            } else return arrayOf(root1).filter{ !it.isNaN() }
        }

    val axisOfSymmetry: Double
        get() {
            return (-b)/(2*a)
        }

    val extremeValue: Double
        get() {
            return this.evaluateAt(axisOfSymmetry)
        }

    val opensUpward: Boolean
        get() {
            return a > 0
        }

    fun evaluateAt(x: Double): Double {
        return a*x.pow(2) + b*x + c
    }
}