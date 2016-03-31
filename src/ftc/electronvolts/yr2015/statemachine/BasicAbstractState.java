package ftc.electronvolts.yr2015.statemachine;

/**
 * BasicAbstractState removes the need to use transitions. It requires that the state manages when it should be completed.
 */
public abstract class BasicAbstractState implements State {
    private boolean isStarted = false;

    /**
     * Run once when the state is initialized
     */
    abstract void init();
    
    /**
     * The state does all of the work in this method
     * @return true if state is finished executing
     */
    abstract boolean isDone();
    
    /**
     * The next state to be executed
     * @return the name of the next state to be executed
     */
    abstract StateName getNextStateName();

    @Override
    public StateName act() {
        if (!isStarted) {
            init();
            isStarted = true;
        }
        if (isDone()) {
            isStarted = false;
            return getNextStateName();
        }
        return null; //null means no transition
    }
}