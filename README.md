# Endpoints

## GET

### /getWeatherOnRoute

#### Parameters:
routes - list of routes 

geocodingResult - google maps api gives it but wont be used

timeOffset - time offset in seconds. -1 for no offset

##### Gets the weather data on the closest coordinate rounded to the hundredths for each leg at the closest time available. Can be given an offset min(1-3599) hour(3600-86399) day(86400-604800).

#### Returns:
Processed data

### /getWeatherOnSpot

#### Parameters:
cords - Coordinates (float lat, float lon) rounded to the hundredths place

timeOffset - time offset in seconds. -1 for hourly data for the next 48 hours

##### Gets weather data on the spot and processes the data. Can be given an offset min(1-3599) hour(3600-86399) day(86400-604800). If its from 1-3599 then it will just return the current time weather

#### Returns: 
Weather data for current time/hour/day. If time offset = -1, gives hourly data for the next 48 hours.

## POST

## PUT

## DELETE
