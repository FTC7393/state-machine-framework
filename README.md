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
public enum s implements StateName {
  BobState, JohnState, JennyState, Stop
};

// Create a new builder, giving it the name of the first state
StateMachineBuilder b = new StateMachineBuilder(s.BobState);

// Initialize all of the states
b.add(s.BobState, States.bobState(), s.JohnState);
b.add(s.JohnState, States.JohnState(), s.JennyState);
b.add(s.JennyState, States.JennyState, s.Stop);
b.addStop(s.Stop);

// Build the state machine
StateMachine stateMachine = b.build();
```
Using a StateMachine in a project is extremely simple. Just add this line to the loop method:
```java
stateMachine.act();
```
