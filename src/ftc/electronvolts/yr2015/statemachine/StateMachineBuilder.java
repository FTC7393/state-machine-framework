package ftc.electronvolts.yr2015.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The state machine builder simplifies the creation of the state machine. The builder requires an enum with values for each state. For example:
 * enum stateNames {
 * 	Bill, John
 * };
 * You would then initialize StateMachineBuilder with the first state to be run:
 * StateMachineBuilder builder = new StateMachineBuilder(Bill);
 * 
 * Note: The states are not added to the state machine yet. The individual states still need to be initialized and associated with an enum value.
 */
public class StateMachineBuilder {
    private Map<StateName, State> stateMap = new HashMap<StateName, State>();
    private final StateName firstStateName;
    
    /**
     * A constructor for the builder. The State machine must have a state to start with
     * @param firstStateName the first state to be executed in order
     */
    public StateMachineBuilder(StateName firstStateName) {
        this.firstStateName = firstStateName;
    }
    
    /**
     * Create a new transition
     * @param endCondition the end condition for the next state
     * @param nextStateName the name of the enum for the next state
     * @return a list containing the one transition
     */
    public List<Transition> ts(EndCondition endCondition, StateName nextStateName) {
        return toList(new Transition(endCondition, nextStateName));
    }

    private <T>List<T> toList(T t) {
		List<T> list = new ArrayList<T>(1);
		list.add(t);
		return list;
	}
    
    /**
     * Create a new transition with a timed end condition
     * @param durationMillis the amount of time to wait before advancing to the next state
     * @param nextStateName the enum value associated with the next state
     * @return a list containing the one transition
     */
	public List<Transition> ts(long durationMillis, StateName nextStateName) {
        return ts(EndConditions.timed(durationMillis), nextStateName);
    }

	/**
	 * @see EndConditions
	 */
    public EndCondition timed(long durationMillis){
        return EndConditions.timed(durationMillis);
    }

    /**
     * Add a state to the state machine
     * @param stateName the enum value to be associated with the state 
     * @param state the state to be added
     */
    public void add(StateName stateName, State state){
        stateMap.put(stateName, state);
    }
    
    /**
     * Build the state machine with the added states
     * @return the output state machine
     * @see StateMachine
     */
    public StateMachine build(){
        return new StateMachine(stateMap, firstStateName);
    }
}
