package ftc.electronvolts.test.statemachine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ftc.electronvolts.statemachine.EndCondition;
import ftc.electronvolts.statemachine.EndConditions;
import ftc.electronvolts.util.BasicResultReceiver;
import ftc.electronvolts.util.InputExtractor;
import ftc.electronvolts.util.InputExtractors;
import ftc.electronvolts.util.MatchTimer;
import ftc.electronvolts.util.ResultReceiver;
import ftc.electronvolts.util.units.Time;

public class EndConditionsTest {

    private class MyEndCondition implements EndCondition {
        public boolean done = false, init = false;

        @Override
        public void init() {
            init = true;
        }

        @Override
        public boolean isDone() {
            return done;
        }
    }

    private static final InputExtractor<Boolean> trueIE = new InputExtractor<Boolean>() {
        @Override
        public Boolean getValue() {
            return true;
        }
    };

    private static final InputExtractor<Boolean> falseIE = new InputExtractor<Boolean>() {
        @Override
        public Boolean getValue() {
            return false;
        }
    };

    private static InputExtractor<Boolean> flipFlopIE(final boolean startState) {
        return new InputExtractor<Boolean>() {
            boolean state = !startState;

            @Override
            public Boolean getValue() {
                state = !state;
                return state;
            }
        };
    }

    public static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTimed() {
        EndCondition e1 = EndConditions.timed(20);
        EndCondition e2 = EndConditions.timed(Time.fromSeconds(0.020));
        for (int i = 0; i < 2; i++) {
            e1.init();
            e2.init();
            assertFalse(e1.isDone());
            assertFalse(e2.isDone());
            delay(10);
            assertFalse(e1.isDone());
            assertFalse(e2.isDone());
            delay(10);
            assertTrue(e1.isDone());
            assertTrue(e2.isDone());
        }
    }

    @Test
    public void testMatchTimed() {
        MatchTimer m = new MatchTimer(1000);
        EndCondition e = EndConditions.matchTimed(m, 20);
        e.init();
        assertFalse(e.isDone());
        delay(10);
        m.start();
        assertFalse(e.isDone());
        delay(10);
        m.update();
        assertFalse(e.isDone());
        delay(10);
        m.update();
        assertTrue(e.isDone());
        e.init();
        assertTrue(e.isDone());
    }

    @Test
    public void testNot() {
        MyEndCondition myEndCondition = new MyEndCondition();
        EndCondition e = EndConditions.not(myEndCondition);
        for (int i = 0; i < 2; i++) {
            myEndCondition.done = false;
            e.init();
            assertTrue(myEndCondition.init);
            assertTrue(e.isDone());
            myEndCondition.done = true;
            assertFalse(e.isDone());
        }
    }

    @Test
    public void testCount() {
        EndCondition e = EndConditions.count(3);
        e.init();
        assertFalse(e.isDone());
        e.init();
        assertFalse(e.isDone());
        e.init();
        assertTrue(e.isDone());
        e.init();
        assertTrue(e.isDone());
    }

    @Test
    public void testLoopCount() {
        EndCondition e = EndConditions.loopCount(3);
        for (int i = 0; i < 2; i++) {
            e.init();
            assertFalse(e.isDone());
            assertFalse(e.isDone());
            assertTrue(e.isDone());
            assertTrue(e.isDone());
        }
    }

    @Test
    public void testAll() {
        List<MyEndCondition> myEndConditions = new ArrayList<>();
        myEndConditions.add(new MyEndCondition());
        myEndConditions.add(new MyEndCondition());
        myEndConditions.add(new MyEndCondition());

        List<EndCondition> endConditions = new ArrayList<>();

        endConditions.add(myEndConditions.get(0));
        endConditions.add(myEndConditions.get(1));
        endConditions.add(myEndConditions.get(2));

        EndCondition e = EndConditions.all(endConditions);

        for (int i = 0; i < 2; i++) {
            e.init();
            assertTrue(myEndConditions.get(0).init);
            assertTrue(myEndConditions.get(1).init);
            assertTrue(myEndConditions.get(2).init);

            myEndConditions.get(0).done = false;
            myEndConditions.get(1).done = false;
            myEndConditions.get(2).done = false;

            assertFalse(e.isDone());

            myEndConditions.get(0).done = true;
            myEndConditions.get(1).done = false;
            myEndConditions.get(2).done = false;

            assertFalse(e.isDone());

            myEndConditions.get(0).done = false;
            myEndConditions.get(1).done = true;
            myEndConditions.get(2).done = true;

            assertFalse(e.isDone());

            myEndConditions.get(0).done = true;
            myEndConditions.get(1).done = false;
            myEndConditions.get(2).done = true;

            assertFalse(e.isDone());

            myEndConditions.get(0).done = true;
            myEndConditions.get(1).done = true;
            myEndConditions.get(2).done = true;

            assertTrue(e.isDone());
        }
    }

