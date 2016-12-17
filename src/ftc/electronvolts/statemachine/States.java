package ftc.electronvolts.statemachine;

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
     * @param subStateToState map that links the sub states to the states in the main state machine
     * @param stateMachineBuilder the builder to add the sub-states to
     * @return the created State
     */
    public static State subStates(final Map<Enum<?>, Enum<?>> subStateToState, final StateMachineBuilder stateMachineBuilder) {
        for (Map.Entry<Enum<?>, Enum<?>> entry : subStateToState.entrySet()) {
            Enum<?> subState = entry.getKey();
            stateMachineBuilder.addStop(subState);
        }
        final StateMachine stateMachine = stateMachineBuilder.build();
        return new BasicAbstractState() {
            private Enum<?> nextStateName;

            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                stateMachine.act();
                for (Map.Entry<Enum<?>, Enum<?>> entry : subStateToState.entrySet()) {
                    Enum<?> subState = entry.getKey();
                    Enum<?> state = entry.getValue();
                    // if the current state is one of the ending sub-states
                    if (stateMachine.getCurrentStateName() == subState) {
                        // go to the corresponding super-state
                        nextStateName = state;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Enum<?> getNextStateName() {
                return nextStateName;
            }
        };
    }

    /**
     * "Go back from whence you came -- and never return!"
     * 
     * A state that never returns
     *
     * @return the created state
     */
    public static State stop() {
        return new BasicAbstractState() {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Enum<?> getNextStateName() {
                return null;
            }
        };
    }

    /**
     * "An empty-head thinks mischief is fun, but a mindful person relishes wisdom."
     * (Proverbs 2^10-1)
     * 
     * An empty state.
     *
     * @param transitions the transitions to be considered
     * @return the created State
     */
    public static State empty(Map<Enum<?>, EndCondition> transitions) {
        return new AbstractState(transitions) {
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
     * "Stop! The cup is full!" said Tokusan.
     * 
     * "Exactly," said Master Ryutan. "You are like this cup; you are full of
     * ideas. You come and ask for teaching, but your cup is full; I can't put
     * anything in. Before I can teach you, you'll have to empty your cup."
     * 
     * An empty state.
     *
     * @param nextStateName the next state to be run
     * @return the created State
     */
    public static State empty(final Enum<?> nextStateName) {
        return new BasicAbstractState() {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public Enum<?> getNextStateName() {
                return nextStateName;
            }
        };
    }

    /**
     * "She set up a great loom in her palace, and set to weaving a web of
     * threads long and fine. Then she said to us: 'Young men, my suitors
     * now that the great Odysseus has perished, wait, though you are eager
     * to marry me, until I finish this web, so that my weaving will not be
     * useless and wasted.'"
     * ~Penelope, the Odyssey
     * 
     * A state used to run a thread. Useful for off-loading computer intensive
     * tasks such as image processing.
     *
     * @param nextStateName the next state to be run immediately
     * @param thread the thread to be run at the start of the state
     * @return the created State
     */
    public static State runThread(final Enum<?> nextStateName, final Thread thread) {
        return new BasicAbstractState() {
            @Override
            public void init() {
                thread.start();
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public Enum<?> getNextStateName() {
                return nextStateName;
            }
        };
    }

    /**
     * "The power of the Executive Branch is vested in the President of the
     * United States, who also acts as head of state and Commander-in-Chief of
     * the armed forces."
     * 
     * This is used to do branching and decision trees in the state machine
     * can be used for doing different things depending on which side of the
     * field you are on
     *
     * @param trueStateName the state to go to if the condition is true
     * @param falseStateName the state to go to if the condition is false
     * @param condition the boolean to decide which branch to go to
     * @return the created State
     */
    public static State branch(final Enum<?> trueStateName, final Enum<?> falseStateName, final boolean condition) {
        return new BasicAbstractState() {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public Enum<?> getNextStateName() {
                if (condition) {
                    return trueStateName;
                } else {
                    return falseStateName;
                }
            }
        };
    }

    /**
     * "Established by Article I of the Constitution, the Legislative Branch
     * consists of the House of Representatives and the Senate, which together
     * form the United States Congress."
     * 
     * This is used to do branching and decision trees in the state machine
     * can be used for doing different things depending on which side of the
     * field you are on
     *
     * @param trueStateName the state to go to if the condition is true
     * @param falseStateName the state to go to if the condition is false
     * @param nullStateName the state to go to if the condition is null
     * @param condition the boolean to decide which branch to go to
     * @return the created State
     */
    public static State branch(final Enum<?> trueStateName, final Enum<?> falseStateName, final Enum<?> nullStateName, final Boolean condition) {
        return new BasicAbstractState() {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public Enum<?> getNextStateName() {
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
     * "Where the Executive and Legislative branches are elected by the people,
     * members of the Judicial Branch are appointed by the President and
     * confirmed by the Senate."
     * 
     * This is used to do branching and decision trees in the state machine
     * using a result
     * acquired while it is running, and to communicate between threads
     *
     * @param trueStateName the state to go to if the receiver returns true
     * @param falseStateName the state to go to if the receiver returns false
     * @param nullStateName the state to go to if the receiver returns null
     * @param receiver the receiver that decides which branch to go to
     * @return the created State
     */
    public static State branch(final Enum<?> trueStateName, final Enum<?> falseStateName, final Enum<?> nullStateName, final ResultReceiver<Boolean> receiver) {
        return new BasicAbstractState() {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return receiver.isReady();
            }

            @Override
            public Enum<?> getNextStateName() {
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

    /**
     * Count von Count: Five bananas. Six bananas. SEVEN BANANAS!
     * [thunder strikes]
     * Count von Count: Ah, ah, ah. Now, that was silly. Wouldn't you agree, my
     * bats? Ah, ah, ah.
     * 
     * Moves to continueStateName for the first n times it is called, then moves
     * to doneStateName after that
     * 
     * @param continueStateName will be transitioned to first
     * @param doneStateName will be transitioned to after
     * @param n the number of times to return continueStateName
     * @return the created State
     */
    public static State count(final Enum<?> continueStateName, final Enum<?> doneStateName, final int n) {
        return new BasicAbstractState() {
            int i = 0;

            @Override
            public void init() {
                i++;
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public Enum<?> getNextStateName() {
                return i <= n ? continueStateName : doneStateName;
            }
        };
    }
}
