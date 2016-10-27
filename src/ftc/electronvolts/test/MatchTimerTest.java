package ftc.electronvolts.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.MatchTimer;

public class MatchTimerTest {

    @Test
    public void testMatchTimer() {
        new MatchTimer(1000);
        new MatchTimer(2000);
        new MatchTimer(-1);
        new MatchTimer(-100000);
    }

    // @Test
    // public void testStart() {
    // fail("Not yet implemented");
    // }

    public static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // @Test
    // public void testUpdate() {
    // fail("Not yet implemented");
    // }

    @Test
    public void testGetDeltaTime() {
        MatchTimer m = new MatchTimer(2000);
        long update;
        m.start();

        delay(30);
        update = m.update();
        assertEquals(30, update, 5);
        assertTrue(update == m.getDeltaTime());

        delay(30);
        update = m.update();
        assertEquals(30, update, 5);
        assertTrue(update == m.getDeltaTime());
    }

    @Test
    public void testGetElapsedTime() {
        MatchTimer m = new MatchTimer(2000);
        m.start();

        delay(30);
        m.update();
        assertEquals(30, m.getElapsedTime(), 5);

        delay(30);
        m.update();
        assertEquals(60, m.getElapsedTime(), 10);
    }

    @Test
    public void testGetTimeLeft() {
        MatchTimer m = new MatchTimer(2000);
        m.start();

        delay(30);
        m.update();
        assertEquals(2000 - 30, m.getTimeLeft(), 5);

        delay(30);
        m.update();
        assertEquals(2000 - 60, m.getTimeLeft(), 10);
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
