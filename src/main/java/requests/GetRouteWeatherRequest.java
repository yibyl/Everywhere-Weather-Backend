package requests;

import Entities.GoogleRoutesResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetRouteWeatherRequest {
    public GoogleRoutesResponse route;
}
