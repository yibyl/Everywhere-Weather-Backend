import Resources.WeatherResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import requests.GetRouteWeatherRequest;
import requests.GetSpotWeatherRequest;

@ApplicationScoped
@Path("/")
public class Main {

    @Inject
    WeatherResource weatherResource;

    @POST
    @Path("getWeatherOnRoute")
    public Response getWeatherOnRoute(GetRouteWeatherRequest request){
        System.out.println(request.route);
        System.out.println(request.route.routes);
        System.out.println(request.timeOffset);


        if (request == null) {
            System.err.println("Received null request!");
            return Response.status(Response.Status.BAD_REQUEST).entity("Request body is null").build();
        }
        System.out.println("Received request: " + request);
        if (request.route == null ) {
            System.err.println("Received request with null or empty routes!");
            return Response.status(Response.Status.BAD_REQUEST).entity("Routes are null or empty").build();
        }
        return Response.ok(weatherResource.getWeatherOnRoute(request.route, request.timeOffset)).build();
    }

    @POST
    @Path("getWeatherOnSpot")
    public Response getWeatherOnSpot(GetSpotWeatherRequest request){
        return weatherResource.getWeatherOnSpot(request);
    }
}
