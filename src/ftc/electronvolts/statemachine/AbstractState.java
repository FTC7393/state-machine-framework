package ftc.electronvolts.statemachine;

import java.util.Map;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * AbstractState is a simple state that handles transitions.
 */
public abstract class AbstractState implements State {
    // Map of possible transitions to other states
    private final Map<StateName, EndCondition> transitions;

    private boolean isRunning = false;

    /**
     * An abstract state must contain a map of transitions, containing end
     * conditions and their respective next state names.
     *
     * @param transitions the map of EndCondition to NextState
     * @see Transition
     */
    public AbstractState(Map<StateName, EndCondition> transitions) {
        this.transitions = transitions;
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
            for(Map.Entry<StateName, EndCondition> entry : transitions.entrySet()) {
                EndCondition endCondition = entry.getValue();
                endCondition.init();
            }
        }
        for(Map.Entry<StateName, EndCondition> entry : transitions.entrySet()) {
            StateName stateName = entry.getKey();
            EndCondition endCondition = entry.getValue();
            if (endCondition.isDone()) {
                dispose();
                isRunning = false;
                return stateName;
            }
        }
        run();
        return null;
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
