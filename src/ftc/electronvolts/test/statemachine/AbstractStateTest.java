package ftc.electronvolts.test.statemachine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ftc.electronvolts.statemachine.AbstractState;
import ftc.electronvolts.statemachine.EndCondition;
import ftc.electronvolts.statemachine.StateName;
import ftc.electronvolts.statemachine.Transition;

public class AbstractStateTest {

    private enum S implements StateName {
        STATE1, STATE2
    }

    private class Vars {
        public String log = "";
    }

    private class MyEndCondition implements EndCondition {
        public boolean done = false;

        @Override
        public void init() {

        }

        @Override
        public boolean isDone() {
            return done;
        }
    }

    @Test
    public void testAbstractState() {
        final Vars vars = new Vars();
        StateName s;

        List<Transition> transitions = new ArrayList<>();
        MyEndCondition myEndCondition = new MyEndCondition();
        transitions.add(new Transition(myEndCondition, S.STATE2));

        AbstractState abstractState = new AbstractState(S.STATE1, transitions) {

            @Override
            public void init() {
                vars.log += "init ";
            }

            @Override
            public void run() {
                vars.log += "run ";
            }

            @Override
            public void dispose() {
                vars.log += "dispose ";
            }
        };

        s = abstractState.act();
        assertEquals(S.STATE1, s);
        assertEquals("init run ", vars.log);
        vars.log = "";

        s = abstractState.act();
        assertEquals(S.STATE1, s);
        assertEquals("run ", vars.log);
        vars.log = "";

        s = abstractState.act();
        assertEquals(S.STATE1, s);
        assertEquals("run ", vars.log);
        vars.log = "";

        myEndCondition.done = true;

        s = abstractState.act();
        assertEquals(S.STATE2, s);
        assertEquals("dispose ", vars.log);
        vars.log = "";
    }

    @Test
    public void testGetName() {
        List<Transition> transitions = new ArrayList<>();
        AbstractState abstractState = new AbstractState(S.STATE1, transitions) {

            @Override
            public void init() {
            }

            @Override
            public void run() {
            }

            @Override
            public void dispose() {
            }
        };
        assertEquals(S.STATE1, abstractState.getName());
    }
}
