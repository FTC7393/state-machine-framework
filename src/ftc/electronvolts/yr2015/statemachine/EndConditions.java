package ftc.electronvolts.yr2015.statemachine;

import java.util.List;

/**
 * Created by vandejd1 on 10/21/15.
 * FTC Team EV 7393
 */
public class EndConditions {

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
