package camelinactionConsumer;

public class DatabaseInterfaceImpl implements DatabaseInterface {

	private DatabaseInterfaceImpl(){};
	private static DatabaseQueryEngine myDatabaseQueryEngine;
	
	// DatabaseInterface is a Singleton
	// It also is the top level layer
	public static DatabaseInterface getInstance(){
		if (myDatabaseInterface == null) {
			myDatabaseInterface = new DatabaseInterfaceImpl();
			myDatabaseQueryEngine = new DatabaseQueryEngineImpl();
		}
		return myDatabaseInterface;
	}
	private static DatabaseInterface myDatabaseInterface = null;

    
	// METHODS (basically all of these are implementing the Facade design pattern, calling down to DatabaseQueryEngine layer):

	
	// INITIALIZING SYSTEM:
	// NOTE: only call this once ever, after having done so once always use synchronizeAndStartSystem
	// Method to initialize the database system the first time it is ever used
	/* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#initializeSystem()
	 */
	public final void initializeSystem() throws Exception {
		myDatabaseQueryEngine.initializeDatabaseEngine();
	 }
	    
    // STARTING SYSTEM:
	// NOTE: only call this once
    //Method to start and synchronize the database system
	// TO DO: think about how to avoid calling this twice!
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#synchronizeAndStartSystem()
	 */
    public void synchronizeAndStartSystem() throws Exception {		
        myDatabaseQueryEngine.synchronizeSystem();
    }
    
    
    // UPLOADING:
    // TO DO: PROBABLY NOT SUPPORTED
    
    
    // SEARCHING INDIVIDUAL ENTRIES:
    //Method to print out details of a crime, given its ID
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#printDetailsCrimeByID(int)
	 */
    public void printDetailsCrimeByID( int crimeID ) {
    	System.out.println(myDatabaseQueryEngine.getDetailsForCrimeID(crimeID));
    }
    
    
    // DOWNLOADING:
    //Method to download all crime data in the database, in a csv file with the given file name
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#downloadAllData(java.lang.String)
	 */
    public void downloadAllData( String outputFileName ) {
        myDatabaseQueryEngine.callDownloadAllData(outputFileName);
    }
    
    //Method to download data of all crimes in a certain city, given its name. The file will be a single csv file with the given file name.
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#downloadAllDataForCity(java.lang.String, java.lang.String)
	 */
    public void downloadAllDataForCity( String name, String outputFileName ) {
        myDatabaseQueryEngine.callDownloadAllDataForCity(name, outputFileName);
    }
    
    //Method to download data of all crimes in a certain year. The file will be a single csv file with the given file name.
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#downloadAllDataForYear(int, java.lang.String)
	 */
    public void downloadAllDataForYear( int year, String outputFileName ) {
        myDatabaseQueryEngine.callDownloadAllDataForYear(year, outputFileName);
    }
    
    //Method to download data of all crimes in a certain month. The file will be a single csv file with the given file name.
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#downloadAllDataForMonth(int, java.lang.String)
	 */
    public void downloadAllDataForMonth( int month, String outputFileName ) {
        myDatabaseQueryEngine.callDownloadAllDataForMonth(month, outputFileName);
    }
    
    //Method to download data of all crimes in a certain crime category, given its ucrNcicCode. The file will be a single csv file with the given file name.
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#downloadAllDataForCrimeCategory(int, java.lang.String)
	 */
    public void downloadAllDataForCrimeCategory( int ucrNcicCode, String outputFileName ) {
        myDatabaseQueryEngine.callDownloadAllDataForCrimeCategory(ucrNcicCode, outputFileName);
    }
    
    //Method to download data of crimes in a certain city (indicated by cityName), filtered based on a search query in a specific column. The columnNames to be searched are "grid", "district", "beat", "ucrNcicCode", "year" and "month". The file will be a single csv file with the given file name.
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#downloadFilteredDataForCity(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
    public void downloadFilteredDataForCity( String cityName, String columnName, String queryValue, String outputFileName ) {
        myDatabaseQueryEngine.callDownloadFilteredDataForCity(cityName, columnName, queryValue, outputFileName);
    }
    
    //Method to download data of crimes in a certain year (indicated by year), filtered based on a search query in a specific column. The columnNames to be searched are "city", "ucrNcicCode" and "month". The file will be a single csv file with the given file name.
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#downloadFilteredDataForYear(int, java.lang.String, java.lang.String, java.lang.String)
	 */
    public void downloadFilteredDataForYear( int year, String columnName, String queryValue, String outputFileName ) {
        myDatabaseQueryEngine.callDownloadFilteredDataForYear(year, columnName, queryValue, outputFileName);
    }
    
