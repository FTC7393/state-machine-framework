package ftc.electronvolts.yr2015.statemachine;

import java.security.KeyException;
import java.util.Map;

import ftc.electronvolts.yr2015.util.Hardware;

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
//        if(stateMap.containsKey(currentStateName)){
            currentState = stateMap.get(currentStateName);
//        } else {
//            throw new
//        }
    }

    public void act(){
        Hardware.getInstance().getTelem().addData("current state: " + currentStateName.name(), 0);
        StateName nextStateName = currentState.act();
        if(nextStateName != null){
//            Hardware.getInstance().getTelem().addData("Next State!!!", 1);
            Hardware.getInstance().getTelem().addData("next state:" + nextStateName.name(), 1);
//        if(stateMap.containsKey(nextStateName)){
            currentStateName = nextStateName;
            currentState = stateMap.get(currentStateName);
//            Hardware.getInstance().getTelem().addData(stateMap.toString(), 2);
//        } else {
//            throw new
        }
        //}
    }
}
