package com.williambarkoff.csx.rustremoval;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GettingStartedTest {
    @Test
    public void add() {
        assertThat(GettingStarted.add(2, 3), is(5));
    }
}
