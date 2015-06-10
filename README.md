This project implements a crime database query tool, allowing users to retrieve data from a dataset of crime records. Besides allowing users to pull simply the “raw data” (either all entries or single crimes) from the database, the tool allows users to run specific searches, filter and aggregate results and perform statistical analysis on records. The data retrieval happens at the request of the user, i.e. is not triggered automatically/periodically. The database is based on the Camel ActiveMQ broker and coded in Java. It assumes that the code is run in an Eclipse workspace.

The database source file "crime_database.csv" was created by an own Python script (Data Creator.py) based on the file found here, which had just crime data for Sacramento and January 2006: http://samplecsvs.s3.amazonaws.com/SacramentocrimeJanuary2006.csv
I expanded it to cover the years 2005 to 2011, all 12 months and the 5 cities Sacramento, Chicago, Los Angeles, New York and Boston.
Each crime has the following details:
o	unique ID
o	time/date of crime
o	city
o	address
o	district
o	beat
o	grid
o	crime description
o	NCIC offense code (i.e. an identifier for the category of crime)
o	latitude
o	longitude

My Eclipse workspace consists of 2 folders: MPCS-final-Producer, which contains a ActiveMQ Producer reading in my database_final.csv source file and MPCS-final-Consumer-Backend-Interface, which contains a Consumer reading from the Producer (DataFeedConsumer.java) as well as various Java classes needed to compute my statistics and perform my searches and filterings of the crimes as well as the DatabaseInterface to control the database. I also have included a Test program (DatabaseTest.java) to show the functionality of the database. Moreover, to enhance the usability, I have created a Command line tool which takes in user commands to run the database and queries (DBCommandLineTool.java).

Generally speaking, the database works as follows: an ActiveMQ Producer takes in my data source file, which has got the details for all crimes and then splits it up into single lines/crimes and sends each crime as a message to a data channel JMS queue. Then, the DataFeedConsumer (an ActiveMQ consumer) dequeues all messages from the data channel and sends each message, each containing the details for one crime, to the main database JMS queue. Furthermore, however, each crime gets sent to dynamically created queues per city, year, month and crime category the crime belongs to, as well as saves a csv file per crime in the data outbox folder (again both for the main database an per city, year, month and crime category). The user can run queries and statistical analyses of the crimes stored in these data outbox folders through the Database Interface or the command line tool. 

The functions implemented on the Interface level and in the command line tool of my database are as follows:
- initializeSystem: method to initialize the database system the first time it is ever used. This is necessary to make sure the data gets loaded into the Camel ActiveMQ system. NOTE: only call this once ever, after having done so the system synchronizes and automatically.
- printDetailsCrimeByID <ID>: Method to print out details of a crime, given its ID <ID>.
- downloadAllData <outputFileName>: Method to download all crime data in the database, in a csv file with the given file name <outputFileName>.
- downloadAllDataForCity <city> <outputFileName>: Method to download data of all crimes in a certain city, given its name <city>. The file will be a single csv file with the given file name <outputFileName>.
- downloadAllDataForYear <year> <outputFileName>: Method to download data of all crimes in a certain year, given its year <year> (format is 4 digits, e.g. 2006). The file will be a single csv file with the given file name <outputFileName>.
- downloadAllDataForMonth <month by digit> <outputFileName>: Method to download data of all crimes in a certain month, given its month <month by digit> (e.g. 1 for January, 2 for February etc.). The file will be a single csv file with the given file name <outputFileName>.
- downloadAllDataForCrimeCategory <crimeCode> <outputFileName>: Method to download data of all crimes in a certain crime category, given its crime category code <crimeCode> (e.g. 7000). The file will be a single csv file with the given file name <outputFileName>.
- downloadFilteredDataForCity <cityName> <columnName> <queryValue> <outputFileName>: Method to download data of crimes in a certain city (indicated by <cityName>), filtered based on a search query <queryValue> in a specific column <columnName>. The columnNames to be searched are 'grid', 'district', 'beat', 'ucrNcicCode', 'year' and 'month'. The file will be a single csv file with the given file name. <outputFileName>.
- downloadFilteredDataForYear <year> <columnName> <queryValue> <outputFileName>: Method to download data of crimes in a certain year (indicated by <year>), filtered based on a search query <queryValue> in a specific column <columnName>. The columnNames to be searched are 'city', 'grid', 'district', 'beat', 'ucrNcicCode' and 'month'. The file will be a single csv file with the given file name. <outputFileName>.
- downloadFilteredDataForMonth <month> <columnName> <queryValue> <outputFileName>: Method to download data of crimes in a certain month (indicated by <month>, which is indicated by a number between 1 and 12), filtered based on a search query <queryValue> in a specific column <columnName>. The columnNames to be searched are 'city', 'grid', 'district', 'beat', 'ucrNcicCode' and 'year'. The file will be a single csv file with the given file name. <outputFileName>.
 - downloadFilteredDataForMonth <crimeCode> <columnName> <queryValue> <outputFileName>: Method to download data of crimes in a certain crime category (indicated by <crimeCode>, such as 7000), filtered based on a search query <queryValue> in a specific column <columnName>. The columnNames to be searched are 'city', 'grid', 'district', 'beat', 'year' and 'month'. The file will be a single csv file with the given file name. <outputFileName>.
