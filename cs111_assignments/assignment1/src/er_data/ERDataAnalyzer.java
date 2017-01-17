package er_data;

public class ERDataAnalyzer {
	// private methods here
	// If you find yourself writing the same code over and over, 
	// consider creating a private method which you can call instead.

	/**
	 * Converts a 3-d integer array of visitations by total patient visits per hour
	 * and converts it into a 2-d integer array of total patients per day.
	 * 
	 * @param data 	a 3-d integer array corresponding to the number of visitations.
	 * 				1st dimension corresponds to the week, 2nd dimension the day, 3rd dimension the hour.
	 * @return		a 2-d integer array of total patient visits per day.
	 */
	private static int[][] convertHourstoDays(int[][][] data){
		int sum = 0;
		int[][] anArray = new int[data.length][data[0].length];
		for(int week = 0; week < data.length; week++){
			for(int day = 0; day < data[0].length; day++){
				for(int hour = 0; hour < data[0][0].length; hour++){
					sum += data[week][day][hour];
				}
				anArray[week][day] += sum;
				sum = 0;
			}
		}	
		return anArray;
	}

	/**

	 * Converts a 2-d integer array of visitations by total patient visits per day
	 * and converts it into a 1-d integer array of total patients per week.
	 * 
	 * @param data 	a 2-d integer array corresponding to the number of visitations.
	 * 				1st dimension corresponds to the week, 2nd dimension the day.
	 * @return		a 1-d integer array of total patient visits per week.
	 */
	private static int[] convertDaystoWeeks(int[][] data){
		int sum = 0;
		int[] anArray = new int[data.length];
		for(int week = 0; week < data.length; week++){
			for(int day = 0; day < data[0].length; day++){
				sum += data[week][day];
			}
			anArray[week] = sum;
			sum = 0;
		}
		return anArray;
	}

	/**
	 * Converts a 3-d integer array of visitations by total patient visits per hour
	 * and converts it into a 2-d double array of total patients per day.
	 * 
	 * @param data 	a 3-d integer array corresponding to the number of visitations.
	 * 				1st dimension corresponds to the week, 2nd dimension the day, 3rd dimension the hour.
	 * @return		a 2-d integer array of total patient visits per day.
	 */
	private static double[][] doubleconvertHourstoDays(int[][][] data){
		int sum = 0;
		double[][] anArray = new double[data.length][data[0].length];
		for(int week = 0; week < data.length; week++){
			for(int day = 0; day < data[0].length; day++){
				for(int hour = 0; hour < data[0][0].length; hour++){
					sum += data[week][day][hour];
				}
				anArray[week][day] += sum;
				sum = 0;
			}
		}	
		return anArray;
	}

	/**
	 * Replaces integers 0-6 with corresponding String days starting from 0 = Sunday.
	 * @param number	integer number from 0 to 6.
	 * @return			String of the day corresponding to the number.
	 */
	public static String numbertoDay(int number){
		String day = "";
		switch(number){
		case 0: day = "Sunday";
			break;
		case 1: day = "Monday";
			break;
		case 2: day = "Tuesday";
			break;
		case 3: day = "Wednesday";
			break;
		case 4: day = "Thursday";
			break;
		case 5: day = "Friday";
			break;
		case 6: day = "Saturday";
			break;
		default: 
			break;
		}
		return day;
	}
	
	/**
	 * Returns the total number of patients for each week
	 * 
	 * @param data	a 3-d integer array corresponding to the number of visitations. 
	 * 				1st dimension corresponds to the week, 2nd dimension the day, 3rd dimension the hour 				
	 * @return 		an 1-d integer array of weekly totals
	 */
	public static int[] patientsPerWeek(int[][][] data) {
		int[] anArray = convertDaystoWeeks(convertHourstoDays(data));
		return anArray;
	}

