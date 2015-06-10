
# coding: utf-8

# In[55]:

from __future__ import division  # ensures that default division is real number division
import numpy as np
import pandas as pd
from pandas import *


# In[56]:

#pull SacramentocrimeJanuary2006.csv with data and put it into a DataFrame
df_sac_jan_2006 = pd.read_csv('SacramentocrimeJanuary2006.csv', header=0)


# In[57]:

# print some entries to get a feel for the data
print df_sac_jan_2006.head()


# In[58]:

print df_sac_jan_2006.iloc[7583]


# In[59]:

print df_sac_jan_2006.iloc[0]


# In[60]:

print df_sac_jan_2006.shape # we have 7584 crimes recorded in the database


# In[61]:

# now, we change and add random new entries with more months, years and cities
cdatetimes = np.asarray(df_sac_jan_2006.cdatetime)
addresses = np.asarray(df_sac_jan_2006.address)
districts = np.asarray(df_sac_jan_2006.district)
beats = np.asarray(df_sac_jan_2006.beat)
grids = np.asarray(df_sac_jan_2006.grid)
crimedescrs = np.asarray(df_sac_jan_2006.crimedescr)
ucr_ncic_codes = np.asarray(df_sac_jan_2006.ucr_ncic_code)
latitudes = np.asarray(df_sac_jan_2006.latitude)
longitudes = np.asarray(df_sac_jan_2006.longitude)


# In[62]:

i = 0
for beat in beats:
    x = beat.strip()
    if x == "":
        x = "2B"
    beats[i] = x
    i += 1


# In[63]:

#these are the categories we want to expand to
months = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"]
days = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "15", "18", "20", "25", "26", "27"]
years = ["05", "06", "07", "08", "09", "10", "11", "12"]
times = ["0:00", "1:00", "2:30", "3:45", "8:00", "9:15", "11:00", "13:30", "15:00", "16:45", "18:30", "20:15"]
new_cities = ["New York", "Boston", "Chicago", "Los Angeles"]


# In[64]:

#create a new city column 
cities = []
for i in range(0, 7584):
    cities.append("Sacramento")


# In[65]:

#first change the months, years and times of a majority of random entries in Sacramento
for x in range(0, 5000):
    i = np.random.randint(0, 7584)
    day = np.random.choice(days)
    month = np.random.choice(months)
    year = np.random.choice(years)
    time = np.random.choice(times)
    cdatetimes[i] =  month + "/" + day + "/" + year + " " + time


# In[66]:

# now create 7000 new entries for New York, Boston, Los Angeles and Chicago each
new_cdatetimes = []
new_addresses = []
new_districts = []
new_beats = []
new_grids = []
new_crimedescrs = []
new_ucr_ncic_codes = []
new_cities_array = []
for new_city in new_cities:
    for x in range(0, 7000):
        day = np.random.choice(days)
        month = np.random.choice(months)
        year = np.random.choice(years)
        time = np.random.choice(times)
        new_cdatetime = month + "/" + day + "/" + year + " " + time
        new_cdatetimes.append(new_cdatetime)
        new_addresses.append(addresses[np.random.randint(0, 7584)])
        index_loc = np.random.randint(0, 7584)
        new_districts.append(districts[index_loc])
        new_beats.append(beats[index_loc])
        new_grids.append(grids[index_loc])
        index_crime = np.random.randint(0, 7584)
        new_crimedescrs.append(crimedescrs[index_crime])
        new_ucr_ncic_codes.append(ucr_ncic_codes[index_crime])
        new_cities_array.append(new_city)


# In[67]:

cdatetimes = np.concatenate((cdatetimes, new_cdatetimes))
addresses = np.concatenate((addresses, new_addresses))
districts = np.concatenate((districts, new_districts))
beats = np.concatenate((beats, new_beats))
grids = np.concatenate((grids, new_grids))
crimedescrs = np.concatenate((crimedescrs, new_crimedescrs))
ucr_ncic_codes = np.concatenate((ucr_ncic_codes, new_ucr_ncic_codes))
cities = np.concatenate((cities, new_cities_array))


# In[68]:

print districts[10000]
print ucr_ncic_codes[10000]


# In[69]:

# and finally also create the longitude and latitude columns
# New York
new_longitudes = []
new_latitudes = []
for i in range(0, 7000):
    long_parts = str(longitudes[i]).split(".")
    new_longitude = "-40." + long_parts[1]
    new_longitudes.append(new_longitude)
    lat_parts = str(latitudes[i]).split(".")
    new_latitude = "74." + lat_parts[1]
    new_latitudes.append(new_latitude)
    
# Boston
for i in range(0, 7000):
    long_parts = str(longitudes[i]).split(".")
    new_longitude = "-42." + long_parts[1]
    new_longitudes.append(new_longitude)
    lat_parts = str(latitudes[i]).split(".")
    new_latitude = "71." + lat_parts[1]
    new_latitudes.append(new_latitude)
    
# Chicago
for i in range(0, 7000):
    long_parts = str(longitudes[i]).split(".")
    new_longitude = "-41." + long_parts[1]
    new_longitudes.append(new_longitude)
    lat_parts = str(latitudes[i]).split(".")
    new_latitude = "87." + lat_parts[1]
    new_latitudes.append(new_latitude)
    
# LA
for i in range(0, 7000):
    long_parts = str(longitudes[i]).split(".")
    new_longitude = "-34." + long_parts[1]
    new_longitudes.append(new_longitude)
    lat_parts = str(latitudes[i]).split(".")
    new_latitude = "118." + lat_parts[1]
    new_latitudes.append(new_latitude)


# In[70]:

latitudes = np.concatenate((latitudes, new_latitudes))
longitudes = np.concatenate((longitudes, new_longitudes))


# In[71]:

print longitudes.shape
print cities.shape
print addresses.shape


# In[72]:

#now tie together results in a new dataframe
new_df = DataFrame()
new_df['city'] = Series(cities)
new_df['cdatetime'] = Series(cdatetimes)
new_df['address'] = Series(addresses)
new_df['district'] = Series(districts)
new_df['beat'] = Series(beats)
new_df['grid'] = Series(grids)
new_df['crimedescr'] = Series(crimedescrs)
new_df['ucr_ncic_code'] = Series(ucr_ncic_codes)
new_df['longitutde'] = Series(longitudes)
new_df['latitude'] = Series(latitudes)


# In[73]:

print new_df.shape


# In[74]:

print new_df.head()


# In[75]:

print new_df.iloc[8000]


# In[76]:

N = new_df.shape[0]
print N


# In[77]:

new_df = new_df.ix[np.random.choice(new_df.index, 1500)] #only pick 1500 random crimes


# In[78]:

new_df.index = range(1500)


# In[79]:

print new_df.shape


# In[80]:

#save to csv
new_df.to_csv("crime_database_final.csv")


# In[ ]:



