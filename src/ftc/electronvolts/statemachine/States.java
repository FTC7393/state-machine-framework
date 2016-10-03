package ftc.electronvolts.statemachine;

import java.util.List;

import ftc.electronvolts.util.ResultReceiver;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * A factory class containing methods that return several useful states.
 *
 * To write your own State factory, make a class that extends this one and add your own
 * methods. It will inherit all these methods as well, so that when you use your class, you will
 * have access to all these methods and your own in one place.
 */
public class States {
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
     * @param stateName   the name of the state
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
     * @param stateName     the name of the state
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
     * A state used to run a thread. Useful for off-loading computer intensive tasks such as image processing.
     *
     * @param stateName     the name of the state
     * @param thread        the thread to be run at the start of the state
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
     * can be used for doing different things depending on which side of the field you are on
     *
     * @param stateName      the name of the state
     * @param condition      the boolean to decide which branch to go to
     * @param trueStateName  the state to go to if the condition is true
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
     * can be used for doing different things depending on which side of the field you are on
     *
     * @param stateName      the name of the state
     * @param condition      the boolean to decide which branch to go to
     * @param trueStateName  the state to go to if the condition is true
     * @param falseStateName the state to go to if the condition is false
     * @param nullStateName  the state to go to if the condition is null
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
     * This is used to do branching and decision trees in the state machine using a result
     * acquired while it is running, and to communicate between threads
     *
     * @param stateName      the name of the state
     * @param receiver       the receiver that decides which branch to go to
     * @param trueStateName  the state to go to if the receiver returns true
     * @param falseStateName the state to go to if the receiver returns false
     * @param nullStateName  the state to go to if the receiver returns null
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









