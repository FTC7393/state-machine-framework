package ftc.electronvolts.test.statemachine;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ftc.electronvolts.statemachine.State;
import ftc.electronvolts.statemachine.StateMachine;
import ftc.electronvolts.statemachine.StateName;
import ftc.electronvolts.statemachine.States;

public class StateMachineTest {

    private enum S implements StateName{
        STATE1, STATE2, STATE3
    }

    @Test
    public void testStateMachine() {
        Map<StateName, State> stateMap = new HashMap<>();
        stateMap.put(S.STATE1, States.empty(S.STATE2));
        stateMap.put(S.STATE2, States.empty(S.STATE3));
        stateMap.put(S.STATE3, States.stop());

        StateMachine stateMachine = new StateMachine(stateMap, S.STATE1);
        assertEquals(S.STATE1, stateMachine.getCurrentStateName());
        stateMachine.act();
        assertEquals(S.STATE2, stateMachine.getCurrentStateName());
        stateMachine.act();
        assertEquals(S.STATE3, stateMachine.getCurrentStateName());
        stateMachine.act();
        assertEquals(S.STATE3, stateMachine.getCurrentStateName());
        stateMachine.act();
        assertEquals(S.STATE3, stateMachine.getCurrentStateName());
    }

}
