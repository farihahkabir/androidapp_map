package com.example.testmap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in dhaka and move the camera
        LatLng dhaka = new LatLng(23.797545, 90.410650);
        mMap.addMarker(new MarkerOptions().position(dhaka).title("Dhaka"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dhaka, 5F));

        LatLng beijing = new LatLng(39.9, 116.4);
        mMap.addMarker(new MarkerOptions().position(beijing).title("Beijing"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(beijing, 5F));

        LatLng bangkok = new LatLng(13.8, 100.5);
        mMap.addMarker(new MarkerOptions().position(bangkok).title("Bangkok"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bangkok, 5F));


        LatLng london = new LatLng(51.5, 0.12);
        mMap.addMarker(new MarkerOptions().position(london).title("London"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(london, 5F));


        LatLng newyork = new LatLng(40.7, 74);
        mMap.addMarker(new MarkerOptions().position(newyork).title("New York"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newyork, 5F));


        LatLng sydney = new LatLng(33.9, 151.2);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5F));


        LatLng barcelona = new LatLng(41.3, 2.2);
        mMap.addMarker(new MarkerOptions().position(barcelona).title("Barcelona"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(barcelona, 5F));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
