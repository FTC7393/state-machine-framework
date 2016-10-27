package ftc.electronvolts.statemachine;

import java.util.List;

import ftc.electronvolts.util.InputExtractor;
import ftc.electronvolts.util.MatchTimer;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * This is a factory class for the EndCondition interface.
 * It contains methods which return some useful end conditions that can be used
 * not only in the state machine, but also anywhere in your code.
 *
 * To write your own EndCondition factory, make a class that extends this one
 * and add your own methods. It will inherit all these methods as well, so that
 * when you use your class, you will have access to all these methods and your
 * own in one place.
 */
public class EndConditions {
    /**
     * An end condition that finishes after a certain amount of time
     *
     * @param durationMillis the amount of time to wait before finishing
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
     * An end condition that finishes after a certain amount of time since the
     * start of the match has passed
     *
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
     *
     * @param endCondition the end condition to use
     * @return the end condition
     */
    public static EndCondition not(final EndCondition endCondition) {
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
     * An end condition that finishes if it has been initialized a certain
     * amount of times
     *
     * @param maxCount the maximum amount of times for the end condition to be
     *            initialized
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
     *
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
     * An end condition that finishes if all of the specified end conditions are
     * true
     *
     * @param endConditionList a list containing all of the end conditions
     * @return the end condition
     */
    public static EndCondition all(final List<EndCondition> endConditionList) {
        return new EndCondition() {
            @Override
            public void init() {
                for (EndCondition e : endConditionList) {
                    e.init();
                }
            }

            @Override
            public boolean isDone() {
                for (EndCondition e : endConditionList) {
                    if (!e.isDone()) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    /**
     * An end condition that finishes if any of the specified end conditions are
     * true
     *
     * @param endConditionList a list containing all of the end conditions
     * @return the end condition
     */
    public static EndCondition any(final List<EndCondition> endConditionList) {
        return new EndCondition() {
            @Override
            public void init() {
                for (EndCondition e : endConditionList) {
                    e.init();
                }
            }

            @Override
            public boolean isDone() {
                for (EndCondition e : endConditionList) {
                    if (e.isDone()) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * An end condition that never finishes
     *
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
     *
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

    // The following few end conditions use input extractors
    // They may or may not be useful to most people since it would usually be
    // easier to write your
    // own end condition instead of using these

    /**
     * This could be used for an end condition that uses the joystick
     *
     * @param inputExtractor an extracted boolean
     * @return the end condition
     */
    public static EndCondition inputExtractor(final InputExtractor<Boolean> inputExtractor) {
        return new EndCondition() {
            @Override
            public void init() {

            }

            @Override
            public boolean isDone() {
                return inputExtractor.getValue();
            }
        };
    }

    /**
     * compares the values of two input extractors
     *
     * @param inputExtractorA the first input extractor
     * @param inputExtractorB the second input extractor
     * @param inclusive whether or not the values being equal satisfies the
     *            condition
     * @return the end condition
     */
    public static EndCondition aGreaterThanB(final InputExtractor<Double> inputExtractorA, final InputExtractor<Double> inputExtractorB, final boolean inclusive) {
        return new EndCondition() {
            @Override
            public void init() {

            }

            @Override
            public boolean isDone() {
                if (inclusive) {
                    return inputExtractorA.getValue() >= inputExtractorB
                            .getValue();
                } else {
                    return inputExtractorA.getValue() > inputExtractorB
                            .getValue();
                }
            }
        };
    }

    /**
     * @param inputExtractor the input extractor
     * @param target the target value
     * @param inclusive whether or not the values being equal satisfies the
     *            condition
     * @return the end condition
     */
    public static EndCondition valueGreater(final InputExtractor<Double> inputExtractor, final double target, final boolean inclusive) {
        return new EndCondition() {
            @Override
            public void init() {

            }

            @Override
            public boolean isDone() {
                if (inclusive) {
                    return inputExtractor.getValue() >= target;
                } else {
                    return inputExtractor.getValue() > target;
                }
            }
        };
    }

    /**
     * @param inputExtractor the input extractor
     * @param target the target value
     * @param inclusive whether or not the values being equal satisfies the
     *            condition
     * @return the end condition
     */
    public static EndCondition valueLess(final InputExtractor<Double> inputExtractor, final double target, final boolean inclusive) {
        return new EndCondition() {
            @Override
            public void init() {

            }

            @Override
            public boolean isDone() {
                if (inclusive) {
                    return inputExtractor.getValue() <= target;
                } else {
                    return inputExtractor.getValue() < target;
                }
            }
        };
    }

    /**
     * @param inputExtractor the input extractor
     * @param min the lower edge of the target range
     * @param max the upper edge of the target range
     * @param inclusive whether or not the value being equal to the min or max
     *            satisfies the condition
     *            when min = max, this should be true.
     * @return the end condition
     */
    public static EndCondition valueBetween(final InputExtractor<Double> inputExtractor, final double min, final double max, final boolean inclusive) {
        return new EndCondition() {
            @Override
            public void init() {

            }

            @Override
            public boolean isDone() {
                double value = inputExtractor.getValue();
                if (inclusive) {
                    return min <= value && value <= max;
                } else {
                    return min < value && value < max;
                }
            }
        };
    }

    /**
     * @param inputExtractor the input extractor
     * @param target the target value
     * @param tolerance the tolerance +/- the value to be accepted as meeting
     *            the condition
     * @param inclusive whether or not the value being on the edge of the
     *            tolerance satisfies the condition.
     *            when the tolerance is 0, this should be true.
     * @return the end condition
     */
    public static EndCondition valueCloseTo(InputExtractor<Double> inputExtractor, double target, double tolerance, boolean inclusive) {
        return valueBetween(inputExtractor, target - tolerance, target
                + tolerance, inclusive);
    }
}
