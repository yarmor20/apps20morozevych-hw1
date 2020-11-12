package ua.edu.ucu.tempseries;

public final class TempSummaryStatistics implements Cloneable {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    public TempSummaryStatistics(TemperatureSeriesAnalysis tempAnalysis) {
        this.avgTemp = tempAnalysis.average();
        this.devTemp = tempAnalysis.deviation();
        this.minTemp = tempAnalysis.min();
        this.maxTemp = tempAnalysis.max();
    }

    public double getAverage() {
        return this.avgTemp;
    }

    public double getDevTemp() {
        return this.devTemp;
    }

    public double getMinTemp() {
        return this.minTemp;
    }

    public double getMaxTemp() {
        return this.maxTemp;
    }

    @Override
    protected TempSummaryStatistics clone() throws CloneNotSupportedException {
        return (TempSummaryStatistics) super.clone();
    }
}
