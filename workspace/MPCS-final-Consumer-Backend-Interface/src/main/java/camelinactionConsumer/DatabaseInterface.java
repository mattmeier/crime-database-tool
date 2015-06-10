package camelinactionConsumer;

public interface DatabaseInterface {

	// INITIALIZING SYSTEM:
	// NOTE: only call this once ever, after having done so once always use synchronizeAndStartSystem
	// Method to initialize the database system the first time it is ever used
	public void initializeSystem() throws Exception;

	// STARTING SYSTEM:
	// NOTE: only call this once
	//Method to start and synchronize the database system
	// TO DO: think about how to avoid calling this twice!
	public void synchronizeAndStartSystem() throws Exception;

	// SEARCHING INDIVIDUAL ENTRIES:
	//Method to print out details of a crime, given its ID
	public void printDetailsCrimeByID(int crimeID);

	// DOWNLOADING:
	//Method to download all crime data in the database, in a csv file with the given file name
	public void downloadAllData(String outputFileName);

	//Method to download data of all crimes in a certain city, given its name. The file will be a single csv file with the given file name.
	public void downloadAllDataForCity(String name, String outputFileName);

	//Method to download data of all crimes in a certain year. The file will be a single csv file with the given file name.
	public void downloadAllDataForYear(int year, String outputFileName);

	//Method to download data of all crimes in a certain month. The file will be a single csv file with the given file name.
	public void downloadAllDataForMonth(int month, String outputFileName);

	//Method to download data of all crimes in a certain crime category, given its ucrNcicCode. The file will be a single csv file with the given file name.
	public void downloadAllDataForCrimeCategory(int ucrNcicCode,
			String outputFileName);

	//Method to download data of crimes in a certain city (indicated by cityName), filtered based on a search query in a specific column. The columnNames to be searched are "grid", "district", "beat", "ucrNcicCode", "year" and "month". The file will be a single csv file with the given file name.
	public void downloadFilteredDataForCity(String cityName, String columnName,
			String queryValue, String outputFileName);

	//Method to download data of crimes in a certain year (indicated by year), filtered based on a search query in a specific column. The columnNames to be searched are "city", "ucrNcicCode" and "month". The file will be a single csv file with the given file name.
	public void downloadFilteredDataForYear(int year, String columnName,
			String queryValue, String outputFileName);

	//Method to download data of crimes in a certain month (indicated by month), filtered based on a search query in a specific column. The columnNames to be searched are "city", "ucrNcicCode" and "year". The file will be a single csv file with the given file name.
	public void downloadFilteredDataForMonth(int month, String columnName,
			String queryValue, String outputFileName);

	//Method to download data of crimes of a certain crime category (indicated by ucrNcicCode), filtered based on a search query in a specific column. The columnNames to be searched are "city", "year" and "month". The file will be a single csv file with the given file name.
	public void downloadFilteredDataForCrimeCategory(int ucrNcicCode,
			String columnName, String queryValue, String outputFileName);

	// STATISTICS:
	//Method to print the total count of crimes in the database
	public void printTotalCrimeCount();

	//Method to print a specific statistic per city, based on one of the two methods: "mean" or median" of crimes per city
	public void printSpecificStatisticPerCity(String statisticsMethod);

	//Method to print a specific statistic per year, based on one of the two methods: "mean" or median" of crimes per year
	public void printSpecificStatisticPerYear(String statisticsMethod);

	//Method to print a specific statistic per month, based on one of the two methods: "mean" or median" of crimes per month
	public void printSpecificStatisticPerMonth(String statisticsMethod);

	//Method to print a specific statistic per crime category, based on one of the two methods: "mean" or median" of crimes per category
	public void printSpecificStatisticPerCrimeCategory(String statisticsMethod);

	//Method to print summary and statistics for the entire database
	public void printOverallSummaryAndStatistics();

	//Method to print all statistics for a given city
	public void printAllStatisticsForCity(String cityName);

	//Method to print all statistics for a given year
	public void printAllStatisticsForYear(int year);

	//Method to print all statistics for a given month
	public void printAllStatisticsForMonth(int month);

	//Method to print summary statistics for a given crime category
	public void printAllStatisticsForCrimeCategory(int ucrNcicCode);

}