package Recources;

import Clients.OpenWeatherClient;
import DTOs.DailyWeatherDTO;
import DTOs.HourlyWeatherDTO;
import DTOs.MinutelyWeatherDTO;
import Entities.Cords;
import Entities.GoogleRoutesResponse;
import Entities.WeatherData;
import hidden.ApiKeys;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import requests.GetRouteWeatherRequest;
import requests.GetSpotWeatherRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class WeatherResource {


    @Inject
    ApiKeys apiKeys;

    @Inject
    @RestClient
    OpenWeatherClient openWeatherClient;


    public Object getWeatherOnRoute(GoogleRoutesResponse request) {


        return null;
    }

    public Object getWeatherOnSpot(GetSpotWeatherRequest request){
        WeatherData weatherData = openWeatherClient.getWeather(request.cords.lat,request.cords.lon,apiKeys.openWeatherApiKey);

        if (request.timeOffest == -1){
            //hourly data
            List<HourlyWeatherDTO> hourlyData = new ArrayList<>();
            weatherData.hourly.forEach(hourly -> {
                HourlyWeatherDTO h = new HourlyWeatherDTO();
                h.dt = hourly.dt;
                h.temp = hourly.temp;
                h.feelsLike = hourly.feels_like;
                h.windSpeed = hourly.wind_speed;
                h.precipitaionChance = hourly.pop;
                h.weatherDescription = hourly.weather.getFirst().description;
                if(hourly.rain != null)
                    h.rain = hourly.rain.one_hour;
                if(hourly.snow != null)
                    h.snow = hourly.snow.one_hour;
                hourlyData.add(h);
            });
            return hourlyData;
        }else{
            //specific time
            if(request.timeOffest < 3600){
                //minutely
                // gives current because no enough change
                long currentDtMin = Instant.now().minusSeconds(Instant.now().getEpochSecond() % 60).getEpochSecond();
                MinutelyWeatherDTO weather = new MinutelyWeatherDTO();
                weather.dt = currentDtMin + request.timeOffest;
                weather.temp = weatherData.current.temp;
                weather.feelsLike = weatherData.current.feels_like;
                weather.windSpeed = weatherData.current.wind_speed;
                if(weatherData.current.rain != null)
                    weather.rain = weatherData.current.rain.one_hour;
                if(weatherData.current.snow != null)
                    weather.snow = weatherData.current.snow.one_hour;
                weather.weatherDescription = weatherData.current.weather.getFirst().description;
                return weather;
            } else if (request.timeOffest < 172800) {
                //hourly
                long currentDtHour = Instant.now().minusSeconds(Instant.now().getEpochSecond() % 3600).getEpochSecond();
                HourlyWeatherDTO h = new HourlyWeatherDTO();
                weatherData.hourly.forEach(hourly -> {
                    if (hourly.dt == currentDtHour + request.timeOffest){
                        h.dt = hourly.dt;
                        h.temp = hourly.temp;
                        h.feelsLike = hourly.feels_like;
                        h.windSpeed = hourly.wind_speed;
                        h.precipitaionChance = hourly.pop;
                        h.weatherDescription = hourly.weather.getFirst().description;
                        if(hourly.rain != null)
                            h.rain = hourly.rain.one_hour;
                        if(hourly.snow != null)
                            h.snow = hourly.snow.one_hour;
                    }

                });
                return h;
            }else{
                //daily
                long currentDtDay = Instant.now().minusSeconds(Instant.now().getEpochSecond() % 86400 + 25200).getEpochSecond();
                DailyWeatherDTO d = new DailyWeatherDTO();
                System.out.println(currentDtDay);
                weatherData.daily.forEach(daily -> {
                    if (daily.dt == currentDtDay + request.timeOffest){
                        d.dt = daily.dt;
                        d.tempMorn = daily.temp.morn;
                        d.tempDay = daily.temp.day;
                        d.tempEve = daily.temp.eve;
                        d.tempNight = daily.temp.night;
                        d.tempMin = daily.temp.min;
                        d.tempMax = daily.temp.max;
                        d.feelsLikeMorn = daily.feels_like.morn;
                        d.feelsLikeDay = daily.feels_like.day;
                        d.feelsLikeEve = daily.feels_like.eve;
                        d.feelsLikeNight = daily.feels_like.night;
                        d.windSpeed = daily.wind_speed;
                        d.precipitaionChance = daily.pop;
                        d.summary = daily.summary;
                        d.rain = daily.rain;
                        d.snow = daily.snow;
                        d.weatherDescription = daily.weather.getFirst().description;

                    }
                });
                return d;
            }

        }
    }
}


