package ftc.electronvolts.yr2015.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vandejd1 on 3/2/16.
 * FTC Team EV 7393
 */
public class StateMachineBuilder {
    private Map<StateName, State> stateMap = new HashMap<StateName, State>();
    private final StateName firstStateName;

    public List<Transition> ts(EndCondition endCondition, StateName nextStateName) {
        return toList(new Transition(endCondition, nextStateName));
    }

    private <T>List<T> toList(T t) {
		List<T> list = new ArrayList<T>(1);
		list.add(t);
		return list;
	}

	public List<Transition> ts(long durationMillis, StateName nextStateName) {
        return ts(EndConditions.timed(durationMillis), nextStateName);
    }

    public EndCondition timed(long durationMillis){
        return EndConditions.timed(durationMillis);
    }

    public StateMachineBuilder(StateName firstStateName) {
        this.firstStateName = firstStateName;
    }

    public void add(StateName stateName, State state){
        stateMap.put(stateName, state);
    }

//    public State getState(StateName stateName){
//        return stateMap.get(stateName);
//    }

    public StateMachine build(){
        return new StateMachine(stateMap, firstStateName);
//        for(Map.Entry<StateName, State> entry: stateMap.entrySet()) {
//            StateName stateName = entry.getKey();
//            State state = entry.getValue();
//        }
    }
}
