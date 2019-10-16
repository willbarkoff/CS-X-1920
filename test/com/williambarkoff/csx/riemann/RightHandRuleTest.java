package com.williambarkoff.csx.riemann;

import org.dalton.polyfun.Polynomial;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RightHandRuleTest {
    @Test
    public void slice() {
        AbstractRiemann r = new RightHandRule();
        assertEquals(1, r.rs(new Polynomial(1), 0, 1, 1), 0);
        assertEquals(0.625, r.rs(new Polynomial(2), 0, 1, 2), 0);
        assertEquals(315.4296875, r.rs(new Polynomial(2), 5, 10, 8), 0.01);
    }
}
