import Resources.WeatherResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import requests.GetRouteWeatherRequest;
import requests.GetSpotWeatherRequest;

@ApplicationScoped
@Path("/")
public class Main {

    @Inject
    WeatherResource weatherResource;

    @GET
    @Path("getWeatherOnRoute")
    public Response getWeatherOnRoute(GetRouteWeatherRequest request){
        return Response.ok(weatherResource.getWeatherOnRoute(request.route, request.timeOffset)).build();
    }

    @GET
    @Path("getWeatherOnSpot")
    public Response getWeatherOnSpot(GetSpotWeatherRequest request){
        return weatherResource.getWeatherOnSpot(request);
    }
}
