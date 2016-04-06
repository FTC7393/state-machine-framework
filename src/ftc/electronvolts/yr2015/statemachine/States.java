package ftc.electronvolts.yr2015.statemachine;

import java.util.List;

/**
 * A factory method containing several commonly used states.
 */
public class States {
	/**
	 * A state that never returns
	 * @param stateName the name of the state
	 * @return the created state
	 */
    public static State stop(StateName stateName){
        return new BasicAbstractState(stateName) {
            @Override
            void init() {
            }

            @Override
            boolean isDone() {
                return false;
            }

            @Override
            StateName getNextStateName() {
                return null;
            }
        };
    }
	/**
	 * An empty state
	 * @param transitions the transitions to be considered
	 * @return the created AbstractState
	 */
    public static AbstractState empty(StateName stateName, List<Transition> transitions){
        return new AbstractState(stateName, transitions) {
            @Override
            void init() {}

            @Override
            void run() {}

            @Override
            void dispose() {}
        };
    }

    /**
     * An empty state.
     * @param nextStateName the next state to be run
     * @return the created BasicAbstractState
     */
    public static BasicAbstractState basicEmpty(StateName stateName, final StateName nextStateName){
        return new BasicAbstractState(stateName) {
            @Override
            void init() {}

            @Override
            boolean isDone() {
                return true;
            }

            @Override
            StateName getNextStateName() {
                return nextStateName;
            }
        };
    }

    /**
     * A state used to run a thread. Useful for off-loading computer intensive tasks such as image processing.
     * @param thread the thread to be run at the start of the state
     * @param nextStateName the next state to be run on the completion of the end condition
     * @return the created BasicAbstractState
     */
    public static BasicAbstractState runThread(StateName stateName, final Thread thread, final StateName nextStateName){
        return new BasicAbstractState(stateName) {
            @Override
            void init() {
                thread.start();
            }

            @Override
            boolean isDone() {
                return true;
            }

            @Override
            StateName getNextStateName() {
                return nextStateName;
            }
        };
    }
}









