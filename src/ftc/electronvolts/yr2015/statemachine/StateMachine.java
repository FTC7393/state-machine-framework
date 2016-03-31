package ftc.electronvolts.yr2015.statemachine;

import java.util.Map;

/**
 * Created by vandejd1 on 3/2/16.
 * FTC Team EV 7393
 */
public class StateMachine {
    private final Map<StateName, State> stateMap;
    private State currentState;
    private StateName currentStateName;

    public StateMachine(Map<StateName, State> stateMap, StateName currentStateName){
        this.currentStateName = currentStateName;
        this.stateMap = stateMap;
        currentState = stateMap.get(currentStateName);
    }

    public void act(){
    	StateName nextStateName = currentState.act();
        if(nextStateName != null){
            currentStateName = nextStateName;
            currentState = stateMap.get(currentStateName);
        }
    }
}
