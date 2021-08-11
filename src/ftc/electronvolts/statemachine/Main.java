package ftc.electronvolts.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ftc.electronvolts.statemachine.State;
import ftc.electronvolts.statemachine.StateName;

public class Main {
	enum S implements StateName {
		DRIVE,
		WALL,
		TIMEOUT,
//		PRINT_TWICE,
//		BRANCH,
		STOP
	}

	public static void main(String[] args) throws StateNotFoundError {
		Map<StateName, State> stateMap = new HashMap<>();
		
		//TODO Abhi's checks on unused states
		//TODO Mark's counter instead of boolean
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(TaskFactory.count(3, S.TIMEOUT));
		tasks.add(TaskFactory.count(2, S.WALL)); //TODO order based priority
		
		stateMap.put(S.DRIVE, StateFactory.behavior(BehaviorFactory.print("vroooooooooom"), tasks));

		stateMap.put(S.WALL, StateFactory.behavior(BehaviorFactory.print("hit a wall :("), TaskFactory.count(3, S.STOP)));
		stateMap.put(S.TIMEOUT, StateFactory.behavior(BehaviorFactory.print("timed out :("), TaskFactory.count(3, S.STOP)));
		//TODO ordering of next state for priority, possibly with orderedmap or just a list
		
//		stateMap.put(S.PRINT_TWICE, StateFactory.printTwice(S.BRANCH, "print twice state"));
//		stateMap.put(S.BRANCH, StateFactory.branch(S.STOP, S.PRINT_TWICE));
		stateMap.put(S.STOP, StateFactory.stop());
		
		StateMachine sm = new StateMachine(stateMap, S.DRIVE, S.values());

		while (true) {
			sm.act();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
