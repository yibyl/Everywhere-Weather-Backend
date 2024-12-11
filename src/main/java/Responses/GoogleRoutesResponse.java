package Responses;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GoogleRoutesResponse {
    @SerializedName("routes")
    public List<Route> routes;

    public static class Route {
        @SerializedName("legs")
        public List<Leg> legs;

        @SerializedName("summary")
        public String summary;

        @SerializedName("overview_polyline")
        public OverviewPolyline overviewPolyline;
    }

    public static class Leg {
        @SerializedName("distanceMeters")
        public int distanceMeters;

        @SerializedName("duration")
        public Duration duration;

        @SerializedName("staticDuration")
        public String staticDuration;

        @SerializedName("start_location")
        public Location startLocation;

        @SerializedName("end_location")
        public Location endLocation;

        @SerializedName("steps")
        public List<Step> steps;
    }

    public static class Step {
        @SerializedName("distanceMeters")
        public int distanceMeters;

        @SerializedName("staticDuration")
        public Duration staticDuration;

        @SerializedName("polyline")
        public Polyline polyline;

        @SerializedName("start_location")
        public Location startLocation;

        @SerializedName("end_location")
        public Location endLocation;

        @SerializedName("navigationInstruction")
        public NavigationInstruction navigationInstruction;
    }

    public static class Duration {
        @SerializedName("text")
        public String text;

        @SerializedName("value")
        public int value;
    }

    public static class NavigationInstruction {
        @SerializedName("maneuver")
        public String maneuver;

        @SerializedName("instruction")
        public String instruction;
    }

    public static class Polyline {
        @SerializedName("points")
        public String encodedPolyline;
    }

    public static class OverviewPolyline {
        @SerializedName("points")
        public String encodedPolyline;
    }

    public static class Location {
        @SerializedName("lat")
        public double latitude;

        @SerializedName("lng")
        public double longitude;
    }
}
