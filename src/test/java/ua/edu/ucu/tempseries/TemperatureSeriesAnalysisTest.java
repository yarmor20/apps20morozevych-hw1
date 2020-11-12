package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.74165739;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMinValue() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 6.0, -2.0, 2.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMaxValue() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 6.0, -2.0, 2.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 6.0;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 6.0, -2.0, 2.0, -1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 6.0, -2.0, 2.0, -1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 6.0;

        double actualResult = seriesAnalysis.findTempClosestToValue(7.0);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 6.0, -2.0, 2.0, -1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-5.0, 1.0, -2.0, -1.0};

        double[] actualResult = seriesAnalysis.findTempsLessThen(2.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 6.0, -2.0, 2.0, -1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {3.0, 5.0, 6.0};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(2.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatistics() throws CloneNotSupportedException {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics expResult = seriesAnalysis.summaryStatistics();

        assertEquals(expResult.getAverage(), 1.0, 0.00001);
        assertEquals(expResult.getDevTemp(), 3.74165739, 0.00001);
        assertEquals(expResult.getMinTemp(), -5.0, 0.00001);
        assertEquals(expResult.getMaxTemp(), 5.0, 0.00001);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        int result = seriesAnalysis.addTemps(6.0, 7.0, -1.0, 12.0);
        int expResult = 0;

        assertEquals(result, expResult);
    }
}
