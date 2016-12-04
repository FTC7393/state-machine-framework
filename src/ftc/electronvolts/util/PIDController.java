package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * A PID controller to use for controlling motors
 */
public class PIDController implements ControlLoop {
    private final double pGain, iGain, dGain, maxOutput;
    private double iTerm = 0;
    private double input = 0, lastInput = 0;
    private long lastTime = -1;

    /**
     * create a new PID controller
     *
     * @param pGain p constant (cannot be negative)
     * @param iGain i constant (cannot be negative)
     * @param dGain d constant (cannot be negative)
     * @param maxOutput the max value of the output and iTerm (cannot be
     *            negative)
     */
    public PIDController(double pGain, double iGain, double dGain, double maxOutput) {
        if (pGain < 0) {
            throw new IllegalArgumentException("Illegal pGain constant \"" + pGain + "\". PID constants cannot be negative.");
        }
        if (iGain < 0) {
            throw new IllegalArgumentException("Illegal iGain constant \"" + iGain + "\". PID constants cannot be negative.");
        }
        if (dGain < 0) {
            throw new IllegalArgumentException("Illegal dGain constant \"" + dGain + "\". PID constants cannot be negative.");
        }
        if (maxOutput < 0) {
            throw new IllegalArgumentException("Illegal maxOutput constant \"" + maxOutput + "\". PID constants cannot be negative.");
        }

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
    @Override
    public double computeCorrection(double setPoint, double input) {
        long now = System.currentTimeMillis();
        double output = 0;
        if (lastTime < 0) {
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

    /**
     * Reset the PIDController to its initial state by resetting the iTerm and
     * the lastTime
     */
    @Override
    public void initialize() {
        lastInput = input;
        iTerm = 0;
        lastTime = -1;
    }
}
