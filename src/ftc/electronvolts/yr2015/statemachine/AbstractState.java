package ftc.electronvolts.yr2015.statemachine;

import java.util.List;

import ftc.electronvolts.yr2015.statemachine.State;

/**
 * Created by vandejd1 on 10/17/15.
 * FTC Team EV 7393
 */
public abstract class AbstractState implements State {
    private final List<Transition> transitions;

    private boolean isStarted = false;

    AbstractState(List<Transition> transitions) {
        this.transitions = transitions;
    }
//    protected String stateName = "AbstractState";

//    @Override
//    public void setTransitions(List<Transition> transitions){
//        this.transitions = transitions;
//    }

    abstract void init();
    abstract void run();
    abstract void dispose();

    public StateName act() {
        if (!isStarted) {
            init();
            isStarted = true;
//            String endConditionsNames = "";
            for (Transition t : transitions) {
                t.getEndCondition().init();
//                endConditionsNames += " " + t.getClass().getSimpleName();
            }
//            Hardware.getInstance().getTelem().clearData();
//            Hardware.getInstance().getTelem().addData("State", "State: " + stateName + " ECS:" + endConditionsNames);
        }
//        if (transitions == null) {
//            run();
//            return this;
//        }
        for (Transition t : transitions) {
            if (t.getEndCondition().isDone()) {
                dispose();
                isStarted = false;
//                t.getNextStateName().act();
                return t.getNextStateName();
            }
        }
        run();
        return null;
    }
}
