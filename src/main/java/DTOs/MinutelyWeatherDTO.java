package DTOs;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MinutelyWeatherDTO {
    public long dt;
    public double temp;
    public double feelsLike;
    public double windSpeed;
    public double rain;
    public double snow;
    public String weatherDescription;
    public double precipitaionChance;
}
