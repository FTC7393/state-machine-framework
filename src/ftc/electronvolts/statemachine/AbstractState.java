package ftc.electronvolts.statemachine;

import java.util.Collection;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * AbstractState is a simple state that handles transitions.
 */
public abstract class AbstractState implements State {
    // List of possible transitions to other states
    private final Collection<Transition> transitions;

    private boolean isRunning = false;
    private final StateName stateName;

    /**
     * An abstract state must contain a list of transitions, containing end
     * conditions and their respective states.
     *
     * @param stateName the name of this state
     * @param transitions the list of transitions
     * @see Transition
     */
    public AbstractState(StateName stateName, Collection<Transition> transitions) {
        this.stateName = stateName;
        this.transitions = transitions;
    }

    /**
     * Implementation of the getName() method in the State interface
     *
     * @return the state's name for use in the builder
     */
    @Override
    public StateName getName() {
        return stateName;
    }

    /**
     * Implementation of the act() method in the State interface
     * Cycles through Transitions to run one of the next states if its
     * EndCondition has been met. Does "edge detection" on the state, running
     * init() for the first cycle, run() for subsequent cycles, and dispose()
     * for the last cycle.
     *
     * @return The name of the state to be run next cycle (returns itself if
     *         there are no state changes)
     */
    @Override
    public StateName act() {
        if (!isRunning) {
            init();
            isRunning = true;
            for (Transition t : transitions) {
                t.getEndCondition().init();
            }
        }
        for (Transition t : transitions) {
            if (t.getEndCondition().isDone()) {
                dispose();
                isRunning = false;
                return t.getNextStateName();
            }
        }
        run();
        return stateName;
    }

    /**
     * Run once when the state is initialized.
     * This can run tasks such as starting motors.
     */
    public abstract void init();

    /**
     * Run every cycle after init()
     * This can do tasks such as gyro stabilization or line following
     */
    public abstract void run();

    /**
     * Run once when the state is finished before the next state is initialized.
     * This can do any cleaning up, such as stopping any started motors.
     */
    public abstract void dispose();
}
