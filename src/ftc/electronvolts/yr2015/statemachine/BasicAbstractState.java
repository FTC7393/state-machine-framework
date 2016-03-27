package ftc.electronvolts.yr2015.statemachine;

import java.util.Map;

/**
 * Created by vandejd1 on 11/25/15.
 * FTC Team EV 7393
 */
public abstract class BasicAbstractState implements State {
    private boolean isStarted = false;
//    protected String stateName = "BasicAbstractState";

    abstract void init();
    abstract boolean isDone();
    abstract StateName getNextStateName();

    @Override
    public StateName act() {
        if (!isStarted) {
            init();
            isStarted = true;
//            Hardware.getInstance().getTelem().clearData();
//            Hardware.getInstance().getTelem().addData("State", "State: " + stateName);
        }
        if (isDone()) {
            isStarted = false;
//            getNextStateName().act();
            return getNextStateName();
        }
        return null; //null means no transition
    }
}