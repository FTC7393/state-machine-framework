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
        assertEquals(10, valueHistory.getLength());
        assertEquals(0, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(1);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(1, valueHistory.getAverage(), 0);
        assertEquals(0, valueHistory.getStandardDeviation(), 0);
        assertEquals(10, valueHistory.getLength());
        assertEquals(1, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(2);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(1.5, valueHistory.getAverage(), 0);
        assertEquals(0.5, valueHistory.getStandardDeviation(), 0);
        assertEquals(10, valueHistory.getLength());
        assertEquals(2, valueHistory.getNumActive());
        
        valueHistory.replaceOldestValue(3);
        
        assertFalse(valueHistory.areAllActive());
        assertEquals(2, valueHistory.getAverage(), 0);
        assertEquals(0.81649658092773, valueHistory.getStandardDeviation(), 1e-12);
        assertEquals(10, valueHistory.getLength());
        assertEquals(3, valueHistory.getNumActive());
    }

}
