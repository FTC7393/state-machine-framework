package ftc.electronvolts.statemachine;

import ftc.electronvolts.statemachine.StateName;
import ftc.electronvolts.util.ResultReceiver;

public class TaskFactory {
	public static Task count(final int goal, final StateName next) {
		return new Task() {
			int i;
			@Override
			public void init() {
				i=0;
			}

			@Override
			public boolean isDone() {
				i++;
				return i>goal;
			}

			@Override
			public StateName getNext() {
				return next;
			}
		};
	}
	
    /**
     * "The power of the Executive Branch is vested in the President of the
     * United States, who also acts as head of state and Commander-in-Chief of
     * the armed forces."
     * 
     * This is used to do branching and decision trees in the state machine can
     * be used for doing different things depending on which side of the field
     * you are on
     *
     * @param trueStateName the state to go to if the condition is true
     * @param falseStateName the state to go to if the condition is false
     * @param condition the boolean to decide which branch to go to
     * @return the created State
     */
    public static Task branch(final StateName trueStateName, final StateName falseStateName, final boolean condition) {
        return new Task() {

			@Override
			public void init() {
				
			}

			@Override
			public boolean isDone() {
				return true;
			}

			@Override
			public StateName getNext() {
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
     * This is used to do branching and decision trees in the state machine can
     * be used for doing different things depending on which side of the field
     * you are on
     *
     * @param trueStateName the state to go to if the condition is true
     * @param falseStateName the state to go to if the condition is false
     * @param nullStateName the state to go to if the condition is null
     * @param condition the boolean to decide which branch to go to
     * @return the created State
     */
    public static Task branch(final StateName trueStateName, final StateName falseStateName, final StateName nullStateName, final Boolean condition) {
        return new Task() {



			@Override
			public void init() {
				
			}

			@Override
			public boolean isDone() {
				return true;
			}

			@Override
			public StateName getNext() {
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
     * using a result acquired while it is running, and to communicate between
     * threads
     *
     * @param trueStateName the state to go to if the receiver returns true
     * @param falseStateName the state to go to if the receiver returns false
     * @param nullStateName the state to go to if the receiver returns null
     * @param receiver the receiver that decides which branch to go to
     * @return the created State
     */
    public static Task branch(final StateName trueStateName, final StateName falseStateName, final StateName nullStateName, final ResultReceiver<Boolean> receiver) {
        return new Task() {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return receiver.isReady();
            }

            @Override
            public StateName getNext() {
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
    
    public static Task runThread(final StateName nextStateName, final Thread thread) {
        return new Task() {

			@Override
			public void init() {
                thread.start();
			}

			@Override
			public boolean isDone() {
				return true;
			}

			@Override
			public StateName getNext() {
                return nextStateName;
                
			}
        };
    }
    
    public static Task stop() {
    	return new Task() {

			@Override
			public void init() {		
			}

			@Override
			public boolean isDone() {
				return false;
			}

			@Override
			public StateName getNext() {
				return null;
			}
    		
    	};
    }
    
}
