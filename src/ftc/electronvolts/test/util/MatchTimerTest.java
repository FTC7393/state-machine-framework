package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.MatchTimer;
import ftc.electronvolts.util.units.Time;

public class MatchTimerTest {
    private static int MILLIS_ERROR = 10;

    @Test
    public void testMatchTimer() {
        new MatchTimer(1000);
        new MatchTimer(2000);
        new MatchTimer(-1);
        new MatchTimer(-100000);
        new MatchTimer(Time.fromSeconds(10));
        assertTrue(new MatchTimer(null).getMatchLengthMillis() < 0);
    }

    public static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetDeltaTime() {
        MatchTimer m = new MatchTimer(2000);
        long update;
        m.start();

        delay(30);
        update = m.update();
        assertEquals(30, update, MILLIS_ERROR);
        assertTrue(update == m.getDeltaTime());

        delay(30);
        update = m.update();
        assertEquals(30, update, MILLIS_ERROR);
        assertTrue(update == m.getDeltaTime());
    }

    @Test
    public void testGetElapsedTime() {
        MatchTimer m = new MatchTimer(2000);
        m.start();

        delay(30);
        m.update();
        assertEquals(30, m.getElapsedTime(), MILLIS_ERROR);

        delay(30);
        m.update();
        assertEquals(60, m.getElapsedTime(), 2 * MILLIS_ERROR);
    }

    @Test
    public void testGetTimeLeft() {
        MatchTimer m = new MatchTimer(2000);
        m.start();

        delay(30);
        m.update();
        assertEquals(2000 - 30, m.getTimeLeft(), MILLIS_ERROR);

        delay(30);
        m.update();
        assertEquals(2000 - 60, m.getTimeLeft(), 2 * MILLIS_ERROR);
    }

    @Test
    public void testIsMatchOver() {
        MatchTimer m = new MatchTimer(50);
        m.start();

        delay(30);
        m.update();
        assertFalse(m.isMatchOver());

        delay(30);
        m.update();
        assertTrue(m.isMatchOver());

        delay(10);
        m.update();
        assertTrue(m.isMatchOver());
    }

    @Test
    public void testIsMatchJustOver() {
        MatchTimer m = new MatchTimer(50);
        m.start();

        delay(30);
        m.update();
        assertFalse(m.isMatchJustOver());

        delay(30);
        m.update();
        assertTrue(m.isMatchJustOver());

        delay(10);
        m.update();
        assertFalse(m.isMatchJustOver());
    }

    @Test
    public void testGetMatchLengthMillis() {
        assertEquals(new MatchTimer(2000).getMatchLengthMillis(), 2000);
        assertEquals(new MatchTimer(Time.fromSeconds(2)).getMatchLengthMillis(), 2000);
    }

    @Test
    public void testGetStartTime() {
        MatchTimer m = new MatchTimer(50);
        long startTime = System.currentTimeMillis();
        m.start();
        assertEquals(startTime, m.getStartTime());

        delay(30);
        m.update();
        assertEquals(startTime, m.getStartTime());
    }

    @Test
    public void testGetNow() {
        MatchTimer m = new MatchTimer(50);
        m.start();
        assertEquals(System.currentTimeMillis(), m.getNow());

        delay(30);
        m.update();
        assertEquals(System.currentTimeMillis(), m.getNow());
    }

}
