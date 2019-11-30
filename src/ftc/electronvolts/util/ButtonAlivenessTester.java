package ftc.electronvolts.util;

public class ButtonAlivenessTester implements AlivenessTester {

    private final DigitalInputEdgeDetector aOrB;
    private final DigitalInputEdgeDetector start;
    private StateTimer timer = new StateTimer();

    public ButtonAlivenessTester(DigitalInputEdgeDetector aOrB, DigitalInputEdgeDetector start) {
        this.aOrB = aOrB;
        this.start = start;
        timer.init(0L);
    }

    @Override
    public boolean isAlive() {
        if(aOrB.isPressed() && start.isPressed()) {
            timer.init(500L);
            return false;
        } else {
            return timer.isDone();
        }
    }
}