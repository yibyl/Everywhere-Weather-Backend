package Clients;

import Entities.WeatherData;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://api.openweathermap.org")
public interface OpenWeatherClient {

    @GET
    @Path("/data/3.0/onecall")
    public WeatherData getWeather(@QueryParam("lat") double lat, @QueryParam("lon") double lon, @QueryParam("appid") String appid);

}
