package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.ValueHistory;

public class ValueHistoryTest {

    @Test
    public void testValueHistory() {
        ValueHistory valueHistory = new ValueHistory(10);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(0, valueHistory.getAverage(), 0);
        assertEquals(0, valueHistory.getStandardDeviation(), 0);
        assertEquals(0, valueHistory.getCoefficientOfVariation(), 0);
        assertEquals(10, valueHistory.getLength());
        assertEquals(0, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(0);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(0, valueHistory.getAverage(), 0);
        assertEquals(0, valueHistory.getStandardDeviation(), 0);
        assertEquals(Double.NaN, valueHistory.getCoefficientOfVariation(), 0);
        assertEquals(10, valueHistory.getLength());
        assertEquals(1, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(1);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(0.5, valueHistory.getAverage(), 0);
        assertEquals(0.5, valueHistory.getStandardDeviation(), 0);
        assertEquals(1, valueHistory.getCoefficientOfVariation(), 0);
        assertEquals(10, valueHistory.getLength());
        assertEquals(2, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(2);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(1, valueHistory.getAverage(), 0);
        assertEquals(0.81649658092773, valueHistory.getStandardDeviation(), 1e-12);
        assertEquals(0.81649658092773, valueHistory.getCoefficientOfVariation(), 1e-12);
        assertEquals(10, valueHistory.getLength());
        assertEquals(3, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(3);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(10, valueHistory.getLength());
        assertEquals(4, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(4);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(10, valueHistory.getLength());
        assertEquals(5, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(5);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(10, valueHistory.getLength());
        assertEquals(6, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(6);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(10, valueHistory.getLength());
        assertEquals(7, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(7);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(10, valueHistory.getLength());
        assertEquals(8, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(8);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(10, valueHistory.getLength());
        assertEquals(9, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(9);
        
        assertTrue(valueHistory.areAllActive());
        assertEquals(10, valueHistory.getLength());
        assertEquals(10, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(10);
        
        assertTrue(valueHistory.areAllActive());
        assertEquals(10, valueHistory.getLength());
        assertEquals(10, valueHistory.getNumActive());
    }

}
