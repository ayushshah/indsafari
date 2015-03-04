package com.example.pleasework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity implements LocationListener,
        OnMapClickListener {
    Location l2;
    boolean isDlgDisplayed = false;
    LatLng currentloc, firstloc, secondloc;
    String hotelAddress2;
    int flag = 1;

    // Google Map
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        hotelAddress2 = intent.getStringExtra("hotelAddress");
        Toast.makeText(getApplicationContext(), "" + hotelAddress2,
                Toast.LENGTH_LONG).show();
        try {
            // Loading map
            initializeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initializeMap() {
        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getBaseContext());

        if (status == ConnectionResult.SUCCESS) {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager()
                        .findFragmentById(R.id.map)).getMap();

                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMapClickListener(this);
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                List<String> providers = locationManager.getAllProviders();
                Criteria criteria = new Criteria();

                String provider = locationManager.getBestProvider(criteria,
                        true);

                Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));

              /*  if (location != null) {

                    if (isDlgDisplayed == true)
                        return;
              */
                    if(location!=null){
                        Toast.makeText(getApplicationContext(), "Hey This Works!", Toast.LENGTH_LONG).show();
                    }

/*
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);

                    MarkerOptions marker1 = new MarkerOptions()
                            .position(latLng).title("First Location ");
                    marker1.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                    googleMap.addMarker(marker1);
*/


                    LatLng latLng2;
                    double longit = 0;
                    double latit = 0;
                    Geocoder coder = new Geocoder(this);
                    try {
                        ArrayList<Address> adresses = (ArrayList<Address>) coder
                                .getFromLocationName(hotelAddress2, 100);
                        for (Address add : adresses) {
                            longit = add.getLongitude();
                            latit = add.getLatitude();
                        }
                        if (!((latit == 0) && (longit == 0))) {
                            latLng2 = new LatLng(latit, longit);

                            MarkerOptions marker2 = new MarkerOptions()
                                    .position(latLng2).title(hotelAddress2);
                            marker2.icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                            googleMap.addMarker(marker2);

                            googleMap.moveCamera(CameraUpdateFactory
                                    .newLatLng(latLng2));

                            googleMap.animateCamera(CameraUpdateFactory
                                    .zoomTo(15));

/*
                            Location l1 = new Location("first");
*/
                            l2 = new Location("second");

/*
                            l1.setLatitude(latLng.latitude);
                            l1.setLongitude(latLng.longitude);
*/

                            l2.setLatitude(latLng2.latitude);
                            l2.setLongitude(latLng2.longitude);
/*

                            float dist = l1.distanceTo(l2);
                            dist = dist / 1000;
                            String dis = String.format("%.2f", dist);
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Distance is " + dis + "Km from your place",
                                    Toast.LENGTH_LONG).show();

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Distance is " + dis + "Km from your place",
                                    Toast.LENGTH_LONG).show();

                            Toast.makeText(getApplicationContext(),
                                    "Tap again for navigation",
                                    Toast.LENGTH_LONG).show();
*/
                        } else {

                            Toast.makeText(getApplicationContext(),
                                    "Address not found on map",
                                    Toast.LENGTH_LONG).show();
                            finish();

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

               /* }else{
                    Toast.makeText(getApplicationContext(), "Lat:Lon:",
                            Toast.LENGTH_LONG).show();
                }*/

                if (googleMap == null) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeMap();
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onMapClick(LatLng location) {

        String url = "http://maps.google.com/maps?f=d&daddr="
                + l2.getLatitude() + "," + l2.getLongitude() + "&mode=driving";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(url));
        intent.setClassName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity");
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Opening google maps...",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

    }
}
