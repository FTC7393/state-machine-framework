package ftc.electronvolts.yr2015.statemachine;

import java.util.Map;

/**
 * Created by vandejd1 on 3/2/16.
 * FTC Team EV 7393
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
        if(stateMap.containsKey(firstStateName)){ //check for the key so the robot does not crash
            currentState = stateMap.get(firstStateName);
        }
    }

    /**
     * call this each time through the loop
     */
    public void act(){
        StateName nextStateName = currentState.act(); //tell the current state to act and return the next state
        if(nextStateName != null){ // if the next state name returned is null, stay in the current state
            if(stateMap.containsKey(nextStateName)){ //if the state requested exists, go to it.
                currentStateName = nextStateName;
                currentState = stateMap.get(currentStateName);
            }
        }
    }
    
    public StateName getCurrentStateName() {
        return currentStateName;
    }
}
