package ftc.electronvolts.test.statemachine;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.statemachine.EndConditions;
import ftc.electronvolts.statemachine.StateMap;
import ftc.electronvolts.statemachine.StateName;

public class StateMapTest {

    private enum S implements StateName {
        STATE1, STATE2, STATE3
    }

    @Test
    public void testOf() {
        StateMap s = StateMap.of();
        assertEquals(0, s.entrySet().size());
    }

    @Test
    public void testOfEndConditionStateName() {
        StateMap s = StateMap.of(S.STATE1, EndConditions.never());
        assertEquals(1, s.entrySet().size());
        assertFalse(s.get(S.STATE1).isDone());
    }

    @Test
    public void testOfEndConditionStateNameEndConditionStateName() {
        StateMap s = StateMap.of(S.STATE1, EndConditions.never(), S.STATE2, EndConditions.now());
        assertEquals(2, s.entrySet().size());
        assertFalse(s.get(S.STATE1).isDone());
        assertTrue(s.get(S.STATE2).isDone());
    }

    @Test
    public void testOfEntryArray() {
        StateMap s = StateMap.of(new StateMap.Entry(S.STATE1, EndConditions.never()), new StateMap.Entry(S.STATE2, EndConditions.now()));
        assertEquals(2, s.entrySet().size());
        assertFalse(s.get(S.STATE1).isDone());
        assertTrue(s.get(S.STATE2).isDone());
    }

}
