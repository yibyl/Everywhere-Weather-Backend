package requests;

import Entities.Cords;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GetWeatherRequest {
    public List<Cords> cordsList;
}
