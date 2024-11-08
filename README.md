# Endpoints

## GET

### /getWeatherOnRoute

#### Parameters:
routes - list of routes 

geocodingResult - google maps api gives it but wont be used

timeOffset - time offset in seconds.

##### Gets the weather data on the closest coordinate rounded to the hundredths for each leg at the closest time available. Can be given an offset min(0-3599) hour(3600-172799) day(172800-604800).

#### Returns:
Returns weather data for every step at a time offset and the time it takes to get there.

Ex:

[
{
"routeLabel": [
"FASTEST_ROUTE"
],
"route": {
"lat=37.784, lon=-122.4094": {
"dt": 1731058620,
"temp": 285.28,
"feelsLike": 283.98,
"windSpeed": 1.54,
"rain": 0.0,
"snow": 0.0,
"weatherDescription": "clear sky",
"precipitaionChance": 0.0
},
"lat=37.7891, lon=-122.4075": {
"dt": 1731058920,
"temp": 285.21,
"feelsLike": 283.91,
"windSpeed": 1.54,
"rain": 0.0,
"snow": 0.0,
"weatherDescription": "clear sky",
"precipitaionChance": 0.0
},
"lat=37.7749, lon=-122.4194": {
"dt": 1731058320,
"temp": 285.25,
"feelsLike": 283.95,
"windSpeed": 1.54,
"rain": 0.0,
"snow": 0.0,
"weatherDescription": "clear sky",
"precipitaionChance": 0.0
},
"lat=37.8044, lon=-122.2712": {
"dt": 1731059520,
"temp": 285.32,
"feelsLike": 283.98,
"windSpeed": 2.24,
"rain": 0.0,
"snow": 0.0,
"weatherDescription": "clear sky",
"precipitaionChance": 0.0
}
}
},
{
"routeLabel": [
"SCENIC_ROUTE"
],
"route": {
"lat=37.7812, lon=-122.4025": {
"dt": 1731058740,
"temp": 285.33,
"feelsLike": 284.04,
"windSpeed": 1.54,
"rain": 0.0,
"snow": 0.0,
"weatherDescription": "clear sky",
"precipitaionChance": 0.0
},
"lat=37.7749, lon=-122.4194": {
"dt": 1731058320,
"temp": 285.25,
"feelsLike": 283.95,
"windSpeed": 1.54,
"rain": 0.0,
"snow": 0.0,
"weatherDescription": "clear sky",
"precipitaionChance": 0.0
},
"lat=37.7795, lon=-122.41": {
"dt": 1731058500,
"temp": 285.28,
"feelsLike": 283.98,
"windSpeed": 1.54,
"rain": 0.0,
"snow": 0.0,
"weatherDescription": "clear sky",
"precipitaionChance": 0.0
},
"lat=37.8044, lon=-122.2712": {
"dt": 1731059640,
"temp": 285.32,
"feelsLike": 283.98,
"windSpeed": 2.24,
"rain": 0.0,
"snow": 0.0,
"weatherDescription": "clear sky",
"precipitaionChance": 0.0
}
}
}
]

### /getWeatherOnSpot

#### Parameters:
cords - Coordinates (float lat, float lon) rounded to the hundredths place

timeOffset - time offset in seconds. -1 for hourly data for the next 48 hours

##### Gets weather data on the spot and processes the data. Can be given an offset min(0-3599) hour(3600-172799) day(172800-604800). If its from 1-3599 then it will just return the current time weather

#### Returns: 
Weather data for current time/hour/day. If time offset = -1, gives hourly data for the next 48 hours.

Ex:

timeOffset = -1

[
{
"dt": 1731056400,
"temp": 278.49,
"feelsLike": 275.24,
"windSpeed": 4.34,
"precipitaionChance": 0.0,
"weatherDescription": "clear sky",
"rain": 0.0,
"snow": 0.0
},
{
"dt": 1731060000,
"temp": 278.52,
"feelsLike": 275.47,
"windSpeed": 3.99,
"precipitaionChance": 0.0,
"weatherDescription": "clear sky",
"rain": 0.0,
"snow": 0.0
},
...]

0 <= timeOffset < 3600

{
"dt": 1731060840,
"temp": 278.61,
"feelsLike": 276.54,
"windSpeed": 2.57,
"rain": 0.0,
"snow": 0.0,
"weatherDescription": "clear sky",
"precipitaionChance": 0.0
}

3600 <= timeOffset < 172799

{
"dt": 1731099600,
"temp": 285.79,
"feelsLike": 284.36,
"windSpeed": 2.55,
"precipitaionChance": 0.0,
"weatherDescription": "broken clouds",
"rain": 0.0,
"snow": 0.0
}

172800 <= timeOffset < 604800

{
"dt": 1731344400,
"tempMorn": 279.9,
"tempDay": 286.7,
"tempEve": 281.26,
"tempNight": 278.24,
"tempMin": 278.24,
"tempMax": 286.7,
"feelsLikeMorn": 277.27,
"feelsLikeDay": 285.96,
"feelsLikeEve": 278.56,
"feelsLikeNight": 274.87,
"windSpeed": 5.15,
"precipitaionChance": 0.0,
"summary": "Expect a day of partly cloudy with clear spells",
"rain": 0.0,
"snow": 0.0,
"weatherDescription": "overcast clouds"
}

## POST

## PUT

## DELETE
