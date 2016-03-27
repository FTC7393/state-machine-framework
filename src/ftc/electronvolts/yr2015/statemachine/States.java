package ftc.electronvolts.yr2015.statemachine;

import com.google.common.collect.ImmutableList;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.GyroSensor;

import java.util.List;

import ftc.electronvolts.yr2015.util.DoubleLineSensor;
import ftc.electronvolts.yr2015.util.FourMotorRobot;
import ftc.electronvolts.yr2015.util.Hardware;
import ftc.electronvolts.yr2015.util.InputExtractor;
import ftc.electronvolts.yr2015.util.MecanumControl;
import ftc.electronvolts.yr2015.util.MecanumRobot;
import ftc.electronvolts.yr2015.util.RedBlueSensor;
import ftc.electronvolts.yr2015.util.ServoCommand;
import ftc.electronvolts.yr2015.util.ServoControl;
import ftc.electronvolts.yr2015.util.TeamColor;
import ftc.electronvolts.yr2015.util.vision.ImageProcessingReceiver;

/**
 * Created by vandejd1 on 10/24/15.
 * FTC Team EV 7393
 */
public class States {
    public static State stop(final FourMotorRobot robot){
        return new BasicAbstractState() {
            @Override
            void init() {
                robot.stopMotors();
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

    public static AbstractState empty(List<Transition> transitions){
        return new AbstractState(transitions) {
            @Override
            void init() {}

            @Override
            void run() {}

            @Override
            void dispose() {}
        };
    }

    public static BasicAbstractState teamColorSwitch(final TeamColor teamColor, final StateName redStateName, final StateName blueStateName){
        return new BasicAbstractState() {
            @Override
            void init() {}

            @Override
            boolean isDone() {
                return true;
            }

            @Override
            StateName getNextStateName() {
                if(teamColor == TeamColor.RED){
                    return redStateName;
                } else {
                    return blueStateName;
                }
            }
        };
    }

    public static BasicAbstractState basicEmpty(final StateName nextStateName){
        return new BasicAbstractState() {
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

    public static BasicAbstractState telemetry(final String message, final double value){
        return new BasicAbstractState() {
            @Override
            void init() {}

            @Override
            boolean isDone() {
                Hardware.getInstance().getTelem().addData(message, value);
                return false;
            }

            @Override
            StateName getNextStateName() {
                return null;
            }
        };
    }
    public static BasicAbstractState telemetry(final String message1, final InputExtractor<Double> input1, final String message2, final InputExtractor<Double> input2){
        return new BasicAbstractState() {
            @Override
            void init() {}

            @Override
            boolean isDone() {
                Hardware.getInstance().getTelem().addData(message1, input1.getValue());
                Hardware.getInstance().getTelem().addData(message2, input2.getValue());
                return false;
            }

            @Override
            StateName getNextStateName() {
                return null;
            }
        };
    }




    public static AbstractState telemetry(List<Transition> transitions, final String message, final double value){
        return new AbstractState(transitions) {
            @Override
            void init() {}

            @Override
            void run() {
                Hardware.getInstance().getTelem().addData(message, value);
            }

            @Override
            void dispose() {}
        };
    }

    public static AbstractState telemetry(List<Transition> transitions, final String message, final InputExtractor<Double> input, final String message2, final InputExtractor<Double> input2){
        return new AbstractState(transitions) {
            @Override
            void init() {}

            @Override
            void run() {
                Hardware.getInstance().getTelem().addData(message, input.getValue());
                Hardware.getInstance().getTelem().addData(message2, input2.getValue());
            }

            @Override
            void dispose() {}
        };
    }

    public static BasicAbstractState servoInit(final StateName nextStateName){
        return new BasicAbstractState() {
            @Override
            void init() {}

            @Override
            boolean isDone() {
                return Hardware.getInstance().areServosDone();
            }

            @Override
            StateName getNextStateName() {
                return nextStateName;
            }
        };
    }

    public static BasicAbstractState calibrateGyro(final GyroSensor gyro, final StateName nextStateName){
        gyro.calibrate();
        return new BasicAbstractState() {
            @Override
            void init() {}

            @Override
            boolean isDone() {
                return !gyro.isCalibrating();
            }

            @Override
            StateName getNextStateName() {
                return nextStateName;
            }
        };
    }

    public static BasicAbstractState calibrateLineSensor(final DoubleLineSensor doubleLineSensor, final StateName nextStateName){
        doubleLineSensor.calibrate();
        return new BasicAbstractState() {
            @Override
            void init() {}

            @Override
            boolean isDone() {
                return !doubleLineSensor.isCalibrating();
            }

            @Override
            StateName getNextStateName() {
                return nextStateName;
            }
        };
    }

    public static BasicAbstractState calibrateRBSensor(final RedBlueSensor redBlueSensor, final StateName nextStateName){
        return new BasicAbstractState() {

            private int counter = 0;

            @Override
            void init() {
                counter = 0;
            }

            @Override
            boolean isDone() {
                redBlueSensor.measure();
                counter++;
                return counter >= 50;
            }

            @Override
            StateName getNextStateName() {
                return nextStateName;
            }
        };
    }

    public static BasicAbstractState runThread(final Thread thread, final StateName nextStateName){
        return new BasicAbstractState() {
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

    public static BasicAbstractState servoTurn(final ServoCommand servoCommand, final boolean waitForDone, final StateName nextStateName){
        final ServoControl servoControl = servoCommand.getServo();
        return new BasicAbstractState() {

            @Override
            void init() {
                servoControl.go(servoCommand);
            }

            @Override
            boolean isDone() {
                return !waitForDone || servoControl.isDone();
            }

            @Override
            StateName getNextStateName() {
                return nextStateName;
            }
        };
    }

    public static DetectColorState detectColor(RedBlueSensor redBlueSensor, StateName redState, StateName blueState, StateName unknownState){
        return new DetectColorState(redBlueSensor, redState, blueState, unknownState);
    }

    public static MecanumDriveState mecanumDrive(List<Transition> transitions, MecanumControl mecanumControl, double velocity, double direction, double orientation, double maxAngularSpeed){
        return new MecanumDriveState(transitions, mecanumControl, velocity, direction, orientation, maxAngularSpeed);
    }

    public static MecanumDriveState mecanumDrive(List<Transition> transitions, MecanumControl mecanumControl, double velocity, double direction, double orientation){
        return new MecanumDriveState(transitions, mecanumControl, velocity, direction, orientation);
    }

    public static MecanumLineFollowState mecanumLineFollow(List<Transition> transitions, StateName lostLineState, MecanumControl mecanumControl, double velocity, MecanumControl.LineFollowDirection lineFollowDirection){
        return new MecanumLineFollowState(transitions, lostLineState, mecanumControl, velocity, lineFollowDirection);
    }

    public static RedBlueConditionalState redBlueConditional(StateName redState, StateName blueState, StateName inconclusiveState, StateName timeoutState, ImageProcessingReceiver receiver, long timeoutMillis){
        return new RedBlueConditionalState(redState, blueState, inconclusiveState, timeoutState, receiver, timeoutMillis);
    }
}









