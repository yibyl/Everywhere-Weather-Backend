import Recources.WeatherResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import requests.GetWeatherRequest;

import java.net.URISyntaxException;

@ApplicationScoped
@Path("/")
public class Main {

    @Inject
    WeatherResource weatherResource;

    @GET
    @Path("getWeatherOnRoute")
    public Response getWeatherOnRoute(GetWeatherRequest request){
        return Response.ok(weatherResource.getWeatherOnRoute(request)).build();
    }

    @GET
    @Path("getWeatherOnSpot")
    public Response getWeatherOnSpot(GetWeatherRequest request) throws URISyntaxException {
        return Response.ok(weatherResource.getWeatherOnSpot(request.cordsList.getFirst())).build();
    }
}
