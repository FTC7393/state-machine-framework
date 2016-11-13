package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * A PID controller to use for controlling motors
 */
public class PIDController implements ControlLoop {
    private final double pGain, iGain, dGain;
    private final double maxOutput;
    private double iTerm = 0;
    private double input = 0, lastInput = 0;
    private long lastTime = -1;

    /**
     * create a new PID controller
     *
     * @param pGain p constant
     * @param iGain i constant
     * @param dGain d constant
     * @param maxOutput the max value of the output and iTerm
     */
    public PIDController(double pGain, double iGain, double dGain, double maxOutput) {
        this.pGain = pGain;
        this.iGain = iGain;
        this.dGain = dGain;
        this.maxOutput = maxOutput;
    }

    /**
     * @param setPoint the target value
     * @param input the actual value
     * @return the output of the PID
     */
    public double computeCorrection(double setPoint, double input) {
        long now = System.currentTimeMillis();
        double output = 0;
        if (lastTime == -1) {
            lastTime = now;
        } else {
            double timeChange = now - lastTime;
            if (timeChange > 0) {
                this.input = input;

                // Compute all the working error variables
                double error = setPoint - input;
                iTerm += iGain * error * timeChange;
                iTerm = Utility.mirrorLimit(iTerm, maxOutput);
                
                // compute dInput instead of dError to avoid spikes
                double dInput = (input - lastInput) / timeChange;

                // Compute PID Output
                output = pGain * error + iTerm - dGain * dInput;
                output = Utility.mirrorLimit(output, maxOutput);

                // Remember some variables for next time
                lastInput = input;
                lastTime = now;
            }
        }
        return output;
    }

    public void initialize() {
        lastInput = input;
        iTerm = 0;
    }
}
