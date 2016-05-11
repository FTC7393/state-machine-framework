package ftc.electronvolts.util;

/**
 * Created by vandejd1 on 12/27/15.
 * FTC Team EV 7393
 */
public class PIDController {
    private double pGain, iGain, dGain;
    private double iTerm = 0;
    private double input = 0, lastInput = 0;
    private long lastTime = -1;
    private double maxOutput;

    public PIDController(double pGain, double iGain, double dGain, double maxOutput) {
        this.pGain = pGain;
        this.iGain = iGain;
        this.dGain = dGain;
        this.maxOutput = maxOutput;
    }

//    public void setPID(double pGain, double iGain, double dGain) {
//        this.pGain = pGain;
//        this.iGain = iGain;
//        this.dGain = dGain;
//    }

    public double computeCorrection(double setPoint, double input){
        long now = System.currentTimeMillis();
        double output = 0;
        if (lastTime == -1){
            lastTime = now;
        } else {
            double timeChange = now - lastTime;
            if(timeChange > 0) {
                this.input = input;

                // Compute all the working error variables
                double error = setPoint - input;
                iTerm += iGain * error * timeChange;
                iTerm = limit(iTerm, maxOutput);
                double dInput = (input - lastInput) / timeChange; //compute dInput instead of dError to avoid spikes

                //Compute PID Output
                output = pGain * error + iTerm - dGain * dInput;
                output = limit(output, maxOutput);

                //Remember some variables for next time
                lastInput = input;
                lastTime = now;
            }
        }
        return output;
    }

    private double limit(double value, double max){
        if (value > max) return max;
        if (value < -max) return -max;
        return value;
    }

    public void initialize() {
        lastInput = input;
        iTerm = 0;
    }
}
