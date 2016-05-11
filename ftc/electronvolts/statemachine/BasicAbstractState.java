package ftc.electronvolts.statemachine;

/**
 * BasicAbstractState removes the need to use transitions. It requires that the state manages when it should be completed.
 */
public abstract class BasicAbstractState implements State {
    private boolean isStarted = false;
    private final StateName stateName;

    /**
     * 
     * @param stateName the name of this state
     */
    public BasicAbstractState(StateName stateName){
        this.stateName = stateName;
    }
    
    /**
     * @return the state's name for use in the builder
     */
    @Override
    public StateName getName(){
        return stateName;
    }
    
    /**
     * Run once when the state is initialized
     */
    public abstract void init();
    
    /**
     * The state does all of the work in this method
     * @return true if state is finished executing
     */
    public abstract boolean isDone();
    
    /**
     * The next state to be executed
     * @return the name of the next state to be executed
     */
    public abstract StateName getNextStateName();

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
        return stateName;
    }
}