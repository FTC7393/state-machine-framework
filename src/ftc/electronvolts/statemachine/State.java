package ftc.electronvolts.statemachine;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * This is the core of the state machine framework. It is the interface that
 * defines the required behavior of all the classes that call themselves a
 * "State". This way anything that receives a State object knows exactly what
 * methods that object has, regardless of the state's internal structure or
 * function.
 */
public interface State {
    /**
     * Act is run in every single loop
     *
     * @return Returns the name of the next state. Returns null to stay
     *         in the current state.
     */
    StateName act();
}