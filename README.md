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

# Contact
Email team members of the project with any questions or comments! Please email [nikita.wootten@gmail.com](nikita.wootten@gmail.com) or submit issues to the issue tracker. You can also reach out to us on twitter at [@electronVoltFTC](https://twitter.com/electronVoltFTC).

We are constantly adding features from our project, so expect frequent updates!