- printTotalCrimeCount: Method to print out the total count of crimes in the entire database system.
- printSpecificStatisticPerCity <statisticMethod>: Method to print a specific statistic per city, based on one of the two methods <statisticMethod>: 'mean' or 'median' of crimes per city.
- printSpecificStatisticPerYear <statisticMethod>: Method to print a specific statistic per year, based on one of the two methods <statisticMethod>: 'mean' or 'median' of crimes per year.
- printSpecificStatisticPerMonth <statisticMethod>: Method to print a specific statistic per month, based on one of the two methods <statisticMethod>: 'mean' or 'median' of crimes per month.
- printSpecificStatisticPerCrimeCategory <statisticMethod>: Method to print a specific statistic per crimeCategory, based on one of the two methods <statisticMethod>: 'mean' or 'median' of crimes per crime category.
- printOverallSummaryAndStatistics: Method to print summary and statistics for the entire database.
- printAllStatisticsForCity <cityName>: Method to print all statistics for a given city <cityName>.
- printAllStatisticsForYear <year>: Method to print all statistics for a given year <year>.
- printAllStatisticsForMonth <month>: Method to print all statistics for a given month <month> (number indicating month, e.g. 1 for January, 2 for February etc.).
- printAllStatisticsForCrimeCategory <crimeCode>: Method to print all statistics for a given crimecategory with the code <crimeCode> (e.g. 7000).


Below is a list of Design Pattners and EIPs used:
Design Patterns:
- Singleton: DatabaseInterfaceImpl (implements DatabaseInterface) is a singleton, since we only need one Interface to control the database
- Template: used in my abstract DatabaseType class (which implements GenericDatabaseType), in particular in getCrimesSummary() method, which calls various abstract methods. The DatabaseType class has the subclasses MainDatabase (containing all crimes), City (containing crimes of a certain city), Year (containing crimes of a certain year), Month (containing crimes of a certain month) and CrimeCategory (containing crimes of a certain crime category) which override various methods of the abstract DatabaseType class to compute statistics etc. In particular the City class computes more statistics and has more searches/filterings than the other ones, also splitting down by district, crime beat and grid.
- Layers: DatabaseInterfaceImpl is the top level layer (my easiest interface), DatabaseQueryEngineImpl the second one, and my DatabaseType subclasses MainDatabase, City, Year, Month and CrimeCategory are below it in the third one
- Facade: implemented in basically all DatabaseInterfaceImpl methods, most of the DataQueryEngine methods

EIP, amongst others:
- Broker: Camel ActiveMQ for Producer and DataFeedConsumer
- Message: sent from Producer data inbox to jms:queue:MPCS_51050_final_data_channel JMS queue, from DataFeedConsumer to various other queues
- Message-Endpoint: send messages from Producer data inbox to jms:queue:MPCS_51050_final_data_channel JMS queue, from DataFeedConsumer to various other queues
- Splitter: in Producer there is a splitter, splitting up the big csv file with all crimes into separate lines (one line per crime), and then sending each line, i.e. each crime, as a separate message to the main database queue and other city, year, month and crime category queues.
- Routing: in DataFeedConsumer there also is routing. I use a bean and make it behave as a recipient list to send messages to dynamically created queues, based on their city name, year, month or crime category code. This works like a router, but since it is dynamically creating the queues, based on the city names, crime category codes etc., no normal routing with the .choice().when() method could be done, but a bean implementation was chosen.

Note that I chose to run my statistics and queries based on the data stored in the data outbox folders rather than based on the actual JMS queues because this allows users to run as many queries as they want to, whereas otherwise the messages would be dequeued and the user would have to initialise the whole database again. 
Also note that I chose to use noop=false option for the Producer to avoid duplicates in case the system is initialized twice (which should not happen). There is another copy of the crime_database.csv file in the final folder you can copy into the Producer data inbox folder in case you want to test the Producer several times.
Note also that one difficulty was to deal with different sorts of data types in many different categories, i.e. analysis based on String and Integer values. Therefore, I often had to create two helper methods, one for Strings and one for Integers each. 

Note that this project was handed in as an assigment for my "MPCS 51050 OO Architecture: Patterns, Technologies, Implementations" class in Spring 2015 at the University of Chicago.

I also want to point out that the code related to the Camel ActiveMQ elements is partly based on examples from Apache's Camel ActiveMQ online docs and examples from the "Camel in Action" book by Claus Ibsen and Jonathan Anstey.
