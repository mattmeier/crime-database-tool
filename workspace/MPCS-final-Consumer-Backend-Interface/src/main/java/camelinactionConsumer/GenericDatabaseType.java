package camelinactionConsumer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public interface GenericDatabaseType {

	//Method to get the name of the database
	public String getName();

	/**
	 * @return the crimesRegister
	 */
	public Map<Integer, Crime> getCrimesRegister();

	//Method to add a new crime to the city
	public void addCrime(Crime newCrime);

	// Method to get the details for a specific crime, given its ID (start counting at 1), formatted in a way to save it as a line in a csv file
	public String getCsvLineForCrimeID(int crimeID);

	// Method to retrieve all values of a given int column
	public ArrayList<Integer> retrieveAllValuesFromIntColumn(String columnName);

	// Method to retrieve all values of a given String column
	public ArrayList<String> retrieveAllValuesFromStringColumn(String columnName);

	// Method to search the database for crime ids matching an int search value in a given column
	public Vector<Integer> searchCrimesForInt(String column, int searchValue);

	// Method to search the database for crime ids matching a String search query in a given column
	public Vector<Integer> searchCrimesForString(String column,
			String searchQuery);

	// Helper method to save the details for crimes given in an array list to a csv file
	public void saveCrimesArrayListToCsv(ArrayList<Crime> crimesList,
			String fileName);

	// Helper method to save the details for crimes of which a vector of their IDs is given to a csv file
	public void saveCrimesIdVectorToCsv(Vector<Integer> idVector,
			String fileName);

	// Method to get the details for all crimes of the city; saves these details to a csv file
	public void saveDetailsForAllCrimesToFile(String fileName);

	// Method to get the details for all crimes in a given district; saves these details to a csv file.
	public void saveDetailsForCrimesInCity(String city, String fileName);

	// Method to get the details for all crimes of a given category; saves these details to a csv file.
	public void saveDetailsForCrimesForCategory(int ucrNcicCodeValue,
			String fileName);

	// Method to get the details for all crimes in a given year; saves these details to a csv file.
	public void saveDetailsForCrimesInYear(int year, String fileName);

	// Method to get the details for all crimes in a given month (in any year); saves these details to a csv.
	public void saveDetailsForCrimesInMonth(int month, String fileName);

	// Method to get the total count of crimes in this city
	public Integer getTotalCrimeCount();

	// Method to get the frequency count for each unique value of an int array list passed
	public Map<Integer, Integer> getCountPerValueIntList(
			ArrayList<Integer> inputIntegerList);

	// Method to get the frequency count for each unique value of a String array list passed
	public Map<String, Integer> getCountPerValueStringList(
			ArrayList<String> inputStringList);

	// Method to get the 3 values with the most crimes in the given int column
	public Integer[] getMost3IntValues(String columnName);

	// Method to get the 3 values with the most crimes in the given String column
	public String[] getMost3StringValues(String columnName);

	// Method to get the 3 values with the least crimes in the given int column
	public Integer[] getLeast3IntValues(String columnName);

	// Method to get the 3 values with the least crimes in the given String column
	public String[] getLeast3StringValues(String columnName);

	// Helper Method to get the mean of number of crimes per unique value in a given int column
	public Double getAverageCrimesPerIntValue(String columnName);

	// Helper Method to get the mean of number of crimes per unique value in a given String column
	public Double getAverageCrimesPerStringValue(String columnName);

	// Helper Method to get the median of number of crimes per unique value in a given int column
	public Double getMedianCrimesForIntValue(String columnName);

	// Helper Method to get the median of number of crimes per unique value in a given String column
	public Double getMedianCrimesForStringValue(String columnName);

	// Template method to get some summary crime statistics for the DatabaseCategory
	public String getCrimesSummary();

}