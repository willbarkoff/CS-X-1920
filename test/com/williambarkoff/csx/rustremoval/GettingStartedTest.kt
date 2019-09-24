package com.williambarkoff.csx.rustremoval

import org.junit.Test

import org.hamcrest.core.Is.`is`
import org.junit.Assert.*

class GettingStartedTest {

    @Test
    fun add() {
        assertThat(GettingStarted.add(2, 3), `is`(5))
    }
}