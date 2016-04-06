package ftc.electronvolts.yr2015.statemachine;

import java.util.List;

import ftc.electronvolts.yr2015.statemachine.State;

/**
 * AbstractState is a simple state that handles transitions.
 */
public abstract class AbstractState implements State {
    private final List<Transition> transitions;

    private boolean isStarted = false;
    private final StateName stateName;

    /**
     * An abstract state must contain a list of transitions, containing end conditions and their respective states.
     * @param stateName the name of this state
     * @param transitions the list of transitions
     * @see Transition
     */
    AbstractState(StateName stateName, List<Transition> transitions) {
        this.stateName = stateName;
        this.transitions = transitions;
    }
 
    /**
     * @return the state's name for use in the builder
     */
    @Override
    public StateName getName(){
        return stateName;
    }
    
    /**
     * Run once when the state is initialized.
     */
    abstract void init();
    
    /**
     * Run every loop. This should do a majority of the work in the state.
     */
    abstract void run();
    
    /**
     * Run once when the state is finished before the next state is initialized. This should do any cleaning up, such as stopping any started motors.
     */
    abstract void dispose();

    public StateName act() {
        if (!isStarted) {
            init();
            isStarted = true;
            for (Transition t : transitions) {
                t.getEndCondition().init();
            }
        }
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
