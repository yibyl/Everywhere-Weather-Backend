package requests;

import Responses.GoogleRoutesResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetRouteWeatherRequest {
    public GoogleRoutesResponse route;
    public int timeOffset;
}
