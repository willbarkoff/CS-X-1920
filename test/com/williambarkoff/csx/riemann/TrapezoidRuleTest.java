package com.williambarkoff.csx.riemann;

import org.dalton.polyfun.Polynomial;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrapezoidRuleTest {
    @Test
    public void slice() {
        AbstractRiemann r = new TrapezoidRule();
        Polynomial p = new Polynomial(new double[]{0, 0, 1});
        assertEquals(0.5, r.slice(p, 0.0, 1.0), 0);
    }
}
