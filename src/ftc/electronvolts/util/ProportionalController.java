package ftc.electronvolts.util;

public class ProportionalController implements ControlLoop {
    private final double gain, maxOutput, deadzone, minOutput;

    public ProportionalController(double gain, double deadzone, double minOutput, double maxOutput) {
        if (gain < 0) {
            throw new IllegalArgumentException("Illegal gain constant \"" + gain + "\". constants cannot be negative.");
        }
        if (maxOutput < 0) {
            throw new IllegalArgumentException("Illegal maxOutput constant \"" + maxOutput + "\". constants cannot be negative.");
        }
        if (deadzone < 0) {
            throw new IllegalArgumentException("Illegal deadzone constant \"" + deadzone + "\". constants cannot be negative.");
        }
        if (minOutput < 0) {
            throw new IllegalArgumentException("Illegal minOutput constant \"" + minOutput + "\". constants cannot be negative.");
        }
        if (minOutput > maxOutput) {
            throw new IllegalArgumentException("minOutput (" + minOutput + ") cannot be grater than maxOutput (" + maxOutput + ")");
        }
        if (deadzone > minOutput) {
            throw new IllegalArgumentException("deadzone (" + deadzone + ") cannot be grater than minOutput (" + minOutput + ")");
        }

        this.gain = gain;
        this.maxOutput = maxOutput;
        this.deadzone = deadzone;
        this.minOutput = minOutput;
    }

    @Override
    public double computeCorrection(double setPoint, double input) {
        double correction = gain * (setPoint - input);

        if (Math.abs(correction) <= deadzone) {
            //return 0 if the error is in the deadzone
            return 0;
        } else if (Math.abs(correction) < minOutput) {
            //return the minimum if it is below
            return Math.signum(correction) * minOutput;
        } else if (Math.abs(correction) > maxOutput) {
            //cap the correction at +/- maxOutput
            return Math.signum(correction) * maxOutput;
        } else {
            return correction;
        }
    }

    @Override
    public void initialize() {
        //not needed for a proportional controller since there are no persistent state variables
    }

}
