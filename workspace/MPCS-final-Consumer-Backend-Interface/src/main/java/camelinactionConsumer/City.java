package camelinactionConsumer;
import java.util.*;

public class City extends DatabaseType {

	// CONSTRUCTOR:
	public City(String cityName) { 
		databaseType = "city";
		name = cityName;
		crimesRegister = new HashMap <Integer, Crime>();
	}

	// METHODS:
	
	// overriding abstract method to return database type
	@Override
	public String GetDatabaseType( ) {
		return "city";
	}

	@Override
	public Vector<Integer> getCrimeIDsInCity(String cityName) {
		Vector<Integer> ids = new Vector<Integer>();
		if (cityName.equals(name)) {
			ids = new Vector<Integer>(getCrimesRegister().keySet());
		}
		return ids;
	} 

	
	// method to get the IDs for all crimes in a certain district
	public Vector<Integer> getCrimeIDsInDistrict(int districtNumber) {
		return searchCrimesForInt("district", districtNumber);
	} 

	
	// method to get the IDs for all crimes in a certain beat
	public Vector<Integer> getCrimeIDsInBeat(String beatString) {
		return searchCrimesForString("beat", beatString);
	} 

	
	// method to get the IDs for all crimes in a certain grid
	public Vector<Integer> getCrimeIDsInGrid(int gridNumber) {
		return searchCrimesForInt("grid", gridNumber);
	} 

	
	// method to get the IDs for all crimes of a certain category, given by ucrNcicCodeValue
	@Override
	public Vector<Integer> getCrimeIDsForCategory(int ucrNcicCodeValue) {
		return searchCrimesForInt("ucrNcicCode", ucrNcicCodeValue);
	} 

	
	// Method to get the IDs for all crimes of a certain year
	@Override
	public Vector<Integer> getCrimeIDsForYear(int year) {
		return searchCrimesForInt("year", year);
	} 

	
	// Method to get the IDs for all crimes of a certain month (in any year)
	@Override
	public Vector<Integer> getCrimeIDsForMonth(int month) {
		return searchCrimesForInt("month", month);
	} 

	// Method to get the details for all crimes in a given district ; saves these details to a csv file.
	public final void saveDetailsForCrimesInDistrict(int districtNumber, String fileName ) {
		Vector<Integer> idVector = getCrimeIDsInDistrict(districtNumber);
		saveCrimesIdVectorToCsv( idVector, fileName );
	}

	// Method to get the details for all crimes in a given district ; saves these details to a csv file.
	public final void saveDetailsForCrimesInBeat(String beat, String fileName ) {
		Vector<Integer> idVector = getCrimeIDsInBeat(beat);
		saveCrimesIdVectorToCsv( idVector, fileName );
	}

	// Method to get the details for all crimes in a given grid; saves these details to a csv file.
	public final void saveDetailsForCrimesInGrid(int gridNumber, String fileName ) {
		Vector<Integer> idVector = getCrimeIDsInGrid(gridNumber);
		saveCrimesIdVectorToCsv( idVector, fileName );
	}

	// Method to get the count of crimes for a given city
	@Override
	public Integer getCrimeCountForCity(String city) {
		if (city.equals(name)) {
			return getTotalCrimeCount();
		}
		else 
			return 0;
	} 

	// Method to get the count of crimes for a given district
	public Integer getCrimeCountForDistrict(int district) {
		Vector<Integer> idVector = getCrimeIDsInDistrict(district); 
		return idVector.size();
	} 

	// Method to get the count of crimes for a given beat
	public Integer getCrimeCountForBeat(String beat) {
		Vector<Integer> idVector = getCrimeIDsInBeat(beat); 
		return idVector.size();
	} 

	// Method to get the count of crimes for a given grid
	public Integer getCrimeCountForGrid(int grid) {
		Vector<Integer> idVector = getCrimeIDsInGrid(grid); 
		return idVector.size();
	} 

	
	// Method to get the count of crimes for a given category
	@Override
	public Integer getCrimeCountForCategory(int ucrNcicCodeValue) {
		Vector<Integer> idVector = getCrimeIDsForCategory(ucrNcicCodeValue); 
		return idVector.size();
	} 

	
	// Method to get the count of crimes for a given year
	@Override
	public Integer getCrimeCountForYear(int year) {
		Vector<Integer> idVector = getCrimeIDsForYear(year);
		return idVector.size();
	} 

	
	// Method to get the count of crimes for a given month
	@Override
	public Integer getCrimeCountForMonth(int month) {
		Vector<Integer> idVector = getCrimeIDsForMonth(month);
		return idVector.size();
	} 

