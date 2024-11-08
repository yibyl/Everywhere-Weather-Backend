package Entities;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class WeatherData {
    public double lat;
    public double lon;
    public String timezone;
    public int timezone_offset;
    public Current current;
    public List<Minutely> minutely;
    public List<Hourly> hourly;
    public List<Daily> daily;

    public static class Current {
        public long dt;
        public long sunrise;
        public long sunset;
        public double temp;
        public double feels_like;
        public int pressure;
        public int humidity;
        public double dew_point;
        public double uvi;
        public int clouds;
        public int visibility;
        public double wind_speed;
        public double wind_gust;
        public int wind_deg;
        public Precipitation rain;
        public Precipitation snow;
        public List<Weather> weather;
    }

    public static class Minutely {
        public long dt;
        public double precipitation;
    }

    public static class Hourly {
        public long dt;
        public double temp;
        public double feels_like;
        public int pressure;
        public int humidity;
        public double dew_point;
        public double uvi;
        public int clouds;
        public int visibility;
        public double wind_speed;
        public double wind_gust;
        public int wind_deg;
        public double pop;
        public Precipitation rain;
        public Precipitation snow;
        public List<Weather> weather;
    }

    public static class Daily {
        public long dt;
        public long sunrise;
        public long sunset;
        public long moonrise;
        public long moonset;
        public double moon_phase;
        public String summary;
        public Temperature temp;
        public FeelsLike feels_like;
        public int pressure;
        public int humidity;
        public double dew_point;
        public double wind_speed;
        public double wind_gust;
        public int wind_deg;
        public int clouds;
        public double pop;
        public double uvi;
        public double rain;
        public double snow;
        public List<Weather> weather;
    }

    public static class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public static class Temperature {
        public double morn;
        public double day;
        public double eve;
        public double night;
        public double min;
        public double max;
    }

    public static class FeelsLike {
        public double morn;
        public double day;
        public double eve;
        public double night;
    }

    public static class Precipitation {
        public double one_hour;
    }
}
