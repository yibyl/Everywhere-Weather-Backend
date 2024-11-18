package Resources;

import Clients.OpenWeatherClient;
import DTOs.DailyWeatherDTO;
import DTOs.HourlyWeatherDTO;
import DTOs.MinutelyWeatherDTO;
import DTOs.RouteWeatherDTO;
import Entities.Cords;
import Responses.GoogleRoutesResponse;
import Responses.WeatherDataResponse;
import hidden.ApiKeys;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import requests.GetSpotWeatherRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class WeatherResource {


    @Inject
    ApiKeys apiKeys;

    @Inject
    @RestClient
    OpenWeatherClient openWeatherClient;


    public List<RouteWeatherDTO> getWeatherOnRoute(GoogleRoutesResponse request, int timeOffset) {
        List<RouteWeatherDTO> routesWeather = new ArrayList<>();
        AtomicBoolean fistLeg = new AtomicBoolean(true);
        request.routes.forEach(route -> {
            fistLeg.set(true);
            AtomicInteger totalDuration = new AtomicInteger();
            Map<Cords, MinutelyWeatherDTO> routeWeather = new HashMap<>();
            route.legs.forEach(leg -> {
                if (fistLeg.get()){
                    Cords cords = new Cords((float)leg.startLocation.latLng.latitude, (float)leg.startLocation.latLng.longitude);
                    routeWeather.put(cords,getWeatherOnSpot(cords, findClosestTimeOffset(timeOffset)));
                    fistLeg.set(false);
                }
                try{
                    leg.steps.forEach(step -> {
                        totalDuration.addAndGet(convertTimeString(step.staticDuration));
                        Cords cords = new Cords((float)step.endLocation.latLng.latitude, (float)step.endLocation.latLng.longitude);
                        routeWeather.put(cords,getWeatherOnSpot(cords, findClosestTimeOffset(timeOffset + totalDuration.get())));
                    });
                }catch (Exception e){

                }
            });

            routesWeather.add(new RouteWeatherDTO(route.routeLabels, routeWeather));
        });
        return routesWeather;
    }

    public MinutelyWeatherDTO getWeatherOnSpot(Cords cords, int timeOffset){
        GetSpotWeatherRequest request = new GetSpotWeatherRequest();
        request.cords = cords;
        request.timeOffset = timeOffset;
        WeatherDataResponse weatherData = openWeatherClient.getWeather(request.cords.lat,request.cords.lon,apiKeys.openWeatherApiKey);
        if(request.timeOffset < 3600){
            long currentDtMin = Instant.now().minusSeconds(Instant.now().getEpochSecond() % 60).getEpochSecond();
            MinutelyWeatherDTO weather = new MinutelyWeatherDTO();
            weather.dt = currentDtMin + request.timeOffset;
            weather.temp = weatherData.current.temp;
            weather.feelsLike = weatherData.current.feels_like;
            weather.windSpeed = weatherData.current.wind_speed;
            if(weatherData.current.rain != null)
                weather.rain = weatherData.current.rain.one_hour;
            if(weatherData.current.snow != null)
                weather.snow = weatherData.current.snow.one_hour;
            weather.weatherDescription = weatherData.current.weather.getFirst().description;
            return weather;
        } else{
            long currentDtHour = Instant.now().minusSeconds(Instant.now().getEpochSecond() % 3600).getEpochSecond();
            MinutelyWeatherDTO h = new MinutelyWeatherDTO();
            weatherData.hourly.forEach(hourly -> {
                if (hourly.dt == currentDtHour + request.timeOffset){
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
        }
    }

    private int convertTimeString(String time){
        return Integer.parseInt(time.replace("s",""));
    }

    private int findClosestTimeOffset(int offset){
        if(offset < 3600){
            return offset - offset % 60;
        }else if (offset < 172800) {
            return offset - offset % 3600;
        }else{
            return offset - offset % 86400;
        }

    }

    public Response getWeatherOnSpot(GetSpotWeatherRequest request){
        WeatherDataResponse weatherData = openWeatherClient.getWeather(request.cords.lat,request.cords.lon,apiKeys.openWeatherApiKey);

        if (request.timeOffset == -1){
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
            return Response.ok(hourlyData).build();
        }else{
            //specific time
            if(request.timeOffset < 3600){
                //minutely
                // gives current because no enough change
                long currentDtMin = Instant.now().minusSeconds(Instant.now().getEpochSecond() % 60).getEpochSecond();
                MinutelyWeatherDTO weather = new MinutelyWeatherDTO();
                weather.dt = currentDtMin + request.timeOffset;
                weather.temp = weatherData.current.temp;
                weather.feelsLike = weatherData.current.feels_like;
                weather.windSpeed = weatherData.current.wind_speed;
                if(weatherData.current.rain != null)
                    weather.rain = weatherData.current.rain.one_hour;
                if(weatherData.current.snow != null)
                    weather.snow = weatherData.current.snow.one_hour;
                weather.weatherDescription = weatherData.current.weather.getFirst().description;
                return Response.ok(weather).build();
            } else if (request.timeOffset < 172800) {
                //hourly
                long currentDtHour = Instant.now().minusSeconds(Instant.now().getEpochSecond() % 3600).getEpochSecond();
                HourlyWeatherDTO h = new HourlyWeatherDTO();
                weatherData.hourly.forEach(hourly -> {
                    if (hourly.dt == currentDtHour + request.timeOffset){
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
                return Response.ok(h).build();
            }else{
                //daily
                System.out.println("daily");
                long currentDtDay = Instant.now().minusSeconds(Instant.now().getEpochSecond() % 86400 + 25200).getEpochSecond();
                DailyWeatherDTO d = new DailyWeatherDTO();
                weatherData.daily.forEach(daily -> {
                    if (daily.dt == currentDtDay + request.timeOffset){
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
                return Response.ok(d).build();
            }

        }
    }
}


