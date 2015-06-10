package camelinactionConsumer;

// Some tests
class DatabaseTest {
	public static void main(String[] args) throws Exception {
		
		DatabaseInterface myDatabaseInterface = DatabaseInterfaceImpl.getInstance();
		
		//Only do this the first time you run the database system ever! After initialization, always use synchronizeAndStartSystem.
		// However, note that you have to do this once to make system setup and database working.
		//myDatabaseInterface.initializeSystem();
		
		myDatabaseInterface.synchronizeAndStartSystem();
		// print total count of crimes in database
		myDatabaseInterface.printTotalCrimeCount();
		
		// print details for a single crime, given its ID, e.g. 200
		myDatabaseInterface.printDetailsCrimeByID(200);
		
		// print overall summary and statistics for database
		myDatabaseInterface.printOverallSummaryAndStatistics();
		
		// print overall summary and statistics for a given city, e.g. Chicago
		myDatabaseInterface.printAllStatisticsForCity("Chicago");
		
		// print overall summary and statistics for a given year, e.g. 2006
		myDatabaseInterface.printAllStatisticsForYear(2006);
		
		// print overall summary and statistics for a given year, e.g. 2 for February
		myDatabaseInterface.printAllStatisticsForMonth(2);
		
		// print overall summary and statistics for a given crime category, given its crime code, e.g. 7000
		myDatabaseInterface.printAllStatisticsForCrimeCategory(7000);
		
		// download all crimes data for the entire database in one csv file
		myDatabaseInterface.downloadAllData("data/downloads/all_crimes.csv");
		
		// download all crimes data for a given city in one csv file (you can do the same for years, months, crime categories)
		myDatabaseInterface.downloadAllDataForCity("Chicago", "data/downloads/crimes_chicago.csv");
		
		// download all data of crimes in a certain city (indicated by <cityName>), filtered based on a search query <queryValue> in a specific column <columnName>. The columnNames to be searched are 'grid', 'district', 'beat', 'ucrNcicCode', 'year' and 'month'. The file will be a single csv file with the given file name. <outputFileName>.(you can do the similar filtered downloads for years, months, crime categories)
		myDatabaseInterface.downloadFilteredDataForCity("Chicago", "district", "2", "data/downloads/crimes_chicago_district_2.csv");
		
		//  print a specific statistic per city, based on one of the two methods <statisticMethod>: 'mean' or 'median' of crimes per city. (Similar methods available for years, months and crime categories)
		myDatabaseInterface.printSpecificStatisticPerCity("mean");
		myDatabaseInterface.printSpecificStatisticPerCity("median");
		
	}
}
