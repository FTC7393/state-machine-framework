package ftc.electronvolts.test.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ftc.electronvolts.util.DeadZone;
import ftc.electronvolts.util.DeadZones;

public class DeadZonesTest {

    @Test
    public void testAny() {
        List<DeadZone> deadZones = new ArrayList<>();
        deadZones.add(DeadZones.minMaxDeadzone(-0.1, 0.1));
        deadZones.add(DeadZones.minMaxDeadzone(0.9, 1.1));
        DeadZone d = DeadZones.any(deadZones);

        assertFalse(d.isInside(-1));
        assertTrue(d.isInside(-0.1));
        assertTrue(d.isInside(-0.05));
        assertTrue(d.isInside(0));
        assertTrue(d.isInside(0.05));
        assertTrue(d.isInside(0.1));

        assertFalse(d.isInside(0.5));

        assertTrue(d.isInside(0.9));
        assertTrue(d.isInside(1));
        assertTrue(d.isInside(1.1));
        assertFalse(d.isInside(2));

        d = DeadZones.any(new ArrayList<DeadZone>());
        assertFalse(d.isInside(0));

    }

    @Test
    public void testAll() {
        List<DeadZone> deadZones = new ArrayList<>();
        deadZones.add(DeadZones.minMaxDeadzone(-1, 1.5));
        deadZones.add(DeadZones.minMaxDeadzone(1, 5));
        DeadZone d = DeadZones.all(deadZones);

        assertFalse(d.isInside(-2));
        assertFalse(d.isInside(-1));
        assertFalse(d.isInside(-0.5));
        assertFalse(d.isInside(0));
        assertTrue(d.isInside(1));
        assertTrue(d.isInside(1.2));
        assertTrue(d.isInside(1.5));
        assertFalse(d.isInside(2));
        assertFalse(d.isInside(3));
        assertFalse(d.isInside(5));
        assertFalse(d.isInside(6.5));

        d = DeadZones.all(new ArrayList<DeadZone>());
        assertTrue(d.isInside(0));
    }

    @Test
    public void testNot() {
        DeadZone d = DeadZones.not(DeadZones.minMaxDeadzone(-0.1, 0.1));
        assertTrue(d.isInside(-1));
        assertFalse(d.isInside(-0.1));
        assertFalse(d.isInside(-0.05));
        assertFalse(d.isInside(0));
        assertFalse(d.isInside(0.05));
        assertFalse(d.isInside(0.1));
        assertTrue(d.isInside(1));
    }

    @Test
    public void testNoDeadZone() {
        DeadZone d = DeadZones.noDeadZone();
        assertFalse(d.isInside(-1));
        assertFalse(d.isInside(-0.1));
        assertFalse(d.isInside(-0.05));
        assertFalse(d.isInside(0));
        assertFalse(d.isInside(0.05));
        assertFalse(d.isInside(0.1));
        assertFalse(d.isInside(1));
    }

    @Test
    public void testMinMaxDeadzone() {
        DeadZone d = DeadZones.minMaxDeadzone(-0.1, 0.1);
        assertFalse(d.isInside(-1));
        assertTrue(d.isInside(-0.1));
        assertTrue(d.isInside(-0.05));
        assertTrue(d.isInside(0));
        assertTrue(d.isInside(0.05));
        assertTrue(d.isInside(0.1));
        assertFalse(d.isInside(1));
    }

    @Test
    public void testDeltaDeadZone() {
        DeadZone d = DeadZones.deltaDeadZone(0, 0.1);
        assertFalse(d.isInside(-1));
        assertTrue(d.isInside(-0.1));
        assertTrue(d.isInside(-0.05));
        assertTrue(d.isInside(0));
        assertTrue(d.isInside(0.05));
        assertTrue(d.isInside(0.1));
        assertFalse(d.isInside(1));
    }

}
