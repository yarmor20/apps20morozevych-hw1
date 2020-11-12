package ua.edu.ucu.tempseries;
import mathOperations.*;

import java.util.InputMismatchException;


public class TemperatureSeriesAnalysis {
    private double[] temeperatureSeries;

    public TemperatureSeriesAnalysis() {
        this.temeperatureSeries = null;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temeperatureSeries = temperatureSeries;
    }

    public double average() {
        if (temeperatureSeries.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }

        double tempSum = 0;
        for (double temperature: temeperatureSeries) {
            tempSum += temperature;
        }
        return tempSum / temeperatureSeries.length;
    }

    private double squaredDifference(double mean) {
        double sqDiff = 0;
        for (double temperature: temeperatureSeries) {
            sqDiff += Math.pow((temperature - mean), 2);
        }
        return sqDiff / temeperatureSeries.length;
    }

    public double deviation() {
        if (temeperatureSeries.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }
        double mean = average();
        return Math.sqrt(squaredDifference(mean));
    }

    private double getExtremum(MathOperation mathOperation) {
        double extrElement = temeperatureSeries[0];
        for (double temperature: temeperatureSeries) {
            if (mathOperation.operation(temperature, extrElement)) {
                extrElement = temperature;
            }
        }
        return extrElement;
    }

    public double min() {
        return getExtremum(new IsLess());
    }

    public double max() {
        return getExtremum(new IsBigger());
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (temeperatureSeries.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }

        double smallestDistance = Double.MAX_VALUE;
        double closestTemp = temeperatureSeries[0];

        for (double temperature: temeperatureSeries) {
            double stepDistance = Math.abs(tempValue - temperature);
            if (stepDistance < smallestDistance) {
                if (closestTemp == Math.abs(temperature) && temperature < 0) {
                    continue;
                }
                smallestDistance = stepDistance;
                closestTemp = temperature;
            }
        }
        return closestTemp;
    }

    private double[] findTempExtremum(double tempValue, MathOperation mathOperation) {
        int counter = 0;

        for (double temperature: temeperatureSeries)  {
            if (mathOperation.operation(temperature, tempValue)) {
                counter++;
            }
        }

        double[] smallerTemp = new double[counter];
        counter = 0;

        for (double temperature: temeperatureSeries)  {
            if (mathOperation.operation(temperature, tempValue)) {
                smallerTemp[counter] = temperature;
                counter++;
            }
        }

        return smallerTemp;
    }
    public double[] findTempsLessThen(double tempValue) {
        if (temeperatureSeries.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }
        return findTempExtremum(tempValue, new IsLess());
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (temeperatureSeries.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }
        return findTempExtremum(tempValue, new IsBigger());
    }

    public TempSummaryStatistics summaryStatistics() throws CloneNotSupportedException {
        TempSummaryStatistics tempStats = new TempSummaryStatistics(this);
        return tempStats.clone();
    }

    public int addTemps(double ... temps) {
        if (temeperatureSeries.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }

        int actualQuantity = 0;
        double[] tempSeriesCopy = temeperatureSeries.clone();

        for (double temp: temps) {
            if (temp < -273.0) {
                temeperatureSeries = tempSeriesCopy;
                throw new InputMismatchException();
            }
            if (actualQuantity + 1 < temeperatureSeries.length) {
                reserveSpace(2 * temeperatureSeries.length);
            }
            temeperatureSeries[actualQuantity] = temp;
            actualQuantity++;
        }
        return 0;
    }

    private void reserveSpace (int space) {
        double[] newTempSeries = new double[space];
        int counter = 0;

        for (double temp: temeperatureSeries) {
            newTempSeries[counter] = temp;
            counter++;
        }
        this.temeperatureSeries = newTempSeries;
    }
}
