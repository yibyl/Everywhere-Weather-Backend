package DTOs;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HourlyWeatherDTO {
    public long dt;
    public double temp;
    public double feelsLike;
    public double windSpeed;
    public double precipitaionChance;
    public String weatherDescription;

    public double rain;
    public double snow;
}
