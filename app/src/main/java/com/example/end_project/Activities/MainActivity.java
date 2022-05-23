package com.example.end_project.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.end_project.AClickListener;
import com.example.end_project.Advices;
import com.example.end_project.DAOmalls;
import com.example.end_project.Fragment.BottomSheet;
import com.example.end_project.M_RV;
import com.example.end_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    DAOmalls daOmalls;
    ArrayList<M_RV> malls = new ArrayList<>();
    String name;
    String floors_am;
    String tvEnabledGPS;
    String tvStatusGPS;
    String tvLocationGPS;
    String tvEnabledNet;
    String tvStatusNet;
    String tvLocationNet;
    String lon;

    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView helper = findViewById(R.id.helper);

        //GPS
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        //infoButton
        ImageButton infoButton = findViewById(R.id.infoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheetfragment();
            }
        });

        //floorActivity button
        Button tc = findViewById(R.id.button);


        //DAO
        daOmalls = new DAOmalls();
        daOmalls.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()){
                    M_RV m_rv = data.getValue(M_RV.class);
                    malls.add(m_rv);
                }

                //GEO_Data
                Double lat_geo = 0.0;
                Double longt_geo = 0.0;
                String a = "8";
                if(tvLocationGPS == null || lon == null){
                    lat_geo = 55.710689;
                    longt_geo =37.603403;
                } else {
                    lat_geo = Double.parseDouble(tvLocationGPS);
                    longt_geo = Double.parseDouble(lon);
                }
                Double finalLat_geo = lat_geo;
                Double finalLongt_geo = longt_geo;
                Collections.sort(malls, new Comparator<M_RV>() {
                    @Override
                    public int compare(M_RV o1, M_RV o2) {
                        Double x1 = Double.parseDouble(o1.getCoord_x());
                        Double x2 = Double.parseDouble(o2.getCoord_x());
                        Double y1 = Double.parseDouble(o1.getCoord_y());
                        Double y2 = Double.parseDouble(o2.getCoord_y());
                        Double ans1 = Math.sqrt((finalLat_geo -x1)*(finalLat_geo -x1) + (finalLongt_geo -y1)*(finalLongt_geo -y1));
                        Double ans2 = Math.sqrt((finalLat_geo -x2)*(finalLat_geo -x2) + (finalLongt_geo -y2)*(finalLongt_geo -y2));
                        return ans1.compareTo(ans2);
                    }
                });
                for(M_RV m_rv : malls){
                   name = m_rv.getName();
                   floors_am = m_rv.getFloors_am();
                   break;
                }
                tc.setText(name);
                helper.setText(floors_am);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, FloorActivity.class);
                intent2.putExtra("Название выбранного тц", tc.getText().toString());
                intent2.putExtra("Количество этажей", helper.getText().toString());
                startActivity(intent2);
            }
        });


        //fireButton
        ImageButton fire = findViewById(R.id.imageButton3);
        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });



        //telephone button
        ImageButton tel = findViewById(R.id.teleph_button);
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = 112;
                Intent call_112 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+num));
                startActivity(call_112);
            }
        });


    }

    //BottomSheet fragment
    private void openBottomSheetfragment() {
        ;
        ArrayList<Advices> adv = new ArrayList<>();
        adv.add(new Advices(getResources().getString(R.string.adv1)));
        adv.add(new Advices(getResources().getString(R.string.adv2)));
        adv.add(new Advices(getResources().getString(R.string.adv3)));
        adv.add(new Advices(getResources().getString(R.string.adv4)));
        adv.add(new Advices(getResources().getString(R.string.adv5)));
        adv.add(new Advices(getResources().getString(R.string.adv6)));
        adv.add(new Advices(getResources().getString(R.string.adv7)));
        adv.add(new Advices(getResources().getString(R.string.adv8)));
        BottomSheet bottomSheet = new BottomSheet(adv, new AClickListener() {
            @Override
            public void clickItem(Advices advice) {
                Toast.makeText(getApplicationContext(), "Главное - не паникуйте!", Toast.LENGTH_SHORT).show();
            }
        });
        bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000 * 10, 10,
                locationListener);
        checkEnabled();
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            checkEnabled();
        }

        @SuppressLint("MissingPermission")
        @Override
        public void onProviderEnabled(String provider) {
            checkEnabled();
            showLocation(locationManager.getLastKnownLocation(provider));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            if (provider.equals(LocationManager.GPS_PROVIDER)) {
                tvStatusGPS = "Status: " + String.valueOf(status);
            } else if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
                tvStatusNet = "Status: " + String.valueOf(status);
            }
        }
    };

    private void showLocation(Location location) {
        if (location == null)
            return;
        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
            tvLocationGPS = formatLocation(location);
            lon =formatLocation_lon(location);
        } else if (location.getProvider().equals(
                LocationManager.NETWORK_PROVIDER)) {
            tvLocationNet = formatLocation(location);
        }
    }

    @SuppressLint("DefaultLocale")
    private String formatLocation(Location location) {
        if (location == null)
            return "";
        return String.format(
                "%1$.4f",
                location.getLatitude());
    }

    @SuppressLint("DefaultLocale")
    private String formatLocation_lon(Location location) {
        if (location == null)
            return "";
        return String.format(
                "%1$.4f",
                location.getLongitude());
    }


    private void checkEnabled() {
        tvEnabledGPS = "Enabled: "
                + locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        tvEnabledNet = "Enabled: "
                + locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

}