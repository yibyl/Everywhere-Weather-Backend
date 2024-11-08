package Responses;

import java.util.List;

public class GoogleRoutesResponse {
    public List<Route> routes;

    public static class Route {
        public List<String> routeLabels;
        public List<Leg> legs;
        public OverviewPolyline overviewPolyline;
        public String summary;
    }

    public static class Leg {
        public int distanceMeters;
        public String duration;
        public String staticDuration;
        public Location startLocation;
        public Location endLocation;
        public List<Step> steps;
    }

    public static class Step {
        public int distanceMeters;
        public String staticDuration;
        public Polyline polyline;
        public Location startLocation;
        public Location endLocation;
        public NavigationInstruction navigationInstruction;
    }

    public static class NavigationInstruction {
        public String maneuver;
        public String instruction;
    }

    public static class Polyline {
        public String encodedPolyline;
    }

    public static class OverviewPolyline {
        public String encodedPolyline;
    }

    public static class Location {
        public LatLng latLng;
    }

    public static class LatLng {
        public double latitude;
        public double longitude;
    }
}
