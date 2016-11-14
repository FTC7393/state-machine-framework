package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.DigitalInputEdgeDetector;
import ftc.electronvolts.util.InputExtractor;

public class DigitalInputEdgeDetectorTest {

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

    @Test
    public void testDigitalInputEdgeDetector() {
        new DigitalInputEdgeDetector(trueIE);
        new DigitalInputEdgeDetector(falseIE);
        new DigitalInputEdgeDetector(flipFlopIE(true));
        new DigitalInputEdgeDetector(flipFlopIE(false));
    }

    @Test
    public void testUpdate() {
        assertEquals(true, new DigitalInputEdgeDetector(trueIE).update());
        assertEquals(false, new DigitalInputEdgeDetector(falseIE).update());
    }

    @Test
    public void testGetValue() {
        DigitalInputEdgeDetector d;

        d = new DigitalInputEdgeDetector(trueIE);
        d.update();
        assertEquals(true, d.getValue());

        d = new DigitalInputEdgeDetector(falseIE);
        d.update();
        assertEquals(false, d.getValue());

        d = new DigitalInputEdgeDetector(flipFlopIE(false));
        d.update();
        assertEquals(false, d.getValue());
        d.update();
        assertEquals(true, d.getValue());

        d = new DigitalInputEdgeDetector(flipFlopIE(true));
        d.update();
        assertEquals(true, d.getValue());
        d.update();
        assertEquals(false, d.getValue());
    }

    @Test
    public void testIsPressed() {
        DigitalInputEdgeDetector d;

        d = new DigitalInputEdgeDetector(trueIE);
        d.update();
        assertEquals(true, d.isPressed());

        d = new DigitalInputEdgeDetector(falseIE);
        d.update();
        assertEquals(false, d.isPressed());

        d = new DigitalInputEdgeDetector(flipFlopIE(false));
        d.update();
        assertEquals(false, d.isPressed());
        d.update();
        assertEquals(true, d.isPressed());

        d = new DigitalInputEdgeDetector(flipFlopIE(true));
        d.update();
        assertEquals(true, d.isPressed());
        d.update();
        assertEquals(false, d.isPressed());
    }

    @Test
    public void testJustPressed() {
        DigitalInputEdgeDetector d;

        d = new DigitalInputEdgeDetector(trueIE);
        d.update();
        assertEquals(false, d.justPressed());

        d = new DigitalInputEdgeDetector(falseIE);
        d.update();
        assertEquals(false, d.justPressed());

        d = new DigitalInputEdgeDetector(flipFlopIE(false));
        d.update();
        assertEquals(false, d.justPressed());
        d.update();
        assertEquals(true, d.justPressed());
        d.update();
        assertEquals(false, d.justPressed());

        d = new DigitalInputEdgeDetector(flipFlopIE(true));
        d.update();
        assertEquals(false, d.justPressed());
        d.update();
        assertEquals(false, d.justPressed());
        d.update();
        assertEquals(true, d.justPressed());
    }

    @Test
    public void testJustReleased() {
        DigitalInputEdgeDetector d;

        d = new DigitalInputEdgeDetector(trueIE);
        d.update();
        assertEquals(false, d.justReleased());

        d = new DigitalInputEdgeDetector(falseIE);
        d.update();
        assertEquals(false, d.justReleased());

        d = new DigitalInputEdgeDetector(flipFlopIE(false));
        d.update();
        assertEquals(false, d.justReleased());
        d.update();
        assertEquals(false, d.justReleased());
        d.update();
        assertEquals(true, d.justReleased());

        d = new DigitalInputEdgeDetector(flipFlopIE(true));
        d.update();
        assertEquals(false, d.justReleased());
        d.update();
        assertEquals(true, d.justReleased());
        d.update();
        assertEquals(false, d.justReleased());
    }

}
