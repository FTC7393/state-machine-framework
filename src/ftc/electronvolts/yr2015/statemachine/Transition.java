package ftc.electronvolts.yr2015.statemachine;

/**
 * Created by vandejd1 on 10/17/15.
 * FTC Team EV 7393
 */
public class Transition {
    private final EndCondition endCondition;
    private final StateName nextStateName;

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