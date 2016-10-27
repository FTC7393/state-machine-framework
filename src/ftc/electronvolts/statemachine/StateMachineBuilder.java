package ftc.electronvolts.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ftc.electronvolts.util.MatchTimer;
import ftc.electronvolts.util.ResultReceiver;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * The state machine builder simplifies the creation of the state machine. The
 * builder requires an enum with values for each state. See the README for an
 * example of how to use it.
 *
 * To write your own StateMachine builder, make a class that extends this one
 * and add your own convenience methods such as addDrive or addServoTurn. It
 * will inherit all these methods as well, so that when you use your class, you
 * will have access to all these methods and your own in one place.
 */
public class StateMachineBuilder {
    // the map the links a state's name to the state
    private Map<StateName, State> stateMap = new HashMap<>();
    private final StateName firstStateName;

    /**
     * A constructor for the builder. The State machine must have a state to
     * start with
     *
     * @param firstStateName the first state to be executed in order
     */
    public StateMachineBuilder(StateName firstStateName) {
        this.firstStateName = firstStateName;
    }

    /**
     * Create a new transition
     *
     * @param endCondition the end condition for the next state
     * @param nextStateName the name of the enum for the next state
     * @return a list containing the one transition
     */
    public List<Transition> ts(EndCondition endCondition, StateName nextStateName) {
        return toList(new Transition(endCondition, nextStateName));
    }

    private <T> List<T> toList(T t) {
        List<T> list = new ArrayList<>(1);
        list.add(t);
        return list;
    }

    /**
     * Create a new transition with a timed end condition
     *
     * @param durationMillis the amount of time to wait before advancing to the
     *            next state
     * @param nextStateName the enum value associated with the next state
     * @return a list containing the one transition
     */
    public List<Transition> ts(long durationMillis, StateName nextStateName) {
        return ts(EndConditions.timed(durationMillis), nextStateName);
    }

    /**
     * @see EndConditions
     */
    public EndCondition timed(long durationMillis) {
        return EndConditions.timed(durationMillis);
    }

    /**
     * Add a state to the state machine
     *
     * @param state the state to be added
     */
    public void add(State state) {
        stateMap.put(state.getName(), state);
    }

    /**
     * Add an empty state that waits a certain duration
     *
     * @param stateName the enum value to be associated with the wait state
     * @param durationMillis the length of time to wait in millis
     * @param nextStateName the name of the next state
     */
    public void addWait(StateName stateName, long durationMillis, StateName nextStateName) {
        add(States.empty(stateName, ts(EndConditions.timed(durationMillis),
                nextStateName)));
    }

    /**
     * Add an empty state that waits until a certain amount of time has passed
     * since the beginning of the match
     *
     * @param stateName the enum value to be associated with the wait state
     * @param matchTimer a reference to the match timer object
     * @param durationMillis how many millis from the match start to wait
     * @param nextStateName the name of the next state
     * @see MatchTimer
     */
    public void addWait(StateName stateName, MatchTimer matchTimer, long durationMillis, StateName nextStateName) {
        add(States.empty(stateName, ts(EndConditions.matchTimed(matchTimer,
                durationMillis), nextStateName)));
    }

    /**
     * @param stateName the enum value to be associated with the wait state
     * @param condition the boolean to decide which branch to go to
     * @param trueStateName the state to go to if the condition is true
     * @param falseStateName the state to go to if the condition is false
     */
    public void addBranch(StateName stateName, boolean condition, StateName trueStateName, StateName falseStateName) {
        add(States.branch(stateName, condition, trueStateName, falseStateName));
    }

    /**
     * @param stateName the enum value to be associated with the wait state
     * @param condition the boolean to decide which branch to go to
     * @param trueStateName the state to go to if the condition is true
     * @param falseStateName the state to go to if the condition is false
     * @param nullStateName the state to go to if the condition is null
     */
    public void addBranch(StateName stateName, Boolean condition, StateName trueStateName, StateName falseStateName, StateName nullStateName) {
        add(States.branch(stateName, condition, trueStateName, falseStateName,
                nullStateName));
    }

    /**
     * @param stateName the enum value to be associated with the wait state
     * @param receiver the receiver that decides which branch to go to
     * @param trueStateName the state to go to if the receiver returns true
     * @param falseStateName the state to go to if the receiver returns false
     * @param nullStateName the state to go to if the receiver returns null
     */
    public void addBranch(StateName stateName, ResultReceiver<Boolean> receiver, StateName trueStateName, StateName falseStateName, StateName nullStateName) {
        add(States.branch(stateName, receiver, trueStateName, falseStateName,
                nullStateName));
    }

    /**
     * Adds a stop state to the stateMap
     *
     * @param stateName name of the stop state
     */
    public void addStop(StateName stateName) {
        add(States.stop(stateName));
    }

    /**
     * A state used to run a thread. Useful for off-loading computer intensive
     * tasks such as image processing.
     *
     * @param stateName the name of the state
     * @param thread the thread to be run at the start of the state
     * @param nextStateName the next state to be run immediately
     */
    public void addThread(StateName stateName, Thread thread, StateName nextStateName) {
        add(States.runThread(stateName, thread, nextStateName));
    }

    /**
     * Build the state machine with the added states
     *
     * @return the output state machine
     * @see StateMachine
     */
    public StateMachine build() {
        return new StateMachine(stateMap, firstStateName);
    }

    /**
     * Build the state machine with the added states
     *
     * @param firstStateName the state to start at
     * @return the output state machine
     * @see StateMachine
     */

    public StateMachine build(StateName firstStateName) {
        return new StateMachine(stateMap, firstStateName);
    }
}
