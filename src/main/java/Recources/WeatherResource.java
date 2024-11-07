package Recources;

import Clients.OpenWeatherClient;
import Entities.Cords;
import Entities.WeatherData;
import hidden.ApiKeys;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import requests.GetWeatherRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class WeatherResource {


    @Inject
    ApiKeys apiKeys;

    @Inject
    @RestClient
    OpenWeatherClient openWeatherClient;


    public Object getWeatherOnRoute(GetWeatherRequest request) {
        List<WeatherData> allWeatherData = new ArrayList<>();
        request.cordsList.forEach(cords -> {
            allWeatherData.add(openWeatherClient.getWeather(cords.lat,cords.lon,apiKeys.openWeatherApiKey));
        });

        return null;
    }

    public Object getWeatherOnSpot(Cords first){
        return openWeatherClient.getWeather(first.lat,first.lon,apiKeys.openWeatherApiKey);
    }
}
