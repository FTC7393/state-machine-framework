package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.StateTimer;

public class StateTimerTest {

    public static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStateTimer() {
        StateTimer t = new StateTimer();
        t.init(20);
        assertFalse(t.isDone());
        delay(10);
        assertFalse(t.isDone());
        delay(10);
        assertTrue(t.isDone());
    }

}
