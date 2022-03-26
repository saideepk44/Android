package com.example.gpsfinalv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;
    TextView lat;
    TextView lon;
    TextView address;
    ArrayList addresses;
    ArrayList times;
    Location previous;
    double distance;
    TextView lastDistance;
    Geocoder geocoder;
    int i = 0;
    long start;
    long end;
    double time;
    boolean exit;
    int d;
    int large;
    TextView textView;
    String oldAddy;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lon = findViewById(R.id.textView2);
        lat = findViewById(R.id.textView);
        address = findViewById(R.id.textView4);
        distance = 0;
        large = 0;
        times = new ArrayList<Integer>();
        addresses = new ArrayList<String>();
        start = SystemClock.elapsedRealtime();
        lastDistance = findViewById(R.id.textView3);
        textView = findViewById(R.id.textView5);

        geocoder = new Geocoder(MainActivity.this, Locale.US);


        Log.d("TAGGGGGGGG", ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) + "");
        Log.d("TAGGGGGGGG", ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) + "");

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.d("Tag","Location Changed");
                end = SystemClock.elapsedRealtime();
                exit = true;
                if(i != 0)
                {
                    distance = (double) location.distanceTo(previous);
                }
                else
                {
                    i++;
                }
                //previous = location;
                lat.setText("Latitude: " + location.getLatitude());
                lon.setText("Longitude: " + location.getLongitude());
                try {
                    lastDistance.setText(distance + "meters");
                    address.setText(geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1).get(0).getAddressLine(0));
                    Log.d("TAg",geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1).toString());
                    Log.d("Address",geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1).toString());
                    for(int j = 0; j < addresses.size(); j++)
                    {
                        if(addresses.get(j).equals(address.getText().toString()))
                        {
                            exit = false;
                            d = (int)times.get(j) +  + (int)(end - start)/1000;
                            times.set(j,d);
                        }
                    }
                    if(exit)
                    {
                        addresses.add(address.getText());
                        times.add((int)(end - start)/1000);
                    }
                    start = end;
                    large = 0;
                    for(int y = 0; y < times.size(); y++)
                    {
                        Log.d("TAG", addresses.get(y) + " for " + times.get(y));
                        if((Integer.parseInt(times.get(y).toString())>Integer.parseInt(times.get(large).toString())))
                        {
                            Log.d("TAGG",times.get(y).toString() + " " + large);

                            large = y;

                        }
                        Log.d("TAG",times.get(y).toString());
                    }
                    if(times.size() > 1)
                    {
                        textView.setText(oldAddy + " for " + times.get(large));
                    }

                    else
                    {
                        textView.setText(addresses.get(0) + " for " + times.get(0));
                    }
                    oldAddy = addresses.get(large).toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                previous = location;
            }
        };
        if (((ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) != PackageManager.PERMISSION_GRANTED) && ((ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }
        else
        {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,5,5,locationListener);
        }
    }
    @SuppressLint({"MissingPermission", "MissingSuperCall"})
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        Log.d("RPR","F");
        if(requestCode == 0)
        {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,5,5,locationListener);
        }
    }
}