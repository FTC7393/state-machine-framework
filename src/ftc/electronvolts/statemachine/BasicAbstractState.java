package ftc.electronvolts.statemachine;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * BasicAbstractState removes the need to use transitions. It requires that the
 * state manages when it should be completed. It is used when the end condition
 * is directly linked to the state's action, such as a state that turns a servo
 * and exits when the servo is done, or a state that detects red or blue using a
 * color sensor and activates a different state based on the result.
 */
public abstract class BasicAbstractState implements State {
    private boolean isRunning = false;

    /**
     * Implementation of the act() method in the State interface
     * 
     * Runs init() on the first call of the act() method, then isDone() on
     * subsequent calls. If isDone() returns true, getNextStateName is called
     * and the value from it is returned as the next state
     * 
     * @return The name of the state to be run next cycle (returns null if there
     *         are no state changes)
     */
    @Override
    public StateName act() {
        if (!isRunning) {
            init();
            isRunning = true;
        }
        if (isDone()) {
            isRunning = false;
            return getNextStateName();
        }
        return null;
    }

    /**
     * Run once when the state is initialized This can do tasks such as setting
     * servo positions or turning on LED's
     */
    public abstract void init();

    /**
     * Run every cycle after init() This can do tasks such as checking if a
     * sensor is calibrated, updating a value, exiting right away or even never
     * exiting at all.
     *
     * @return true if state is finished executing
     */
    public abstract boolean isDone();

    /**
     * The possible next states are passed in through the constructor, and one
     * of them is chosen here. This lets the state define how the decision is
     * made about the next state. It could have any number of next states for
     * any number of reasons, for example: 0 next states for a stop state, 1
     * next state for a servo turn state, 3 next states for a detect color state
     * (red, blue, or unknown)
     *
     * @return the name of the next state to be executed
     */
    public abstract StateName getNextStateName();
}