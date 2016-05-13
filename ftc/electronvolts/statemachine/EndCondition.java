package ftc.electronvolts.statemachine;

/**
 * An EndCondition is used as a condition. If the end condition is true, the next state is run.
 */
public interface EndCondition {
	/**
	 * Run once when the end condition is created
     * Can be used to tell helper classes to initialize
     * Be careful to reset all variables here in case the state is run a second time
	 */
	void init();

	/**
	 * Run every cycle after init()
	 * Used to get values from helper classes to see if the condition is met
	 * @return true if condition is met.
	 */
    boolean isDone();
}
