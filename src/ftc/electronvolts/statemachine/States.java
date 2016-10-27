package ftc.electronvolts.statemachine;

import java.util.List;
import java.util.Map;

import ftc.electronvolts.util.ResultReceiver;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * A factory class containing methods that return several useful states.
 *
 * To write your own State factory, make a class that extends this one and add
 * your own methods. It will inherit all these methods as well, so that when you
 * use your class, you will have access to all these methods and your own in one
 * place.
 */
public class States {
    /**
     * Creates a state machine inside a state of another state machine
     * "Yo, dawg, we heard you like states so we put a state machine inside a
     * state of another state machine so your state can act while your state
     * machine acts, dawg."
     * 
     * @param stateName the name of the state that contains the sub-state
     *            machine
     * @param stateMachineBuilder the builder to add the sub-states to
     * @param subStateToState map that links the sub states to the states in the
     *            main state machine
     * @return the created State
     */
    public static State subStates(StateName stateName, final StateMachineBuilder stateMachineBuilder, final Map<StateName, StateName> subStateToState) {
        StateName firstState = stateMachineBuilder.build()
                .getCurrentStateName();
        for (Map.Entry<StateName, StateName> entry : subStateToState.entrySet()) {
            StateName subState = entry.getKey();
            stateMachineBuilder.add(basicEmpty(subState, firstState));
        }
        final StateMachine stateMachine = stateMachineBuilder.build();
        return new BasicAbstractState(stateName) {
            private StateName nextStateName;

            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                stateMachine.act();
                for (Map.Entry<StateName, StateName> entry : subStateToState
                        .entrySet()) {
                    // if the current state is one of the ending sub-states
                    if (stateMachine.getCurrentStateName() == entry.getKey()) {
                        // go to the corresponding super-state
                        nextStateName = entry.getValue();
                        return true;
                    }
                }
                return false;
            }

            @Override
            public StateName getNextStateName() {
                return nextStateName;
            }
        };
    }

    /**
     * A state that never returns
     *
     * @param stateName the name of the state
     * @return the created state
     */
    public static State stop(StateName stateName) {
        return new BasicAbstractState(stateName) {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public StateName getNextStateName() {
                return null;
            }
        };
    }

    /**
     * An empty state
     *
     * @param stateName the name of the state
     * @param transitions the transitions to be considered
     * @return the created State
     */
    public static State empty(StateName stateName, List<Transition> transitions) {
        return new AbstractState(stateName, transitions) {
            @Override
            public void init() {
            }

            @Override
            public void run() {
            }

            @Override
            public void dispose() {
            }
        };
    }

    /**
     * An empty state.
     *
     * @param stateName the name of the state
     * @param nextStateName the next state to be run
     * @return the created State
     */
    public static State basicEmpty(StateName stateName, final StateName nextStateName) {
        return new BasicAbstractState(stateName) {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public StateName getNextStateName() {
                return nextStateName;
            }
        };
    }

    /**
     * A state used to run a thread. Useful for off-loading computer intensive
     * tasks such as image processing.
     *
     * @param stateName the name of the state
     * @param thread the thread to be run at the start of the state
     * @param nextStateName the next state to be run immediately
     * @return the created State
     */
    public static State runThread(StateName stateName, final Thread thread, final StateName nextStateName) {
        return new BasicAbstractState(stateName) {
            @Override
            public void init() {
                thread.start();
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public StateName getNextStateName() {
                return nextStateName;
            }
        };
    }

    /**
     * This is used to do branching and decision trees in the state machine
     * can be used for doing different things depending on which side of the
     * field you are on
     *
     * @param stateName the name of the state
     * @param condition the boolean to decide which branch to go to
     * @param trueStateName the state to go to if the condition is true
     * @param falseStateName the state to go to if the condition is false
     * @return the created State
     */
    public static State branch(StateName stateName, final boolean condition, final StateName trueStateName, final StateName falseStateName) {
        return new BasicAbstractState(stateName) {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public StateName getNextStateName() {
                if (condition) {
                    return trueStateName;
                } else {
                    return falseStateName;
                }
            }
        };
    }

    /**
     * This is used to do branching and decision trees in the state machine
     * can be used for doing different things depending on which side of the
     * field you are on
     *
     * @param stateName the name of the state
     * @param condition the boolean to decide which branch to go to
     * @param trueStateName the state to go to if the condition is true
     * @param falseStateName the state to go to if the condition is false
     * @param nullStateName the state to go to if the condition is null
     * @return the created State
     */
    public static State branch(StateName stateName, final Boolean condition, final StateName trueStateName, final StateName falseStateName, final StateName nullStateName) {
        return new BasicAbstractState(stateName) {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public StateName getNextStateName() {
                if (condition == null) {
                    return nullStateName;
                } else if (condition) {
                    return trueStateName;
                } else {
                    return falseStateName;
                }
            }
        };
    }

    /**
     * This is used to do branching and decision trees in the state machine
     * using a result
     * acquired while it is running, and to communicate between threads
     *
     * @param stateName the name of the state
     * @param receiver the receiver that decides which branch to go to
     * @param trueStateName the state to go to if the receiver returns true
     * @param falseStateName the state to go to if the receiver returns false
     * @param nullStateName the state to go to if the receiver returns null
     * @return the created State
     */
    public static State branch(StateName stateName, final ResultReceiver<Boolean> receiver, final StateName trueStateName, final StateName falseStateName, final StateName nullStateName) {
        return new BasicAbstractState(stateName) {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return receiver.isReady();
            }

            @Override
            public StateName getNextStateName() {
                if (receiver.getValue() == null) {
                    return nullStateName;
                } else if (receiver.getValue()) {
                    return trueStateName;
                } else {
                    return falseStateName;
                }
            }
        };
    }
}
