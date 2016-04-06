package ftc.electronvolts.yr2015.statemachine;

/**
 * Created by vandejd1 on 10/17/15.
 * FTC Team EV 7393
 */
public interface State {
    /**
     * Act is run in every single loop
     * @return Returns the name of the next state. Returns null to stay in the current state.
     */
    StateName act();

    /**
     * @return returns this state's name.
     */
    StateName getName();
}