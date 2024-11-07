package Entities;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class WeatherData {
    public double lat;
    public double lon;
    public String timezone;
    public int timezoneOffset;
    public Current current;
    public List<Minutely> minutely;
    public List<Hourly> hourly;
    public List<Daily> daily;

    public static class Current {
        public long dt;
        public long sunrise;
        public long sunset;
        public double temp;
        public double feelsLike;
        public int pressure;
        public int humidity;
        public double dewPoint;
        public double uvi;
        public int clouds;
        public int visibility;
        public double windSpeed;
        public int windDeg;
        public List<Weather> weather;
    }

    public static class Minutely {
        public long dt;
        public double precipitation;
    }

    public static class Hourly {
        public long dt;
        public double temp;
        public double feelsLike;
        public int pressure;
        public int humidity;
        public double dewPoint;
        public double uvi;
        public int clouds;
        public int visibility;
        public double windSpeed;
        public int windDeg;
        public double windGust;
        public List<Weather> weather;
        public double pop;
    }

    public static class Daily {
        public long dt;
        public long sunrise;
        public long sunset;
        public long moonrise;
        public long moonset;
        public double moonPhase;
        public String summary;
        public Temperature temp;
        public FeelsLike feelsLike;
        public int pressure;
        public int humidity;
        public double dewPoint;
        public double windSpeed;
        public int windDeg;
        public double windGust;
        public List<Weather> weather;
        public int clouds;
        public double pop;
        public double uvi;
        public double rain;
    }

    public static class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public static class Temperature {
        public double day;
        public double min;
        public double max;
        public double night;
        public double eve;
        public double morn;
    }

    public static class FeelsLike {
        public double day;
        public double night;
        public double eve;
        public double morn;
    }
}
