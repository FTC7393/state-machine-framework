package ftc.electronvolts.statemachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

	public static State behaviors(List<Behavior> behaviors, List<Task> tasks) {
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

	public static State stop() {
		return new State() {

			@Override
			public StateName act() {
				System.out.println("stop state");
				return null;
			}
		};

	}

	public static State print(StateName next, String printWhat) {
		return new State() {

			@Override
			public StateName act() {
				System.out.println(printWhat);
				return next;
			}
		};

	}

	//this could be simplified with a behavior, see below
	public static State printTwice(StateName next, String printWhat) {
		return task(new Task() {
			int i = 0;

			@Override
			public void init() {
				i = 0;
			}

			@Override
			public boolean isDone() {
				System.out.println(printWhat);
				i++;
				return i == 2;
			}

			@Override
			public StateName getNext() {
				return next;
			}
		});
	}
	
	public static State printN(String s, int n, StateName next) {
		List<Task> tasks = new ArrayList<>();
		tasks.add(TaskFactory.count(n, next));
		return behavior(BehaviorFactory.print(s), tasks);
	}

	public static State drive(List<Task> tasks) {
		return behavior(BehaviorFactory.drive(), tasks);
	}

	//this is bad -- too cluttered and not general enough
	public static State driveWallTimeout(StateName wall, StateName timeout) {
		final int wallTime = 10;
		final int timeoutTime = 3;

		return task(new Task() {
			int i = 0;

			@Override
			public void init() {
				System.out.println("start your engines");
			}

			@Override
			public boolean isDone() {
				System.out.println("vroom");
				i++;
				return i == wallTime || i == timeoutTime;
			}

			@Override
			public StateName getNext() {
				System.out.println("stop driving");
				if (i == wallTime) {
					return wall;
				} else {
					return timeout;
				}
			}
		});
	}

	public static State branch(StateName yesState, StateName noState) {
		return new State() {
			Scanner in = new Scanner(System.in);

			@Override
			public StateName act() {
				System.out.println("branch (yes/no)");
				String s = in.nextLine();
				System.out.println("You entered string " + s);
				if (s.equals("yes")) {
					return yesState;
				} else {
					return noState;
				}
			}
		};
	}
}
