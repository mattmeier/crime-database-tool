package camelinactionConsumer;

public class Crime {

	protected int uniqueID;
	protected String city;
	protected String cDateTime;
	protected String address;
	protected int district;
	protected String beat;
	protected int grid;
	protected String crimeDescr;
	protected int ucrNcicCode;
	protected double latitude;
	protected double longitude;


	// Constructor:
	public Crime(int uniqueID, String city, String cDateTime, String address, int district, String beat, int grid, String crimeDescr, int ucrNcicCode, double latitude, double longitude) {
		this.uniqueID = uniqueID;
		this.city = city;
		this.cDateTime = cDateTime;
		this.address = address; 
		this.district = district;
		this.beat = beat; 
		this.grid = grid;
		this.crimeDescr = crimeDescr;
		this.ucrNcicCode = ucrNcicCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	// Methods:
	public final Integer getID() {
		return this.uniqueID;
	}

	public final String getCity() {
		return this.city;
	}

	public final Integer getDistrict() {
		return this.district;
	}

	public final String getBeat() {
		return this.beat;
	}

	public final Integer getGrid() {
		return this.grid;
	}

	public final Integer getYear() {
		// extract year from cDateTime String
		String[] parts = this.cDateTime.split("/");
		String yearString = parts[2].split("\\s+")[0];
		int year = Integer.parseInt("20"+yearString); // add 20 to make it 2006, 2007 etc. instead of 06, 07 - we assume the database only contains data after 2000
		return year;
	}

	public final Integer getMonth() {
		// extract month from cDateTime String
		String[] parts = this.cDateTime.split("/");
		String monthString = parts[0];
		int month = Integer.parseInt(monthString);
		return month;
	}

	public final Integer getCategory() {
		return this.ucrNcicCode;
	}

	// method to get a specific string attribute
	public final String getStringAttribute(String attributeName) {
		if (attributeName.equals("city")) 
			return getCity();
		else if (attributeName.equals("beat")) 
			return getBeat();
		else {
			System.out.println("Error: attribute not found");
			return null;
		}
	}

	// method to get a specific int attribute
	public final Integer getIntAttribute(String attributeName) {
		if (attributeName.equals("uniqueID")) 
			return getID();
		else if (attributeName.equals("district")) 
			return getDistrict();
		else if (attributeName.equals("grid")) 
			return getGrid();
		else if (attributeName.equals("year")) 
			return getYear();
		else if (attributeName.equals("month")) 
			return getMonth();
		else if (attributeName.equals("ucrNcicCode")) 
			return getCategory();
		else {
			System.out.println("Error: attribute not found");
			return null;
		}
	}

	// Get a String with the Details for this crime, normally taken for printing out
	public final String getDetails( ) {
		String details = String.format("ID: %s\nTime: %s\nCity: %s\nAddress: %s\nDistrict: %d\nBeat: %s\nGrid: %d\nCrime Description: %s\nUCR NCIC Code: %d\nLatitude: %f\nLongitude: %f\n\n", this.uniqueID, this.cDateTime, this.city, this.address, this.district, this.beat, this.grid, this.crimeDescr, this.ucrNcicCode, this.latitude, this.longitude);
		return details;
	};

	// Get a String with the details for this crime to save it to a csv (without column names)
	public final String getCsvLine( ) {
		String line = String.format("%d,%s,%s,%s,%d,%s,%d,%s,%d,%f,%f", this.uniqueID, this.cDateTime, this.city, this.address, this.district, this.beat, this.grid, this.crimeDescr, this.ucrNcicCode, this.latitude, this.longitude);
		return line;
	};


}


