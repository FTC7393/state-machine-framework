package ftc.electronvolts.statemachine;

public abstract class BasicAbstractState implements State {
	int cyclesSinceInit = 0;

	@Override
	public StateName act() {
		if (cyclesSinceInit == 0) {
			init();
		}
		if (isDone()) {
			cyclesSinceInit = 0;
			return getNextStateName();
		}
		cyclesSinceInit++;
		return null;
	}

	public abstract void init();

	public abstract boolean isDone();

	public abstract StateName getNextStateName();
}