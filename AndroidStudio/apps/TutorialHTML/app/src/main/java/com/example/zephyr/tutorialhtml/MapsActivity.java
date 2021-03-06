package com.example.zephyr.tutorialhtml;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class MapsActivity extends Activity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.setMyLocationEnabled(true);

        MapsMarker marker1 = new MapsMarker("Casa di Amore Mio", "The most beautiful girl in the world", 45.211410, 7.629830);
        MapsMarker marker2 = new MapsMarker("Casa Mia", "tanto lontana da casa di amore mio :'(", 45.064560, 7.524945);
        MapsMarker marker3 = new MapsMarker("Politecnico", "Universita' di Torino", 45.062936, 7.660747);

        setMarkerInMap(map, marker1);
        setMarkerInMap(map, marker2);
        setMarkerInMap(map, marker3);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker3.markerLatLng, 13));

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setMarkerInMap(GoogleMap map, MapsMarker mapsMarker){

        map.addMarker(new MarkerOptions().title(mapsMarker.markerName).snippet(mapsMarker.markerDescription).position(mapsMarker.markerLatLng));

    }
}
