package DTOs;

import Entities.Cords;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class RouteWeatherDTO {
    public List<String> routeLabel;
    public Map<Cords, MinutelyWeatherDTO> route;

    public RouteWeatherDTO(List<String> routeLabel, Map<Cords, MinutelyWeatherDTO> route){
        this.routeLabel = routeLabel;
        this.route = route;
    }

    public RouteWeatherDTO(){
    }
}
