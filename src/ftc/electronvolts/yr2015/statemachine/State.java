package ftc.electronvolts.yr2015.statemachine;

/**
 * Created by vandejd1 on 10/17/15.
 * FTC Team EV 7393
 */
public interface State {
	/**
	 * Act is run every single loop
	 * @return
	 */
    StateName act();
}