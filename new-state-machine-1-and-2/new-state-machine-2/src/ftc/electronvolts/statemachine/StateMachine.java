package ftc.electronvolts.statemachine;

import java.util.Map;

public class StateMachine {
	public StateMachine(Map<StateName, State> stateMap, StateName current, StateName[] allPossibleStateNames) {
		this.stateMap = stateMap;
		this.current = stateMap.get(current);
		this.currentStateName = current;
		

		for (StateName sn : allPossibleStateNames) {
			if (!stateMap.containsKey(sn) || stateMap.get(sn) == null) {
				throw new StateNotFoundError();
			}
//			System.out.println(sn.name() + " " + (stateMap.containsKey(sn) && stateMap.get(sn) != null));
		} 
	}

	private final Map<StateName, State> stateMap;
	private State current;
	private StateName currentStateName;
	
	public void run() { //TODO return statename
		StateName nextName = current.act();
		if (nextName != null && stateMap.containsKey(nextName)) {
			currentStateName = nextName;
			current = stateMap.get(nextName);
		}
	}
	
	public StateName getCurrentStateName() {
        return currentStateName;
    }
}
