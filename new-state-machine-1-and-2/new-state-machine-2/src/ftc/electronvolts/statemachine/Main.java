package ftc.electronvolts.statemachine;

import java.util.Map;
import java.util.HashMap;

public class Main {
	enum S implements StateName {
		PRINT, BRANCH, STOP //, NOPE
	}

	public static void main(String[] args) {
		// START -> print -> branch  no-> stop
		//            ^        |yes
		//            \--------/
		Map<StateName, State> stateMap = new HashMap<>();

		stateMap.put(S.PRINT, StateFactory.printTwice(S.BRANCH, "askdljfhalskdjfh"));
		stateMap.put(S.BRANCH, StateFactory.branch(S.PRINT, S.STOP));
		stateMap.put(S.STOP, StateFactory.stop());
//		stateMap.put(S.NOPE, null);
		
		StateMachine stateMachine = new StateMachine(stateMap, S.PRINT, S.values());

		while (true) {
			stateMachine.run();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
