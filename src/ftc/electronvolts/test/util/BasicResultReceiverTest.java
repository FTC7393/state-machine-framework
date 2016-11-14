package ftc.electronvolts.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.util.BasicResultReceiver;
import ftc.electronvolts.util.ResultReceiver;

public class BasicResultReceiverTest {

    @Test
    public void testBasicResultReceiver() {
        ResultReceiver<String> resultReceiver = new BasicResultReceiver<>();
        assertFalse(resultReceiver.isReady());
        assertTrue(resultReceiver.getValue() == null);
        
        resultReceiver.setValue("123");
        assertTrue(resultReceiver.isReady());
        assertTrue("123".equals(resultReceiver.getValue()));
        
        resultReceiver.clear();
        assertFalse(resultReceiver.isReady());
        assertTrue(resultReceiver.getValue() == null);
    }
}
