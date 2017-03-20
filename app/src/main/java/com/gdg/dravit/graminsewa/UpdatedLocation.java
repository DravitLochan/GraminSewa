package com.gdg.dravit.graminsewa;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.google.android.gms.location.LocationListener;
import android.location.*;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UpdatedLocation extends AppCompatActivity {

    TextView timeUpdated, latitude, longitude, details;
    Button getUpdate;
    Geocoder geocoder;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated_location);

        getUpdate = (Button) findViewById(R.id.getUpdate);
        timeUpdated = (TextView) findViewById(R.id.timeUpdated);
        longitude = (TextView) findViewById(R.id.longitude);
        latitude = (TextView) findViewById(R.id.latitude);
        details= (TextView) findViewById(R.id.details);
        geocoder=new Geocoder(this, Locale.getDefault());
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(getApplicationContext(),"called!",Toast.LENGTH_SHORT).show();
                long longD = location.getTime();
                Date d = new Date(longD);
                SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yy HH:mm:ss");
                String sDate = sdf.format(d);
                timeUpdated.setText("time : "+sDate + "");
                longitude.setText("longitude : "+location.getLongitude() + "");
                latitude.setText("latitude : "+location.getLatitude() + "");
                try {
                    List<Address> add= geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    details.setText(add.get(0).getLocality()+"\n"+add.get(0).getAddressLine(0)+"\n"+add.get(0).getFeatureName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "test123", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, locationListener);
        getUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

            }
        });
    }
}
