package ftc.electronvolts.test.statemachine;

import static org.junit.Assert.*;

import org.junit.Test;

import ftc.electronvolts.statemachine.BasicAbstractState;
import ftc.electronvolts.statemachine.StateName;

public class BasicAbstractStateTest {

    private enum S implements StateName {
        STATE1, STATE2
    }

    private class MyBasicAbstractState extends BasicAbstractState {
        public boolean done = false;
        public String log = "";

        public MyBasicAbstractState(StateName stateName) {
            super(stateName);
        }

        @Override
        public void init() {
            log += "init ";
        }

        @Override
        public boolean isDone() {
            log += "isDone ";
            return done;
        }

        @Override
        public StateName getNextStateName() {
            log += "getNextStateName ";
            return S.STATE2;
        }

    }

    @Test
    public void testBasicAbstractState() {
        MyBasicAbstractState b = new MyBasicAbstractState(S.STATE1);
        StateName s;

        s = b.act();
        assertEquals(S.STATE1, s);
        assertEquals("init isDone ", b.log);
        b.log = "";

        s = b.act();
        assertEquals(S.STATE1, s);
        assertEquals("isDone ", b.log);
        b.log = "";

        s = b.act();
        assertEquals(S.STATE1, s);
        assertEquals("isDone ", b.log);
        b.log = "";
        
        b.done = true;

        s = b.act();
        assertEquals(S.STATE2, s);
        assertEquals("isDone getNextStateName ", b.log);
        b.log = "";
    }

    @Test
    public void testGetName() {
        BasicAbstractState basicAbstractState = new BasicAbstractState(S.STATE1) {

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public void init() {
            }

            @Override
            public StateName getNextStateName() {
                return null;
            }
        };
        assertEquals(S.STATE1, basicAbstractState.getName());
    }

}
