package soylente.com.trakrecord;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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
import java.util.List;

public class mainMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Camp> camps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        camps = getCamps();
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
        mMap = googleMap;/*
        for(int i = 0; i < camps.size; i++) {
            if (camps.event_camps[i] != null)
                mMap.addMarker(new MarkerOptions().position(camps.getCoords(i)).title(camps.getName(i)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(camps.getCoords(1)));*/
    }
    private List<Camp> getCamps(){
    Firebase ref = new Firebase("https://trakrecord.firebaseio.com/Camps");
        final List<Camp> camps = new ArrayList<>();
        // Attach an listener to read the data at our posts reference
        ValueEventListener valueEventListener = ref.addValueEventListener(new ValueEventListener() {
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
        });
        return camps;
    }
}
