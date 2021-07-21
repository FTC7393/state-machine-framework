package ftc.electronvolts.statemachine;

public class TaskFactory {
	public static Task count(int goal, StateName next) {
		return new Task() {
			int i;
			@Override
			public void init() {
				i=0;
			}

			@Override
			public boolean isDone() {
				i++;
				return i==goal;
			}

			@Override
			public StateName getNext() {
				return next;
			}
		};
	}
}
