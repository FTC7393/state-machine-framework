package ftc.electronvolts.test.statemachine;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.statemachine.StateMachine;
import ftc.electronvolts.statemachine.StateMachineBuilder;
import ftc.electronvolts.statemachine.StateName;
import ftc.electronvolts.statemachine.States;

public class StateMachineBuilderTest {

    private enum S implements StateName {
        STATE1, STATE2, STATE3
    }

    @Test
    public void testStateMachineBuilder() {
        StateMachineBuilder b = new StateMachineBuilder(S.STATE1);
        b.addEmpty(S.STATE1, S.STATE2);
        b.add(S.STATE2, States.empty(S.STATE3));
        b.addStop(S.STATE3);

        StateMachine stateMachine = b.build();

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

    @Test
    public void testBuildStateName() {
        StateMachineBuilder b = new StateMachineBuilder(S.STATE1);
        b.addEmpty(S.STATE1, S.STATE2);
        b.add(S.STATE2, States.empty(S.STATE3));
        b.addStop(S.STATE3);

        StateMachine stateMachine = b.build(S.STATE2);

        assertEquals(S.STATE2, stateMachine.getCurrentStateName());
        stateMachine.act();
        assertEquals(S.STATE3, stateMachine.getCurrentStateName());
        stateMachine.act();
        assertEquals(S.STATE3, stateMachine.getCurrentStateName());
        stateMachine.act();
        assertEquals(S.STATE3, stateMachine.getCurrentStateName());
    }

    @Test
    public void testAddCount() {
        StateMachineBuilder b = new StateMachineBuilder(S.STATE1);
        b.addEmpty(S.STATE1, S.STATE2);
        b.addCount(S.STATE2, S.STATE1, S.STATE3, 2);
        b.addStop(S.STATE3);

        StateMachine stateMachine = b.build();

        assertEquals(S.STATE1, stateMachine.getCurrentStateName());
        stateMachine.act();
        assertEquals(S.STATE2, stateMachine.getCurrentStateName());
        stateMachine.act();

        assertEquals(S.STATE1, stateMachine.getCurrentStateName());
        stateMachine.act();
        assertEquals(S.STATE2, stateMachine.getCurrentStateName());
        stateMachine.act();

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
