Endpoints

GET

/getWeatherOnRoute
Parameters: cordsList - Array/list of cords(float lat, float lon) with at least a change in the hundredths place and rounded to the hundredths place
Gets the weather data on all of the points in the array and processes the data.
Returns: Processed data

/getWeatherOnSpot
Parameters: cordsList - Array/list of cords(float lat, float lon) but only uses the first value rounded to the hundredths place
Gets weather data on the spot and processes the data.
Returns: Processed data

POST

PUT

DELETE
