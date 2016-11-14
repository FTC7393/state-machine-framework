package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.AnalogInputScaler;
import ftc.electronvolts.util.Functions;
import ftc.electronvolts.util.InputExtractor;

public class AnalogInputScalerTest {

    private static InputExtractor<Double> pointSix = new InputExtractor<Double>() {
        @Override
        public Double getValue() {
            return 0.6;
        }
    };

    @Test
    public void testAnalogInputScaler() {
        new AnalogInputScaler(pointSix, Functions.none());
    }

    @Test
    public void testUpdate() {
        AnalogInputScaler a = new AnalogInputScaler(pointSix, Functions.none());
        assertEquals(0.6, a.update(), 0);
    }

    @Test
    public void testGetValue() {
        AnalogInputScaler a;

        a = new AnalogInputScaler(pointSix, Functions.none());
        a.update();
        assertEquals(0.6, a.getValue(), 0);

        a = new AnalogInputScaler(pointSix, Functions.squared());
        a.update();
        assertEquals(0.6 * 0.6, a.getValue(), 0);
    }

    @Test
    public void testGetRawValue() {
        AnalogInputScaler a;

        a = new AnalogInputScaler(pointSix, Functions.none());
        a.update();
        assertEquals(0.6, a.getRawValue(), 0);

        a = new AnalogInputScaler(pointSix, Functions.squared());
        a.update();
        assertEquals(0.6, a.getRawValue(), 0);
    }

}