    //Method to download data of crimes in a certain month (indicated by month), filtered based on a search query in a specific column. The columnNames to be searched are "city", "ucrNcicCode" and "year". The file will be a single csv file with the given file name.
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#downloadFilteredDataForMonth(int, java.lang.String, java.lang.String, java.lang.String)
	 */
    public void downloadFilteredDataForMonth( int month, String columnName, String queryValue, String outputFileName ) {
        myDatabaseQueryEngine.callDownloadFilteredDataForMonth(month, columnName, queryValue, outputFileName);
    }
    
    //Method to download data of crimes of a certain crime category (indicated by ucrNcicCode), filtered based on a search query in a specific column. The columnNames to be searched are "city", "year" and "month". The file will be a single csv file with the given file name.
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#downloadFilteredDataForCrimeCategory(int, java.lang.String, java.lang.String, java.lang.String)
	 */
    public void downloadFilteredDataForCrimeCategory( int ucrNcicCode, String columnName, String queryValue, String outputFileName ) {
        myDatabaseQueryEngine.callDownloadFilteredDataForCrimeCategory(ucrNcicCode, columnName, queryValue, outputFileName);
    }

    
    // STATISTICS:
    //Method to print the total count of crimes in the database
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#printTotalCrimeCount()
	 */
    public void printTotalCrimeCount( ) {
        System.out.println(myDatabaseQueryEngine.getTotalCountCrimes());
    }
    
    //Method to print a specific statistic per city, based on one of the two methods: "mean" or median" of crimes per city
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#printSpecificStatisticPerCity(java.lang.String)
	 */
    public void printSpecificStatisticPerCity( String statisticsMethod ) {
        System.out.println(myDatabaseQueryEngine.getSpecificStatisticPerCity(statisticsMethod));
    }
    
    //Method to print a specific statistic per year, based on one of the two methods: "mean" or median" of crimes per year
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#printSpecificStatisticPerYear(java.lang.String)
	 */
    public void printSpecificStatisticPerYear( String statisticsMethod ) {
    	System.out.println(myDatabaseQueryEngine.getSpecificStatisticPerYear(statisticsMethod));
    }
    
    //Method to print a specific statistic per month, based on one of the two methods: "mean" or median" of crimes per month
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#printSpecificStatisticPerMonth(java.lang.String)
	 */
    public void printSpecificStatisticPerMonth( String statisticsMethod ) {
    	System.out.println(myDatabaseQueryEngine.getSpecificStatisticPerMonth(statisticsMethod));
    }
    
    //Method to print a specific statistic per crime category, based on one of the two methods: "mean" or median" of crimes per category
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#printSpecificStatisticPerCrimeCategory(java.lang.String)
	 */
    public void printSpecificStatisticPerCrimeCategory( String statisticsMethod ) {
    	System.out.println(myDatabaseQueryEngine.getSpecificStatisticPerCrimeCategory(statisticsMethod));
    }
    
    //Method to print summary and statistics for the entire database
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#printOverallSummaryAndStatistics()
	 */
    public void printOverallSummaryAndStatistics( ) {
    	System.out.println(myDatabaseQueryEngine.getOverallSummaryAndStatistics());
    }
    
    //Method to print all statistics for a given city
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#printAllStatisticsForCity(java.lang.String)
	 */
    public void printAllStatisticsForCity( String cityName ) {
    	System.out.println(myDatabaseQueryEngine.getAllStatisticsForCity(cityName));
    }
    
    //Method to print all statistics for a given year
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#printAllStatisticsForYear(int)
	 */
    public void printAllStatisticsForYear( int year ) {
    	System.out.println(myDatabaseQueryEngine.getAllStatisticsForYear(year));
    }
    
    //Method to print all statistics for a given month
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#printAllStatisticsForMonth(int)
	 */
    public void printAllStatisticsForMonth( int month ) {
    	System.out.println(myDatabaseQueryEngine.getAllStatisticsForMonth(month));
    }
    
    //Method to print summary statistics for a given crime category
    /* (non-Javadoc)
	 * @see camelinactionConsumer.DatabaseInterface#printAllStatisticsForCrimeCategory(int)
	 */
    public void printAllStatisticsForCrimeCategory( int ucrNcicCode ) {
    	System.out.println(myDatabaseQueryEngine.getAllStatisticsForCrimeCategory(ucrNcicCode));
    }
   
}