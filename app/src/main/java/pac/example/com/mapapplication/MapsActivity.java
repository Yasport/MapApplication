package pac.example.com.mapapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private HashMap<Integer,Integer> hm;
    private boolean mLocationPermissionGranted;
    //private Button btn_list;
    //private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Button button = findViewById(R.id.btn_list);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MapsActivity.this.startActivity(new Intent(MapsActivity.this, ListViewActivity.class));
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        hm = new HashMap<>();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReferenceFromUrl("https://mapapplication-daf7c.firebaseio.com/Parking");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Parking parking = dataSnapshot.getValue(Parking.class);

                LatLng latLng = new LatLng(Double.parseDouble(parking.getV1()),Double.parseDouble(parking.getV2()));
                //LatLng latLng = new LatLng(48.113994,-1.646504);
                mMap.addMarker(new MarkerOptions().position(latLng).title(""+parking.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));

                //Toast.makeText(getApplicationContext(), "Name: " + newPost.getName(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "V1: " + newPost.getV1(), Toast.LENGTH_SHORT).show();

                //hm.put(Integer.parseInt(newPost.getV1()),Integer.parseInt(newPost.getV2()));

                //System.out.println("Previous Post ID: " + prevChildKey);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        //LatLng latLng = new LatLng(48.113994,-1.646504);
        //mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in "));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));

        for(Map.Entry m:hm.entrySet()){
            Toast.makeText(getApplicationContext(), "V1: " + m.getKey()+"   V2: "+m.getValue(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_1:
                //newGame();
                return true;
            case R.id.item_2:
                //showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
