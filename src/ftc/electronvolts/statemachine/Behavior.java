package ftc.electronvolts.statemachine;

public interface Behavior {
    /**
     * Run once when the state is initialized.
     * This can run tasks such as starting motors.
     */
    public abstract void init();

    /**
     * Run every cycle after init()
     * This can do tasks such as gyro stabilization or line following
     */
    public abstract void run();

    /**
     * Run once when the state is finished before the next state is initialized.
     * This can do any cleaning up, such as stopping any started motors.
     */
    public abstract void dispose();
}
