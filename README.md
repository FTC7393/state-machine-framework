# State Machine Framework
The state-machine-framework is a library designed to make autonomous design easier. Although this library is designed for use with the  [FTC](http://www.firstinspires.org/robotics/ftc) [libraries](https://github.com/ftctechnh/ftc_app), it can be easily used in other projects.

Features:
- It is easy to add new functionality.
- It supports a very flexible design, allowing for multiple paths and multiple end conditions for each state.
- States can be assembled using an included builder class for added convenience.
- Extremely quick autonomous development and debugging.

We use the state machine framework because it allows us to make so many changes on the fly.

# Examples
Creating a new state machine using the builder:
```java
// First, create a list of states
enum s implements StateName {
    WAIT,
    STOP
};

// Create a new builder, giving it the name of the first state
StateMachineBuilder b = new StateMachineBuilder(s.WAIT);

// Initialize all of the states
b.addWait(s.WAIT, 1000, s.STOP);
b.addStop(s.STOP);

// Build the state machine
StateMachine stateMachine = b.build();
```
Using the StateMachine you created in a project is extremely simple. Just add this line to the loop method:
```java
stateMachine.act();
```

# Usage
Using the state machine framework in the your project is extremely simple!
- Add the latest jar file (found in _build/_ folder) to the  _FTCRobotController/libs/_ folder.
- Add the file to the gradle build dependencies (inside  _build.gradle_ in the 'dependencies' section):
```
compile files('libs/state-machine-framework-<latest version>.jar')
```
- Rebuild the gradle project.
- If you plan on using vision in the project, please follow the instructions located in README_VISION.md, as additional changes need to be made in order to facilitate robot vision.

# Adding Custom States
First, create a class that extends the States factory class. It will inherit all the methods from States.
```java
public class MyStates extends States {

}
```

You can add methods to it such as a gyro calibration state or a telemetry state:
```java
public class MyStates extends States {
    public static State calibrateGyro(StateName stateName, final GyroSensor gyro, final StateName nextStateName){
        gyro.calibrate();
        return new BasicAbstractState(stateName) {
            @Override
            public void init() {}

            @Override
            public boolean isDone() {
                return !gyro.isCalibrating();
            }

            @Override
            public StateName getNextStateName() {
                return nextStateName;
            }
        };
    }
    
    
    public static State telemetry(StateName stateName, List<Transition> transitions, final Telemetry telemetry, final String message, final double value){
        return new AbstractState(stateName, transitions) {
            @Override
            public void init() {}

            @Override
            public void run() {
                telemetry.addData(message, value);
            }

            @Override
            public void dispose() {}
        };
    }
}
```

Then make a class that extends StateMachineBuilder.
```java
public class MyStateMachineBuilder extends StateMachineBuilder {
    public MyStateMachineBuilder(StateName firstStateName) {
        super(firstStateName);
    }
}

```

Then add a convenience method to it that uses your method you made in MyStates.
```java
public class MyStateMachineBuilder extends StateMachineBuilder {
    public MyStateMachineBuilder(StateName firstStateName) {
        super(firstStateName);
    }
    
    public void addCalibrateGyro(StateName stateName, GyroSensor gyro, StateName nextStateName){
        add(MyStates.calibrateGyro(stateName, gyro, nextStateName));
    }
    
    public void addTelem(StateName stateName, Telemetry telemetry, String message, double value, List<Transition> transitions) {
        add(MyStates.telemetry(stateName, transitions, telemetry, message, value));
    }
}

```

You can even pass in objects in the constructor to be used multiple times.
```java
public class MyStateMachineBuilder extends StateMachineBuilder {
    private final Telemetry telemetry;
    
    public MyStateMachineBuilder(StateName firstStateName, Telemetry telemetry) {
        super(firstStateName);
        this.telemetry = telemetry;
    }
    
    public void addCalibrateGyro(StateName stateName, GyroSensor gyro, StateName nextStateName){
        add(MyStates.calibrateGyro(stateName, gyro, nextStateName));
    }
    
    public void addTelem(StateName stateName, String message, double value, List<Transition> transitions) {
        add(MyStates.telemetry(stateName, transitions, telemetry, message, value));
    }
}

```

# Custom EndConditions
Creating your own EndConditions is much simpler than adding states. All you have to do is make a class that extends EndConditions.
```java
public class EVEndConditions extends EndConditions {

}
```

And add some methods to it.
```java
public class EVEndConditions extends EndConditions {

    public static EndCondition gyroCloseTo(final GyroSensor gyro, double targetDegrees, final double toleranceDegrees){
        final Vector3D targetVector = Vector3D.fromPolar2D(1, Angle.fromDegrees(targetDegrees));
        return new EndCondition() {
            @Override
            public void init() {}

            @Override
            public boolean isDone() {
                Vector3D gyroVector = Vector3D.fromPolar2D(1, Angle.fromDegrees(gyro.getHeading()));
                Angle separation = Vector3D.signedAngularSeparation(targetVector, gyroVector);
                return Math.abs(separation.getValueDegrees()) <= toleranceDegrees;
            }
        };
    }

}
```


# Contact
Email team members of the project with any questions or comments! Please email [nikita.wootten@gmail.com](nikita.wootten@gmail.com) or submit issues to the issue tracker. You can also reach out to us on twitter at [@electronVoltFTC](https://twitter.com/electronVoltFTC).

We are constantly adding features from our project, so expect frequent updates!
