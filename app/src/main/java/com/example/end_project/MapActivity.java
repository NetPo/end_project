package com.example.end_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {


    int TAG_CODE_PERMISSION_LOCATION;

    ArrayList<M_RV> malls = new ArrayList<M_RV>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.list);
        M_Adapter m_adapter = new M_Adapter(this, malls);
        recyclerView.setAdapter(m_adapter);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }


    private void setInitialData(){
        malls.add(new M_RV("Бутово Молл", "3"));
        malls.add(new M_RV("Витте Молл", "4"));
        malls.add(new M_RV("Бутово Молл", "3"));
        malls.add(new M_RV("Витте Молл", "4"));
        malls.add(new M_RV("Бутово Молл", "3"));
        malls.add(new M_RV("Витте Молл", "4"));
    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(55.523742, 37.517838))
                .zoom(15)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        googleMap.animateCamera(cameraUpdate);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {

            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
        }

      googleMap.addMarker(new MarkerOptions().position(new LatLng(55.523742, 37.517838)).title("Бутово Молл"));
    }
}