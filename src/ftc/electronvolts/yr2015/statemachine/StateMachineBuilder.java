package ftc.electronvolts.yr2015.statemachine;

import com.google.common.collect.ImmutableList;
import com.qualcomm.robotcore.hardware.GyroSensor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ftc.electronvolts.yr2015.util.FourMotorRobot;
import ftc.electronvolts.yr2015.util.MatchTimer;
import ftc.electronvolts.yr2015.util.MecanumControl;
import ftc.electronvolts.yr2015.util.ServoCommand;

/**
 * Created by vandejd1 on 3/2/16.
 * FTC Team EV 7393
 */
public class StateMachineBuilder {
    private Map<StateName, State> stateMap = new HashMap<StateName, State>();
    private final FourMotorRobot robot;
    private final MecanumControl mecanumControl;
    private final StateName firstStateName;

    public List<Transition> ts(EndCondition endCondition, StateName nextStateName) {
        return ImmutableList.of(new Transition(endCondition, nextStateName));
    }

    public List<Transition> ts(long durationMillis, StateName nextStateName) {
        return ts(EndConditions.timed(durationMillis), nextStateName);
    }

    public EndCondition timed(long durationMillis){
        return EndConditions.timed(durationMillis);
    }

    public StateMachineBuilder(StateName firstStateName, FourMotorRobot robot, MecanumControl mecanumControl) {
        this.firstStateName = firstStateName;
        this.robot = robot;
        this.mecanumControl = mecanumControl;
    }

    public void add(StateName stateName, State state){
        stateMap.put(stateName, state);
    }

    public void addServoInit(StateName stateName, StateName nextStateName){
        add(stateName, States.servoInit(nextStateName));
    }
    public void addCalibrateGyro(StateName stateName, GyroSensor gyro, StateName nextStateName){
        add(stateName, States.calibrateGyro(gyro, nextStateName));
    }

    public void stop(StateName stateName){
        add(stateName, States.stop(robot));
    }

//    public State getState(StateName stateName){
//        return stateMap.get(stateName);
//    }

    public StateMachine build(){
        return new StateMachine(stateMap, firstStateName);
//        for(Map.Entry<StateName, State> entry: stateMap.entrySet()) {
//            StateName stateName = entry.getKey();
//            State state = entry.getValue();
//        }
    }

    public void addDrive(StateName stateName, List<Transition> transitions, double velocity, double direction, double orientation, double maxAngularSpeed) {
        add(stateName, States.mecanumDrive(transitions, mecanumControl, velocity, direction, orientation, maxAngularSpeed));
    }
    public void addDrive(StateName stateName, List<Transition> transitions, double velocity, double direction, double orientation) {
        addDrive(stateName, transitions, velocity, direction, orientation, MecanumControl.DEFAULT_MAX_ANGULAR_SPEED);
    }

    public void addDrive(StateName stateName, long durationMillis, StateName nextStateName, double velocity, double direction, double orientation, double maxAngularSpeed) {
        addDrive(stateName, ts(timed(durationMillis), nextStateName), velocity, direction, orientation, maxAngularSpeed);
    }

    public void addDrive(StateName stateName, long durationMillis, StateName nextStateName, double velocity, double direction, double orientation) {
        addDrive(stateName, durationMillis, nextStateName, velocity, direction, orientation, MecanumControl.DEFAULT_MAX_ANGULAR_SPEED);
    }

    public void addWait(StateName stateName, long durationMillis, StateName nextStateName) {
        add(stateName, States.empty(ts(EndConditions.timed(durationMillis), nextStateName)));
    }

    public void addWait(StateName stateName, MatchTimer matchTimer, long durationMillis, StateName nextStateName) {
        add(stateName, States.empty(ts(EndConditions.matchTimed(matchTimer, durationMillis), nextStateName)));
    }

    public void addServo(StateName stateName, ServoCommand servoCommand, boolean waitForDone, StateName nextStateName) {
        add(stateName, States.servoTurn(servoCommand, waitForDone, nextStateName));
    }

    public void addTelem(StateName stateName, String message, double value, List<Transition> transitions) {
        add(stateName, States.telemetry(transitions, message, value));
    }

    public void addTelem(StateName stateName, String message, double value) {
        add(stateName, States.telemetry(message, value));
    }
}