    @Test
    public void testAny() {
        List<MyEndCondition> myEndConditions = new ArrayList<>();
        myEndConditions.add(new MyEndCondition());
        myEndConditions.add(new MyEndCondition());
        myEndConditions.add(new MyEndCondition());

        List<EndCondition> endConditions = new ArrayList<>();

        endConditions.add(myEndConditions.get(0));
        endConditions.add(myEndConditions.get(1));
        endConditions.add(myEndConditions.get(2));

        EndCondition e = EndConditions.any(endConditions);

        for (int i = 0; i < 2; i++) {
            e.init();
            assertTrue(myEndConditions.get(0).init);
            assertTrue(myEndConditions.get(1).init);
            assertTrue(myEndConditions.get(2).init);

            myEndConditions.get(0).done = false;
            myEndConditions.get(1).done = false;
            myEndConditions.get(2).done = false;

            assertFalse(e.isDone());

            myEndConditions.get(0).done = true;
            myEndConditions.get(1).done = false;
            myEndConditions.get(2).done = false;

            assertTrue(e.isDone());

            myEndConditions.get(0).done = false;
            myEndConditions.get(1).done = true;
            myEndConditions.get(2).done = true;

            assertTrue(e.isDone());

            myEndConditions.get(0).done = true;
            myEndConditions.get(1).done = false;
            myEndConditions.get(2).done = true;

            assertTrue(e.isDone());

            myEndConditions.get(0).done = true;
            myEndConditions.get(1).done = true;
            myEndConditions.get(2).done = true;

            assertTrue(e.isDone());
        }
    }

    @Test
    public void testNever() {
        EndCondition e = EndConditions.never();
        e.init();
        assertFalse(e.isDone());
        e.init();
        assertFalse(e.isDone());
        assertFalse(e.isDone());
        assertFalse(e.isDone());
        assertFalse(e.isDone());
        e.init();
        assertFalse(e.isDone());
        assertFalse(e.isDone());
    }

    @Test
    public void testNow() {
        EndCondition e = EndConditions.now();
        e.init();
        assertTrue(e.isDone());
        e.init();
        assertTrue(e.isDone());
        assertTrue(e.isDone());
        assertTrue(e.isDone());
        assertTrue(e.isDone());
        e.init();
        assertTrue(e.isDone());
        assertTrue(e.isDone());
    }

    @Test
    public void testInputExtractor() {
        EndCondition e;

        e = EndConditions.inputExtractor(flipFlopIE(false));
        e.init();
        assertFalse(e.isDone());
        assertTrue(e.isDone());

        e = EndConditions.inputExtractor(falseIE);
        e.init();
        assertFalse(e.isDone());
        assertFalse(e.isDone());

        e = EndConditions.inputExtractor(trueIE);
        e.init();
        assertTrue(e.isDone());
        assertTrue(e.isDone());
    }

