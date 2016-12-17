package ftc.electronvolts.test.statemachine;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.statemachine.AbstractState;
import ftc.electronvolts.statemachine.EndCondition;
import ftc.electronvolts.statemachine.StateMap;
import ftc.electronvolts.statemachine.StateName;

public class AbstractStateTest {

    private enum S  implements StateName{
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

        MyEndCondition myEndCondition = new MyEndCondition();

        AbstractState abstractState = new AbstractState(StateMap.of(S.STATE2, myEndCondition)) {

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
        assertEquals(null, s);
        assertEquals("init run ", vars.log);
        vars.log = "";

        s = abstractState.act();
        assertEquals(null, s);
        assertEquals("run ", vars.log);
        vars.log = "";

        s = abstractState.act();
        assertEquals(null, s);
        assertEquals("run ", vars.log);
        vars.log = "";

        myEndCondition.done = true;

        s = abstractState.act();
        assertEquals(S.STATE2, s);
        assertEquals("dispose ", vars.log);
        vars.log = "";
    }
}
