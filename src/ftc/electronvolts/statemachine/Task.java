package ftc.electronvolts.statemachine;

import ftc.electronvolts.statemachine.StateName;

public interface Task {
	public void init();
	public boolean isDone();
	public StateName getNext();
}
