package er_data_tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import org.junit.Test;

import er_data.ERDataReader;
import er_data.ERDataAnalyzer;

public class ERDataAnalyzerTest {
  private static final String DATA_FILE = "data/data1.txt";

  @Test
  public void testPatientsPerWeek()
      throws FileNotFoundException, NoSuchElementException, IllegalStateException {
  // We are assuming our ERDataReader works...
    int[][][] testData = ERDataReader.readData(DATA_FILE);
    int[] expecteds = {3118, 2746, 2921, 2676};
    int[] actuals = ERDataAnalyzer.patientsPerWeek(testData);
    assertArrayEquals(expecteds, actuals);
  }
  
  @Test
  public void testpatientsPerDayPerWeek()
      throws FileNotFoundException, NoSuchElementException, IllegalStateException {
    int[][][] testData = ERDataReader.readData(DATA_FILE);
    int[][] expecteds = {{476, 537, 421, 362, 461, 431, 430},
             {371, 362, 461, 349, 395, 486, 322},
             {431, 531, 395, 383, 313, 434, 434},
             {476, 461, 454, 265, 272, 348, 400}};
    int[][] actuals = ERDataAnalyzer.patientsPerDayPerWeek(testData);
    assertArrayEquals(expecteds, actuals);
  }
  
  @Test
  public void testAveragePatientsPerWeek() 
      throws FileNotFoundException, NoSuchElementException, IllegalStateException {
    int[][][] testData = ERDataReader.readData(DATA_FILE);
    double[] expecteds = {445.42857142857, 392.2857142857, 417.2857142857, 382.2857142857}; 
    double[] actuals = ERDataAnalyzer.averagePatientsPerWeek(testData);
    assertArrayEquals(expecteds, actuals, .00001);
  }
  
  @Test
  public void testAveragePatientsPerDayAcrossWeeks() 
      throws FileNotFoundException, NoSuchElementException, IllegalStateException {
    int[][][] testData = ERDataReader.readData(DATA_FILE);
    double[] expecteds = {438.5, 472.75, 432.75, 339.75, 360.25, 424.75, 396.5};
    double[] actuals = ERDataAnalyzer.averagePatientsPerDayAcrossWeeks(testData);
    assertArrayEquals(expecteds, actuals, .00001);
  }
  
  @Test
  public void testBusiestDayPerWeek() 
      throws FileNotFoundException, NoSuchElementException, IllegalStateException {
    int[][][] testData = ERDataReader.readData(DATA_FILE);
    int[] expecteds = {1, 5, 1, 0};
    int[] actuals = ERDataAnalyzer.busiestDayPerWeek(testData);
    assertArrayEquals(expecteds, actuals);
  }
  
  @Test
  public void testLeastBusyDayPerWeek() 
      throws FileNotFoundException, NoSuchElementException, IllegalStateException {
    int[][][] testData = ERDataReader.readData(DATA_FILE);
    int[] expecteds = {3, 6, 4, 3};
    int[] actuals = ERDataAnalyzer.leastBusyDayPerWeek(testData);
    assertArrayEquals(expecteds, actuals);
  }
  
}
