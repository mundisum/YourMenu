package se.andreasottesen.yourmenu.app.restaurant;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Andreas on 2014-06-14.
 */
public class Vendor {
    public String id;
    public String name;
    public String description;
    public double latitude;
    public double longitude;

    public Vendor(String id, String name, String description, double latitude, double longitude){
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LatLng getLatLng(){
        return new LatLng(latitude, longitude);
    }

    @Override
    public String toString() {
        return name;
    }
}
