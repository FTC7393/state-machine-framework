package ftc.electronvolts.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	enum S implements StateName {
		DRIVE,
		WALL,
		TIMEOUT,
//		PRINT_TWICE,
//		BRANCH,
		STOP
	}

	public static void main(String[] args) {
		Map<StateName, State> stateMap = new HashMap<>();
		
		//TODO Abhi's checks on unused states
		//TODO Mark's counter instead of boolean
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(TaskFactory.count(3, S.TIMEOUT));
		tasks.add(TaskFactory.count(3, S.WALL)); //TODO order based priority
		
		stateMap.put(S.DRIVE, StateFactory.drive(tasks));

		stateMap.put(S.WALL, StateFactory.print(S.STOP, "wall!"));
		stateMap.put(S.TIMEOUT, StateFactory.print(S.STOP, "timeout"));
		//TODO ordering of next state for priority, possibly with orderedmap or just a list
		
//		stateMap.put(S.PRINT_TWICE, StateFactory.printTwice(S.BRANCH, "print twice state"));
//		stateMap.put(S.BRANCH, StateFactory.branch(S.STOP, S.PRINT_TWICE));
		stateMap.put(S.STOP, StateFactory.stop());
		
		StateMachine sm = new StateMachine(stateMap, S.DRIVE);

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
