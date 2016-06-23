package soylente.com.trakrecord;

import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class mainMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Camp> camps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Firebase.setAndroidContext(this);
        getCamps();
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (!camps.isEmpty()) {
            for (Camp c : camps) {
                System.out.println(c.getCampName() + "  " + c.getCoords());
                mMap.addMarker(new MarkerOptions().position(c.getCoords()).title(c.getCampName()));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(camps.get(0).getCoords()));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        }
    }

    private void getCamps() {
/*        Firebase ref = new Firebase("https://trakrecord.firebaseio.com/Camps");
        // Attach an listener to read the data at our posts reference

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " camps");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    camps.add(postSnapshot.getValue(Camp.class));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });*/
        camps.add(new Camp("Home", 43.637141, -79.406711));
        camps.add(new Camp("1st", 43.650842, -79.373020));
        camps.add(new Camp("2nd", 43.662902, -79.391624));
    }
}
