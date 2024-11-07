package Entities;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Cords {
    public float lat;
    public float lon;

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
