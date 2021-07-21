package ftc.electronvolts.statemachine;

public interface Task {
	public void init();
	public boolean isDone();
	public StateName getNext();
}
