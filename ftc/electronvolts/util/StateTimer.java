package ftc.electronvolts.util;

/**
 * Created by vandejd1 on 3/1/16.
 * FTC Team EV 7393
 */
public class StateTimer {
    private long endTime;

    public void init(long durationMillis) {
        this.endTime = durationMillis + System.currentTimeMillis();
    }

    public boolean isDone() {
        return System.currentTimeMillis() >= endTime;
    }
}
