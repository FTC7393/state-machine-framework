package ftc.electronvolts.yr2015.statemachine;

import java.util.List;

/**
 * A set of commonly used end conditions.
 */
public class EndConditions {
	/**
	 * An end condition that finishes after a certain amount of time
	 * @param durationMillis  the amount of time to wait before finishing
	 * @return the end condition
	 */
    public static EndCondition timed(final long durationMillis) {
        return new EndCondition() {
            private long endTime;
            @Override
            public void init() {
                endTime = System.currentTimeMillis() + durationMillis;
            }

            @Override
            public boolean isDone() {
                return System.currentTimeMillis() >= endTime;
            }
        };
    }

    /**
     * An end condition that finishes after a certain amount of time since the start of the match has passed
     * @param matchTimer a reference to the match timer object
     * @param millisFromMatchStart how many millis from the match start to wait
     * @return the end condition
     */
    public static EndCondition matchTimed(final MatchTimer matchTimer, final long millisFromMatchStart) {

        return new EndCondition() {
            @Override
            public void init() {
            }

            @Override
            public boolean isDone() {
                return matchTimer.getElapsedTime() >= millisFromMatchStart;
            }
        };
    }
    
    /**
     * An end condition that returns the opposite of another end condition
     * @param endCondition the end condition to use
     * @return the end condition
     */
    public static EndCondition not(final EndCondition endCondition){
        return new EndCondition() {
            @Override
            public void init() {
                endCondition.init();
            }

            @Override
            public boolean isDone() {
                return !endCondition.isDone();
            }
        };
    }

    /**
     * An end condition that finishes if it has been initialized a certain amount of times
     * @param maxCount the maximum amount of times for the end condition to be initialized
     * @return the end condition
     */
    public static EndCondition count(final int maxCount) {
        return new EndCondition() {
            private int counter = 0;
            @Override
            public void init() {
                counter++;
            }

            @Override
            public boolean isDone() {
                return (counter > maxCount);
            }
        };
    }

    /**
     * An end condition that executes a certain number of loops before finishing
     * @param maxCount The number of loops to allow the execution of
     * @return the end condition
     */
    public static EndCondition loopCount(final int maxCount) {
        return new EndCondition() {
            private int counter;
            @Override
            public void init() {
                counter = 0;
            }

            @Override
            public boolean isDone() {
                return (counter++ >= maxCount);
            }
        };
    }

    /**
     * An end condition that finishes if all of the specified end conditions are true
     * @param endConditionList a list containing all of the end conditions
     * @return the end condition
     */
    public static EndCondition all(final List<EndCondition> endConditionList){
        return new EndCondition() {
            @Override
            public void init() {
                for(EndCondition e : endConditionList){
                    e.init();
                }
            }

            @Override
            public boolean isDone() {
                for(EndCondition e : endConditionList){
                    if(!e.isDone()){
                        return false;
                    }
                }
                return true;
            }
        };
    }

    /**
     * An end condition that finishes if any of the specified end conditions are true
     * @param endConditionList a list containing all of the end conditions
     * @return the end condition
     */
    public static EndCondition any(final List<EndCondition> endConditionList){
        return new EndCondition() {
            @Override
            public void init() {
                for(EndCondition e : endConditionList){
                    e.init();
                }
            }

            @Override
            public boolean isDone() {
                for(EndCondition e : endConditionList){
                    if(e.isDone()){
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * An end condition that never finishes
     * @return the end condition
     */
    public static EndCondition never() {
        return new EndCondition() {
            @Override
            public void init() {

            }

            @Override
            public boolean isDone() {
                return false;
            }
        };
    }
    
    /**
     * An end condition that only allows the state to execute one loop
     * @return the end condition
     */
    public static EndCondition now() {
        return new EndCondition() {
            @Override
            public void init() {

            }

            @Override
            public boolean isDone() {
                return true;
            }
        };
    }
}
