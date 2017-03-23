package tech.libin.rahul.ideaproject.service.models;

/**
 * Created by rahul on 3/5/2017.
 */

public class LocationModel {
    String latitude;
    String longitude;
    String landmark;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
