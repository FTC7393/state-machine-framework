package ftc.electronvolts.statemachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ftc.electronvolts.statemachine.State;
import ftc.electronvolts.statemachine.StateName;

public class StateFactory {

	
	public static State task(Task task) {
		List<Behavior> behaviors = new ArrayList<>();
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(task);
		return behaviors(behaviors, tasks);
	}	
	
	public static State behavior(Behavior behavior, Task task) {
		List<Behavior> behaviors = new ArrayList<Behavior>();
		behaviors.add(behavior);
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(task);
		return behaviors(behaviors, tasks);
	}
	
	public static State behavior(Behavior behavior, List<Task> tasks) {
		List<Behavior> behaviors = new ArrayList<Behavior>();
		behaviors.add(behavior);
		return behaviors(behaviors, tasks);
	}
	
	public static State behaviors(List<Behavior> behaviors, Task task) {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(task);
		return behaviors(behaviors, tasks);
	}

	public static State behaviors(final List<Behavior> behaviors, final List<Task> tasks) {
		return new State() {
			private int cyclesSinceInit = 0;

			@Override
			public StateName act() {
				if (cyclesSinceInit == 0) {
					for (Behavior behavior : behaviors) {
						behavior.init();
					}					
					for (Task task : tasks) {
						task.init();
					}
				}
				for (Task task : tasks) {
					if (task.isDone()) {
						for (Behavior behavior : behaviors) {
							behavior.dispose();
						}
						cyclesSinceInit = 0;
						return task.getNext();
					}
				}
				for (Behavior behavior : behaviors) {
					behavior.run();
				}
				cyclesSinceInit++;
				return null;
			}
		};
	}



	public static State branch(final StateName yesState, final StateName noState, final boolean condition) {
		return task(TaskFactory.branch(yesState, noState, condition));
	}
	
	public static State branch(final StateName yesState, final StateName noState, final Boolean condition) {
		return task(TaskFactory.branch(noState, noState, noState, condition));
	}
	
	public static State branch(final StateName yesState, final StateName noState, final boolean condition, 
			final StateName nullState, final Boolean resultReciever) {
		return task(TaskFactory.branch(noState, noState, nullState, resultReciever));
	}
	
	public static State count(int goal, StateName nextStateName) {
		return task(TaskFactory.count(goal, nextStateName));
	}

	public static State stop() {
		return task(TaskFactory.stop());
	}
}
