package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * A proportional controller to use for controlling motors and other outputs
 */
public class ProportionalController implements ControlLoop {
    private final double gain, innerDeadzone, outerDeadzone, minOutput,
            maxOutput;

    private static void checkParameters(double gain, double innerDeadzone, double minOutput, double maxOutput) {
        if (gain < 0) {
            throw new IllegalArgumentException("Illegal gain constant \"" + gain + "\". constants cannot be negative.");
        }
        if (maxOutput < 0) {
            throw new IllegalArgumentException("Illegal maxOutput constant \"" + maxOutput + "\". constants cannot be negative.");
        }
        if (innerDeadzone < 0) {
            throw new IllegalArgumentException("Illegal deadzone constant \"" + innerDeadzone + "\". constants cannot be negative.");
        }
        if (minOutput < 0) {
            throw new IllegalArgumentException("Illegal minOutput constant \"" + minOutput + "\". constants cannot be negative.");
        }
        if (minOutput > maxOutput) {
            throw new IllegalArgumentException("minOutput (" + minOutput + ") cannot be grater than maxOutput (" + maxOutput + ")");
        }
    }

    public ProportionalController(double gain, double innerDeadzone, double minOutput, double maxOutput) {
        checkParameters(gain, innerDeadzone, minOutput, maxOutput);

        this.gain = gain;
        this.maxOutput = maxOutput;
        this.innerDeadzone = innerDeadzone;
        this.outerDeadzone = -1;
        this.minOutput = minOutput;
    }

    public ProportionalController(double gain, double innerDeadzone, double outerDeadzone, double minOutput, double maxOutput) {
        checkParameters(gain, innerDeadzone, minOutput, maxOutput);

        if (outerDeadzone < 0) {
            throw new IllegalArgumentException("Illegal outerDeadzone constant \"" + outerDeadzone + "\". constants cannot be negative.");
        }

        if (innerDeadzone > outerDeadzone) {
            throw new IllegalArgumentException("innerDeadzone (" + innerDeadzone + ") cannot be grater than outerDeadzone (" + outerDeadzone + ")");
        }

        this.gain = gain;
        this.maxOutput = maxOutput;
        this.innerDeadzone = innerDeadzone;
        this.outerDeadzone = outerDeadzone;
        this.minOutput = minOutput;
    }

    @Override
    public double computeCorrection(double setPoint, double input) {
        double error = setPoint - input;
        double correction = gain * error;

        if (Math.abs(error) <= innerDeadzone) {
            //return 0 if the error is in the deadzone
            return 0;
        } else if (outerDeadzone > 0 && Math.abs(error) >= outerDeadzone) {
            //return maxOutput if the error is in the outer deadzone
            return Math.signum(error) * maxOutput;
        } else if (Math.abs(correction) < minOutput) {
            //return the minimum if it is below
            return Math.signum(error) * minOutput;
        } else if (Math.abs(correction) > maxOutput) {
            //cap the correction at +/- maxOutput
            return Math.signum(error) * maxOutput;
        } else {
            return correction;
        }
    }

    @Override
    public void initialize() {
        //not needed for a proportional controller since there are no persistent state variables
    }

}
