package ftc.electronvolts.statemachine;

import java.util.Map;

/**
 * This class uses the stateMap made by the stateMachineBuilder to run the states and preform the
 * transitions between them
 */
public class StateMachine {
    private final Map<StateName, State> stateMap; // This links the names of each state to the actual state
    private State currentState; //the current active state
    private StateName currentStateName; //the name of the current state

    /**
     * @param stateMap the state machine structure
     * @param firstStateName the name of the state to start with
     */
    public StateMachine(Map<StateName, State> stateMap, StateName firstStateName){
        this.stateMap = stateMap;
        currentStateName = firstStateName;
        currentState = stateMap.get(firstStateName);
        //if the stateMap does not have the firstStateName as a key, there is no way to fix it here
    }

    /**
     * call this each time through the loop
     * It runs the current state, then looks up the next state based on the name given by the current state.
     */
    public void act(){
        // tell the current state to act and return the next state
        StateName nextStateName = currentState.act();
        // if the next state name returned is null or the same as this state, stay in the current state
        if(nextStateName != null && nextStateName != currentStateName){
            //if the state requested exists
            if(stateMap.containsKey(nextStateName)){
                //update the current state
                currentStateName = nextStateName;
                currentState = stateMap.get(currentStateName);
            }
        }
    }

    /**
     * used for telemetry purposes
     * @return the name of the current state
     */
    public StateName getCurrentStateName() {
        return currentStateName;
    }
}