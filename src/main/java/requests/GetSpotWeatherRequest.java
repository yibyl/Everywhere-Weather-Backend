package requests;

import Entities.Cords;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetSpotWeatherRequest {
    public Cords cords;
    public int timeOffset;
}
