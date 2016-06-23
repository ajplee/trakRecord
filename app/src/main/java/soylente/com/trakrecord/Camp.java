package soylente.com.trakrecord;

import com.google.android.gms.maps.model.LatLng;

/**
* Created by sam on 2016-06-02.
*/

public class Camp {
    private String campName;

    private double latitude;
    private double longitude;
    private LatLng coords;

    public Camp(){

    }
    public Camp(String name, double lat, double lng) {
        campName = name;
        latitude = lat;
        longitude = lng;
        coords = new LatLng(latitude, longitude);
    }


    public String getName() {
        return campName;
    }

    public LatLng getCoords() {
        if(coords == null)  
            coords = new LatLng(latitude, longitude);
        return coords;
    }
}

