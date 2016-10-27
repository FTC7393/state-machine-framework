package ftc.electronvolts.statemachine;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * A transition contains an end condition and an associated statename for the
 * next state to be executed
 */
public class Transition {
    private final EndCondition endCondition;
    private final StateName nextStateName;

    /**
     * On creation, the transition must be given an end condition and the name
     * of the next state
     *
     * @param endCondition the end condition
     * @param nextStateName the name of the next state
     */
    public Transition(EndCondition endCondition, StateName nextStateName) {
        this.endCondition = endCondition;
        this.nextStateName = nextStateName;
    }

    public EndCondition getEndCondition() {
        return endCondition;
    }

    public StateName getNextStateName() {
        return nextStateName;
    }
}