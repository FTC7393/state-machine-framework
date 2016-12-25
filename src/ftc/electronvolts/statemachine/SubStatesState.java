package ftc.electronvolts.statemachine;

import java.util.Map;

/**
 * This file was made by the electronVolts, FTC team 7393
 */
public class SubStatesState implements State {
    private final Map<StateName, StateName> subStateToState;
    private final StateMachine stateMachine;

    public SubStatesState(final Map<StateName, StateName> subStateToState, final StateMachineBuilder stateMachineBuilder) {
        for (Map.Entry<StateName, StateName> entry : subStateToState.entrySet()) {
            StateName subState = entry.getKey();
            stateMachineBuilder.addStop(subState);
        }
        this.subStateToState = subStateToState;
        stateMachine = stateMachineBuilder.build();
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    @Override
    public StateName act() {
        stateMachine.act();
        for (Map.Entry<StateName, StateName> entry : subStateToState.entrySet()) {
            StateName subState = entry.getKey();
            StateName state = entry.getValue();
            // if the current state is one of the ending sub-states
            if (stateMachine.getCurrentStateName() == subState) {
                // go to the corresponding super-state
                return state;
            }
        }
        return null;
    }
}
