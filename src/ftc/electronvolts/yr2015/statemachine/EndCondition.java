package ftc.electronvolts.yr2015.statemachine;

/**
 * An EndCondition is used as a condition. If the end condition is true, the next state is run.
 */
public interface EndCondition {
	/**
	 * Run once when the end condition is created
	 */
	void init();

	/**
	 * Run every single loop. Work should be done here.
	 * @return true if condition is met.
	 */
    boolean isDone();
}