	@Override
	public  String[] getMost3Cities() {
		String[] array = {name, "NaN", "NaN"};
		return array;
	}

	
	// Method to get the 3 districts with the most crimes
	public Integer[] getMost3Districts() {
		return getMost3IntValues("district");
	} 

	
	// Method to get the 3 beats with the most crimes
	public String[] getMost3Beats() {
		return getMost3StringValues("beat");
	} 

	
	// Method to get the 3 grids with the most crimes
	public Integer[] getMost3Grids() {
		return getMost3IntValues("grid");
	} 

	
	// Method to get the 3 crime categories with the most crimes
	@Override
	public Integer[] getMost3Categories() {
		return getMost3IntValues("ucrNcicCode");
	} 

	
	// Method to get the 3 years with the most crimes
	@Override
	public Integer[] getMost3Years() {
		return getMost3IntValues("year");
	} 

	
	// Method to get the 3 months with the most crimes
	@Override
	public  Integer[] getMost3Months() {
		return getMost3IntValues("month");
	} 

	@Override
	public String[] getLeast3Cities() {
		String[] array = {name, "NaN", "NaN"};
		return array;
	}

	
	// Method to get the 3 districts with the least crimes
	public Integer[] getLeast3Districts() {
		return getLeast3IntValues("district");
	} 

	
	// Method to get the 3 beats with the least crimes
	public String[] getLeast3Beats() {
		return getLeast3StringValues("beat");
	} 

	
	// Method to get the 3 grids with the least crimes
	public Integer[] getLeast3Grids() {
		return getLeast3IntValues("grid");
	} 

	
	// Method to get the 3 crime categories with the least crimes
	@Override
	public Integer[] getLeast3Categories() {
		return getLeast3IntValues("ucrNcicCode");
	} 

	
	// Method to get the 3 years with the least crimes
	@Override
	public Integer[] getLeast3Years() {
		return getLeast3IntValues("year");
	} 

	
	// Method to get the 3 months with the least crimes
	@Override
	public Integer[] getLeast3Months() {
		return getLeast3IntValues("month");
	} 

	// TO DO: Not needed here!
	
	// Method to get the average number of crimes per city
	@Override
	public Double getAveragePerCity() {
		return (double) 1.0;
	}

	
	// Method to get the average number of crimes per district
	public Double getAveragePerDistrict() {
		return getAverageCrimesPerIntValue("district");
	}

	
	// Method to get the average number of crimes per beat
	public Double getAveragePerBeat() {
		return getAverageCrimesPerStringValue("beat");
	}

	
	// Method to get the average number of crimes per grid
	public Double getAveragePerGrid() {
		return getAverageCrimesPerIntValue("grid");
	}

	
	// Method to get the average number of crimes per category
	@Override
	public Double getAveragePerCategory() {
		return getAverageCrimesPerIntValue("ucrNcicCode");
	}

	
	// Method to get the average number of crimes per year
	@Override
	public Double getAveragePerYear() {
		return getAverageCrimesPerIntValue("year");
	}

	
	// Method to get the average number of crimes per month
	@Override
	public Double getAveragePerMonth() {
		return getAverageCrimesPerIntValue("month");
	}

	// Method to get the median number of crimes per city
	@Override
	public Double getMedianPerCity() {
		return (double) 1.0;
	}

	
	// Method to get the median number of crimes per district
	public Double getMedianPerDistrict() {
		return getMedianCrimesForIntValue("district");
	}

	
	// Method to get the median number of crimes per beat
	public Double getMedianPerBeat() {
		return getMedianCrimesForStringValue("beat");
	}

	
	// Method to get the median number of crimes per grid
	public Double getMedianPerGrid() {
		return getMedianCrimesForIntValue("grid");
	}

	
	// Method to get the median number of crimes per category
	@Override
	public Double getMedianPerCategory() {
		return getMedianCrimesForIntValue("ucrNcicCode");
	}

	
	// Method to get the median number of crimes per year
	@Override
	public Double getMedianPerYear() {
		return getMedianCrimesForIntValue("year");
	}

	
	// Method to get the median number of crimes per month
	@Override
	public Double getMedianPerMonth() {
		return getMedianCrimesForIntValue("month");
	}