    @Test
    public void testAGreaterThanB() {
        InputExtractor<Double> low = InputExtractors.constant(-3.0);
        InputExtractor<Double> high = InputExtractors.constant(5.0);

        EndCondition e;

        e = EndConditions.aGreaterThanB(high, low, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.aGreaterThanB(high, low, false);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.aGreaterThanB(low, high, true);
        e.init();
        assertFalse(e.isDone());

        e = EndConditions.aGreaterThanB(low, high, false);
        e.init();
        assertFalse(e.isDone());

        e = EndConditions.aGreaterThanB(low, low, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.aGreaterThanB(low, low, false);
        e.init();
        assertFalse(e.isDone());
    }

    @Test
    public void testValueGreater() {
        InputExtractor<Double> ie = InputExtractors.constant(3.0);
        EndCondition e;

        e = EndConditions.valueGreater(ie, 2, false);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueGreater(ie, 2, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueGreater(ie, 3, false);
        e.init();
        assertFalse(e.isDone());

        e = EndConditions.valueGreater(ie, 3, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueGreater(ie, 4, false);
        e.init();
        assertFalse(e.isDone());

        e = EndConditions.valueGreater(ie, 4, true);
        e.init();
        assertFalse(e.isDone());
    }

    @Test
    public void testValueLess() {
        InputExtractor<Double> ie = InputExtractors.constant(3.0);
        EndCondition e;

        e = EndConditions.valueLess(ie, 4, false);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueLess(ie, 4, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueLess(ie, 3, false);
        e.init();
        assertFalse(e.isDone());

        e = EndConditions.valueLess(ie, 3, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueLess(ie, 2, false);
        e.init();
        assertFalse(e.isDone());

        e = EndConditions.valueLess(ie, 2, true);
        e.init();
        assertFalse(e.isDone());
    }

    @Test
    public void testValueBetween() {
        InputExtractor<Double> ie = InputExtractors.constant(3.0);
        EndCondition e;

        e = EndConditions.valueBetween(ie, 0.1, 4.2, false);
        e.init();
        assertTrue(e.isDone());
        e = EndConditions.valueBetween(ie, 0.1, 4.2, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueBetween(ie, 0.3, 3, false);
        e.init();
        assertFalse(e.isDone());
        e = EndConditions.valueBetween(ie, 0.3, 3, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueBetween(ie, 3, 4.4, false);
        e.init();
        assertFalse(e.isDone());
        e = EndConditions.valueBetween(ie, 3, 4.4, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueBetween(ie, 0.5, 2.6, false);
        e.init();
        assertFalse(e.isDone());
        e = EndConditions.valueBetween(ie, 0.5, 2.6, true);
        e.init();
        assertFalse(e.isDone());

        e = EndConditions.valueBetween(ie, 5.7, 6.8, false);
        e.init();
        assertFalse(e.isDone());
        e = EndConditions.valueBetween(ie, 5.7, 6.8, true);
        e.init();
        assertFalse(e.isDone());

    }

    @Test
    public void testValueCloseTo() {
        InputExtractor<Double> ie = InputExtractors.constant(-3.0);
        EndCondition e;

        e = EndConditions.valueCloseTo(ie, -2.5, 0.9, false);
        e.init();
        assertTrue(e.isDone());
        e = EndConditions.valueCloseTo(ie, -2.5, 0.9, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueCloseTo(ie, -2.5, 0.5, false);
        e.init();
        assertFalse(e.isDone());
        e = EndConditions.valueCloseTo(ie, -2.5, 0.5, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueCloseTo(ie, -3.5, 0.5, false);
        e.init();
        assertFalse(e.isDone());
        e = EndConditions.valueCloseTo(ie, -3.5, 0.5, true);
        e.init();
        assertTrue(e.isDone());

        e = EndConditions.valueCloseTo(ie, -3.5, 0.2, false);
        e.init();
        assertFalse(e.isDone());
        e = EndConditions.valueCloseTo(ie, -3.5, 0.2, true);
        e.init();
        assertFalse(e.isDone());

        e = EndConditions.valueCloseTo(ie, -2.5, 0.1, false);
        e.init();
        assertFalse(e.isDone());
        e = EndConditions.valueCloseTo(ie, -2.5, 0.1, true);
        e.init();
        assertFalse(e.isDone());
    }
    
    @Test
    public void testReceiverReady(){
        ResultReceiver<Integer> r = new BasicResultReceiver<Integer>();
        EndCondition e = EndConditions.receiverReady(r);
        assertFalse(e.isDone());
        r.setValue(3);
        assertTrue(e.isDone());
        r.clear();
        assertFalse(e.isDone());
    }

}