	/**
	 * Returns the total number of visits for each day, for each week.
	 * 
	 * @param data	
	 * @return		2-d integer array of daily totals. 1st dimension is the week, 2nd is the day
	 */
	public static int[][] patientsPerDayPerWeek(int[][][] data) {
		int[][] anArray = convertHourstoDays(data);
		return anArray;
	}

	/**
	 * Returns the average number of patients seen in a day for each week.
	 * For example, given the following two weeks of daily visitations:
	 * 
	 * twoWeekDailyTotals = [[10, 10, 10, 15, 20, 20 20], [20, 20, 20, 30, 40, 40, 40]]
	 * assert averagePatientsPerWeek(twoWeekDailyTotals) == [15.0, 30.0]
	 * 
	 * @param 		data
	 * @return 		1-d double array of the average number of daily patients for each week
	 */
	public static double[] averagePatientsPerWeek(int[][][] data) { 
		int[] anArray = convertDaystoWeeks(convertHourstoDays(data));
		double[] avgArray = new double[data.length];
		for(int avg = 0; avg < data.length; avg++){
			avgArray[avg] = (double)anArray[avg]/data[0].length;
		}
		return avgArray;
	}

	/**
	 * Returns the average number of patients seen on Sundays, Mondays, Tuesdays, etc. 
	 * over an n week period.
	 * 
	 * @param data
	 * @return 		a 1-d double array of daily average visitations
	 * 				where the 1st dimension corresponds to the day
	 */
	public static double[] averagePatientsPerDayAcrossWeeks(int[][][] data) {
		double[][] anArray = doubleconvertHourstoDays(data);
		double[] avgArray = new double[anArray[0].length];
		int sum = 0;
		for(int day = 0; day < anArray[0].length; day++){
			for(int week = 0; week < anArray.length; week++){
				sum += anArray[week][day];
			}
			avgArray[day] = (double)sum/anArray.length;
			sum = 0;
		}
		return avgArray;
	}

	/**
	 * Returns an array of integers indexing the busiest day (most visits)
	 * of the week, for each week. For example, if the daily total visits
	 * over a two week period are:
	 * 
	 * twoWeekDailyTotals = [[10, 50, 20, 15, 30, 9, 25], [20, 30, 60, 60, 10, 15, 5]]
	 * assert busiestDayPerWeek(twoWeekDailyTotals) == [1,  2]
	 * (In case of ties, just choose one of the busiest days)
	 * 
	 * 
	 * @param data
	 * @return		1-d integer array of array indices indicating, for each
	 *              week, the (or one of the) day(s) with the most visits 
	 */
	public static int[] busiestDayPerWeek(int[][][] data) {
		int[][] anArray = patientsPerDayPerWeek(data);
		int[] busiestDay = new int[anArray.length];
		int busyDay = 0;
		for(int week = 0; week < anArray.length; week++){
			for(int day = 0; day < anArray[0].length; day++){
				if(anArray[week][day]>busyDay){
					busyDay = anArray[week][day];
					busiestDay[week] = day;
				}
			}
			busyDay = 0;
		}
		return busiestDay;
	}

	/**
	 * Returns an array of integers indexing the least busy day (fewest visits)
	 * of the week, for each week.
	 * 
	 * @param data
	 * @return		1-d integer array of array indices indicating, for each
	 * 				week, the (or one of the) day(s) with the fewest visits 
	 */
	public static int[] leastBusyDayPerWeek(int[][][] data) {
		int[][] anArray = patientsPerDayPerWeek(data);
		int[] leastBusiestDay = new int[anArray.length];
		int leastBusyDay = 1000000;
		for(int week = 0; week < anArray.length; week++){
			for(int day = 0; day < anArray[0].length; day++){
				if(anArray[week][day]<leastBusyDay){
					leastBusyDay = anArray[week][day];
					leastBusiestDay[week] = day;
				}
			}
			leastBusyDay = 1000000;
		}
		return leastBusiestDay;
	}
}