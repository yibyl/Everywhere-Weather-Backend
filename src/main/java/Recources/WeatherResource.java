package Recources;

import Entities.Cords;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import requests.GetWeatherRequest;

import java.net.URI;
import java.net.URISyntaxException;


@ApplicationScoped
public class WeatherResource {

    public Object getWeatherOnRoute(GetWeatherRequest request) {

        return null;
    }

    public Object getWeatherOnSpot(Cords first) throws URISyntaxException {
        URI uri = new URI("");
        WeatherData openWeatherApi = RestClientBuilder.newBuilder()
                .baseUri(uri)
                .build(Weatherdata.class);

        return null;
    }
}
