import Entities.GoogleRoutesResponse;
import Recources.WeatherResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import requests.GetRouteWeatherRequest;
import requests.GetSpotWeatherRequest;

import java.net.URISyntaxException;

@ApplicationScoped
@Path("/")
public class Main {

    @Inject
    WeatherResource weatherResource;

    @GET
    @Path("getWeatherOnRoute")
    public Response getWeatherOnRoute(GetRouteWeatherRequest request){
        return Response.ok(weatherResource.getWeatherOnRoute(request.route)).build();
    }

    @GET
    @Path("getWeatherOnSpot")
    public Response getWeatherOnSpot(GetSpotWeatherRequest request){
        return Response.ok(weatherResource.getWeatherOnSpot(request)).build();
    }
}
