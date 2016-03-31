package ftc.electronvolts.yr2015.statemachine;

import java.util.List;

/**
 * Created by vandejd1 on 10/24/15.
 * FTC Team EV 7393
 */
public class States {
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
}









