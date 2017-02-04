package ftc.electronvolts.util;

public class ValueHistory {
    private final double[] values;
    private int index = 0;
    private boolean allActive = false;
    private double average, standardDeviation, coefficientOfVariation;

    public ValueHistory(int length) {
        values = new double[length];
    }

    public double replaceOldestValue(double newValue) {
        double oldValue = values[index];
        values[index] = newValue;
        index++;
        if (index >= values.length) {
            index = 0;
            allActive = true;
        }

        int numActive = getNumActive();

        double total = 0;
        for (int i = 0; i < numActive; i++) {
            total += values[i];
        }
        average = total / numActive;

        double variance = 0;
        for (int i = 0; i < numActive; i++) {
            double difference = average - values[i];
            variance += difference * difference;
        }
        variance /= numActive;
        standardDeviation = Math.sqrt(variance);
        
        coefficientOfVariation = standardDeviation / average;

        return oldValue;
    }

    public boolean areAllActive() {
        return allActive;
    }

    public int getLength() {
        return values.length;
    }

    public int getNumActive() {
        if (allActive) {
            return values.length;
        } else {
            return index;
        }
    }

    public double getAverage() {
        return average;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }
    
    public double getCoefficientOfVariation() {
        return coefficientOfVariation;
    }

}