	// Template method to get summary crime statistics for the city, including with statistics for districts, beats and grids
	public final String getDetailedCrimesSummary() {
		// Build up our String with the summary and statistics
		String summary = String.format("Summary for database of city %s:\n", name);

    	// List count
		summary = summary + String.format("Overall count of crimes: %s\n", getTotalCrimeCount());

		// List top and least 3
		Integer[] districts = getMost3Districts();
		summary = summary + String.format("3 districts with most crimes: %d, %d, %d\n", districts[0], districts[1], districts[2]);
		districts = getLeast3Districts();
		summary = summary + String.format("3 districts with least crimes: %d, %d, %d\n", districts[0], districts[1], districts[2]);

		String[] beats = getMost3Beats();			
		summary = summary + String.format("3 beats with most crimes: %s, %s, %s\n", beats[0], beats[1], beats[2]);
		beats = getLeast3Beats();
		summary = summary + String.format("3 beats with least crimes: %s, %s, %s\n", beats[0], beats[1], beats[2]);

		Integer[] grids = getMost3Grids();			
		summary = summary + String.format("3 grids with most crimes: %d, %d, %d\n", grids[0], grids[1], grids[2]);
		grids = getLeast3Grids();
		summary = summary + String.format("3 grids with least crimes: %d, %d, %d\n", grids[0], grids[1], grids[2]);

		Integer[] years = getMost3Years();			
		summary = summary + String.format("3 years with most crimes: %d, %d, %d\n", years[0], years[1], years[2]);
		years = getLeast3Years();
		summary = summary + String.format("3 years with least crimes: %d, %d, %d\n", years[0], years[1], years[2]);

		Integer[] months = getMost3Months();			
		summary = summary + String.format("3 months with most crimes: %d, %d, %d\n", months[0], months[1], months[2]);
		months = getLeast3Months();
		summary = summary + String.format("3 months with least crimes: %d, %d, %d\n", months[0], months[1], months[2]);

		Integer[] categories = getMost3Categories();			
		summary = summary + String.format("3 categories with most crimes: %d, %d, %d\n", categories[0], categories[1], categories[2]);
		categories = getLeast3Categories();
		summary = summary + String.format("3 categories with least crimes: %d, %d, %d\n", categories[0], categories[1], categories[2]);

	    // Statistics:
	    // Average of crimes per district
	    Double average = getAveragePerDistrict();			
		summary = summary + String.format("Mean crimes per district: %f\n", average);
		// Median of crimes per district
		Double median = getMedianPerDistrict();			
		summary = summary + String.format("Median crimes per district: %f\n", median);

	    // Average of crimes per beat
	    average = getAveragePerBeat();			
		summary = summary + String.format("Mean crimes per beat: %f\n", average);
		// Median of crimes per beat
		median = getMedianPerBeat();			
		summary = summary + String.format("Median crimes per beat: %f\n", median);

	    // Average of crimes per grid
	    average = getAveragePerGrid();			
		summary = summary + String.format("Mean crimes per grid: %f\n", average);
		// Median of crimes per grid
		median = getMedianPerGrid();			
		summary = summary + String.format("Median crimes per grid: %f\n", median);

	    // Average of crimes per year
	    average = getAveragePerYear();			
		summary = summary + String.format("Mean crimes per year: %f\n", average);
		// Median of crimes per year
		median = getMedianPerYear();			
		summary = summary + String.format("Median crimes per year: %f\n", median);

		// Average of crimes per month
	    average = getAveragePerMonth();			
		summary = summary + String.format("Mean crimes per month (independent of year): %f\n", average);
		// Median of crimes per month
		median = getMedianPerMonth();			
		summary = summary + String.format("Median crimes per month (independent of year): %f\n", median);

	    // Average of crimes per crime category
	    average = getAveragePerCategory();			
		summary = summary + String.format("Mean crimes per crime category: %f\n", average);
		// Median of crimes per crime category
		median = getMedianPerCategory();			
		summary = summary + String.format("Median crimes per crime category: %f\n", median);
		summary = summary + "\n\n"; 
		return summary;

	}

}
