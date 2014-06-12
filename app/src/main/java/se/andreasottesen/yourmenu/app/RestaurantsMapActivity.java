package se.andreasottesen.yourmenu.app;

import android.app.Activity;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class RestaurantsMapActivity extends Activity
    implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener,
    LocationListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener {

    public static final int UPDATE_INTERVAL = 10000;
    public static final int FASTEST_INTERVAL = 5000;

    private LocationRequest locationRequest;
    private GoogleMap googleMap;
    private LocationClient locationClient;
    private boolean updatesRequested;
    private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_map);

        getActionBar().hide();

        final int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS){
            Toast.makeText(this, "Google play services not available...", Toast.LENGTH_LONG).show();
            finish();
        }
        createMapView();
        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);
        googleMap.setOnMarkerDragListener(this);
        googleMap.setMyLocationEnabled(true);

        locationRequest = LocationRequest.create();
        locationClient = new LocationClient(this, this, this);

        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        updatesRequested = true;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Marker")
                        .draggable(true)
                        .snippet("Lat:" + latLng.latitude + "Long:" + latLng.longitude)
        );
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        LatLng position = marker.getPosition();
        double lat = position.latitude;
        double lon = position.longitude;
        Toast.makeText(this, "Marker dragged! " + lat +": " + lon, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        locationClient.disconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();

        locationClient.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!locationClient.isConnected()){
            locationClient.connect();
        }
    }

    @Override
    protected void onStop() {
        if (locationClient.isConnected()){
            locationClient.removeLocationUpdates(this);
            locationClient.disconnect();
        }

        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.restaurants_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createMapView(){
        try{
            if (googleMap == null){
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();
            }

            if (googleMap == null){
                Toast.makeText(getApplicationContext(), "Error creating map", Toast.LENGTH_LONG).show();
            }
        }
        catch (NullPointerException e){
            Log.e("MapView", e.toString());
        }
    }

    private void addMarker(Location location){
        if (googleMap != null){
            LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());

            googleMap.clear();
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 16));

            googleMap.addMarker(new MarkerOptions()
                    .position(currentPosition)
                    .title("Marker")
                    .draggable(true)
                    .snippet("Lat:" + location.getLatitude() + "Long:" + location.getLongitude())
            );
        }
    }

    private CharSequence addressToText(Address address) {
        final StringBuilder addressText = new StringBuilder();
        for (int i = 0, max = address.getMaxAddressLineIndex(); i < max; ++i) {
            addressText.append(address.getAddressLine(i));
            if ((i+1) < max) {
                addressText.append(", ");
            }
        }
        addressText.append(", ");
        addressText.append(address.getCountryName());
        return addressText;
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();

        if (updatesRequested){
            locationClient.requestLocationUpdates(locationRequest, this);
        }

        currentLocation = locationClient.getLastLocation();
        Log.d("Location", currentLocation.toString());

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 16));

        Geocoder geocoder = new Geocoder(this);

        try {
            List<Address> result = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
            Toast.makeText(this, addressToText(result.get(0)), Toast.LENGTH_LONG).show();
            addMarker(currentLocation);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisconnected() {
        Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection failed", Toast.LENGTH_SHORT).show();
    }
}
