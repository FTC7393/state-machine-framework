package ftc.electronvolts.test.statemachine;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ftc.electronvolts.statemachine.EndConditions;
import ftc.electronvolts.statemachine.State;
import ftc.electronvolts.statemachine.StateMachineBuilder;
import ftc.electronvolts.statemachine.StateMap;
import ftc.electronvolts.statemachine.StateName;
import ftc.electronvolts.statemachine.States;
import ftc.electronvolts.statemachine.SubStatesState;
import ftc.electronvolts.util.BasicResultReceiver;
import ftc.electronvolts.util.ResultReceiver;

public class StatesTest {

    private enum S implements StateName {
        STATE1, STATE2, STATE3, SUB_STATE1, SUB_STATE2, SUB_STATE3, SUB_STATE4
    }

    @Test
    public void testSubStates() {
        StateMachineBuilder b = new StateMachineBuilder(S.SUB_STATE1);
        b.addEmpty(S.SUB_STATE1, S.SUB_STATE2);
        b.addEmpty(S.SUB_STATE2, S.SUB_STATE3);
        b.addEmpty(S.SUB_STATE3, S.SUB_STATE4);

        Map<StateName, StateName> subStateToState = new HashMap<>();
        subStateToState.put(S.SUB_STATE4, S.STATE2);
        SubStatesState s = States.subStates(subStateToState, b);

        assertEquals(S.SUB_STATE1, s.getStateMachine().getCurrentStateName());
        assertEquals(null, s.act());

        assertEquals(S.SUB_STATE2, s.getStateMachine().getCurrentStateName());
        assertEquals(null, s.act());

        assertEquals(S.SUB_STATE3, s.getStateMachine().getCurrentStateName());
        assertEquals(S.STATE2, s.act());

        assertEquals(S.SUB_STATE4, s.getStateMachine().getCurrentStateName());
        assertEquals(S.STATE2, s.act());

        assertEquals(S.SUB_STATE4, s.getStateMachine().getCurrentStateName());
    }

    @Test
    public void testStop() {
        State s = States.stop();

        assertEquals(null, s.act());
        assertEquals(null, s.act());
        assertEquals(null, s.act());
        assertEquals(null, s.act());
    }

    @Test
    public void testEmpty() {
        State s = States.empty(StateMap.of(S.STATE2, EndConditions.now()));
        assertEquals(S.STATE2, s.act());
    }

    @Test
    public void testBasicEmpty() {
        State s = States.empty(S.STATE2);
        assertEquals(S.STATE2, s.act());
    }

    // @Test
    // public void testRunThread() {
    // fail("Not yet implemented");
    // }

    @Test
    public void testBranchStateNameBooleanStateNameStateName() {
        State s;

        s = States.branch(S.STATE2, S.STATE3, true);
        assertEquals(S.STATE2, s.act());

        s = States.branch(S.STATE2, S.STATE3, false);
        assertEquals(S.STATE3, s.act());
    }

    @Test
    public void testBranchStateNameBooleanStateNameStateNameStateName() {
        State s;

        s = States.branch(S.STATE1, S.STATE2, S.STATE3, true);
        assertEquals(S.STATE1, s.act());

        s = States.branch(S.STATE1, S.STATE2, S.STATE3, false);
        assertEquals(S.STATE2, s.act());

        Boolean b = null;
        s = States.branch(S.STATE1, S.STATE2, S.STATE3, b);
        assertEquals(S.STATE3, s.act());
    }

    @Test
    public void testBranchStateNameResultReceiverOfBooleanStateNameStateNameStateName() {
        State s;
        ResultReceiver<Boolean> resultReceiver = new BasicResultReceiver<>();

        resultReceiver.setValue(true);
        s = States.branch(S.STATE1, S.STATE2, S.STATE3, resultReceiver);
        assertEquals(S.STATE1, s.act());

        resultReceiver.setValue(false);
        s = States.branch(S.STATE1, S.STATE2, S.STATE3, resultReceiver);
        assertEquals(S.STATE2, s.act());

        resultReceiver.setValue(null);
        s = States.branch(S.STATE1, S.STATE2, S.STATE3, resultReceiver);
        assertEquals(S.STATE3, s.act());
    }

    @Test
    public void testCount() {
        State s = States.count(S.STATE2, S.STATE3, 2);
        assertEquals(S.STATE2, s.act());
        assertEquals(S.STATE2, s.act());
        assertEquals(S.STATE3, s.act());
        assertEquals(S.STATE3, s.act());
        assertEquals(S.STATE3, s.act());
    }

}
