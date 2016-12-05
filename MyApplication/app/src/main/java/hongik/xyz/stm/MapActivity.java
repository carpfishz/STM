package hongik.xyz.stm;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker[] markers = new Marker[65];
    private DBconnect dbconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);
        dbconnect = new DBconnect(getApplicationContext(), "map.db",null, 1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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

        JSONArray marketData = dbconnect.getAll(DBconnect.TABLE_NAME_MAP);

        int i=0;
        while (!marketData.isNull(i)){
            try {
                JSONObject jo = marketData.getJSONObject(i);
                markers[i] = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(jo.getString("l_lat")),Double.parseDouble(jo.getString("l_long"))))
                        .title(jo.getString("place_ID"))
                        .snippet(jo.getString("place_name")));
                Log.d("marker",jo.getString("place_name"));
            }catch (JSONException e){
                e.getStackTrace();
            }
            i++;
        }
        // Add a marker in Sydney and move the camera
        LatLng sejong = new LatLng(36.600422, 127.299622);
        //mMap.addMarker(new MarkerOptions().position(sejong).title("Marker test"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sejong,16f));
    }
}
