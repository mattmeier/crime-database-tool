package camelinactionConsumer;

public interface DatabaseQueryEngine {

	// STARTING SYSTEM:
	// NOTE: only call this once
	// Method to initialize the system
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#initializeDatabaseEngine()
	 */
	public void initializeDatabaseEngine() throws Exception;

	//Helper method to add a new crime to the DatabaseQueryEngine
	// creates crime object as well as cities, years, months and crime category objects as needed
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#addCrime(camelinactionConsumer.Crime)
	 */
	public void addCrime(Crime newCrime);

	// Method to synchronize the system with the data in ActiveMQ
	//Ensures we create the respective classes for these databases to do search and statistics operations
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#synchronizeSystem()
	 */
	public void synchronizeSystem() throws Exception;

	// SEARCHING INDIVIDUAL ENTRIES:
	// Method to get the details for a specific crime, given its ID (start counting at 1)
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getDetailsForCrimeID(int)
	 */
	public String getDetailsForCrimeID(int crimeID);

	// DOWNLOADING:
	//Method to download all crime data in the database, in a single csv file with the given file name
	//calling down to myMainDatabase layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#callDownloadAllData(java.lang.String)
	 */
	public void callDownloadAllData(String outputFileName);

	//Method to download data of all crimes in a certain city, given its name. The file will be a single csv file with the given file name.
	//calling down to City layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#callDownloadAllDataForCity(java.lang.String, java.lang.String)
	 */
	public void callDownloadAllDataForCity(String name, String outputFileName);

	//Method to download data of all crimes in a certain year. The file will be a single csv file with the given file name.
	//calling down to Year layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#callDownloadAllDataForYear(int, java.lang.String)
	 */
	public void callDownloadAllDataForYear(int year, String outputFileName);

	//Method to download data of all crimes in a certain month. The file will be a single csv file with the given file name.
	//calling down to Month layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#callDownloadAllDataForMonth(int, java.lang.String)
	 */
	public void callDownloadAllDataForMonth(int month, String outputFileName);

	//Method to download data of all crimes in a certain crime category, given its ucrNcicCode. The file will be a single csv file with the given file name.
	//calling down to CrimeCategory layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#callDownloadAllDataForCrimeCategory(int, java.lang.String)
	 */
	public void callDownloadAllDataForCrimeCategory(int ucrNcicCode,
			String outputFileName);

	//Method to download data of crimes in a certain city (indicated by cityName), filtered based on a search query in a specific column. The columnNames to be searched are "grid", "district", "beat", "ucrNcicCode", "year" and "month". 
	//The file will be a single csv file with the given file name.
	//calling down to City layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#callDownloadFilteredDataForCity(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void callDownloadFilteredDataForCity(String cityName,
			String columnName, String queryValue, String outputFileName);

	//Method to download data of crimes in a certain year (indicated by year), filtered based on a search query in a specific column. The columnNames to be searched are "city", "ucrNcicCode" and "month". 
	//The file will be a single csv file with the given file name.
	//calling down to Year layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#callDownloadFilteredDataForYear(int, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void callDownloadFilteredDataForYear(int year, String columnName,
			String queryValue, String outputFileName);

	//Method to download data of crimes in a certain month (indicated by month), filtered based on a search query in a specific column. The columnNames to be searched are "city", "ucrNcicCode" and "year". 
	//The file will be a single csv file with the given file name.
	//calling down to Month layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#callDownloadFilteredDataForMonth(int, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void callDownloadFilteredDataForMonth(int month, String columnName,
			String queryValue, String outputFileName);

	//Method to download data of crimes of a certain crime category (indicated by ucrNcicCode), filtered based on a search query in a specific column. The columnNames to be searched are "city", "year" and "month". 
	// The file will be a single csv file with the given file name.
	//calling down to CrimeCategory layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#callDownloadFilteredDataForCrimeCategory(int, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void callDownloadFilteredDataForCrimeCategory(int ucrNcicCode,
			String columnName, String queryValue, String outputFileName);

	// STATISTICS:
	//Method to get the total count of crimes in the database
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getTotalCountCrimes()
	 */
	public String getTotalCountCrimes();

	//Method to get the number of cities covered in the database
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getNumberCities()
	 */
	public String getNumberCities();

	//Method to get the number of years covered in the database
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getNumberYears()
	 */
	public String getNumberYears();

	//Method to get the number of months covered in the database
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getNumberMonths()
	 */
	public String getNumberMonths();

	//Method to get the number of crime categories covered in the database
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getNumberCategories()
	 */
	public String getNumberCategories();

	//Method to get the names of the cities covered in the database, in a String
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getNamesCities()
	 */
	public String getNamesCities();

	//Method to get the years covered in the database, in a String
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getYears()
	 */
	public String getYears();

	//Method to get summary and statistics for the entire database
	//calling down to myMainDatabase layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getOverallSummaryAndStatistics()
	 */
	public String getOverallSummaryAndStatistics();

	//Method to get a specific statistic per city, based on one of the two methods: "mean" or median" of crimes per city; calling down to City layer 
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getSpecificStatisticPerCity(java.lang.String)
	 */
	public String getSpecificStatisticPerCity(String statisticsMethod);

	//Method to get a specific statistic per year, based on one of the two methods: "mean" or median" of crimes per year; calling down to Year layer 
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getSpecificStatisticPerYear(java.lang.String)
	 */
	public String getSpecificStatisticPerYear(String statisticsMethod);

	//Method to get a specific statistic per month, based on one of the two methods: "mean" or median" of crimes per month; calling down to Month layer 
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getSpecificStatisticPerMonth(java.lang.String)
	 */
	public String getSpecificStatisticPerMonth(String statisticsMethod);

	//Method to get a specific statistic per crime category, based on one of the two methods: "mean" or median" of crimes per category, calling down to CrimeCategory layer 
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getSpecificStatisticPerCrimeCategory(java.lang.String)
	 */
	public String getSpecificStatisticPerCrimeCategory(String statisticsMethod);

	//method to get all statistics for a given city, calling down to City layer 
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getAllStatisticsForCity(java.lang.String)
	 */
	public String getAllStatisticsForCity(String cityName);

	//method to get all statistics for a given year, calling down to Year layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getAllStatisticsForYear(int)
	 */
	public String getAllStatisticsForYear(int year);

	//method to get all statistics for a given month, calling down to Month layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getAllStatisticsForMonth(int)
	 */
	public String getAllStatisticsForMonth(int month);

	//method to get all statistics for a given crime category, calling down to CrimeCategory layer
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseQueryEngine#getAllStatisticsForCrimeCategory(int)
	 */
	public String getAllStatisticsForCrimeCategory(int ucrNcicCode);

}