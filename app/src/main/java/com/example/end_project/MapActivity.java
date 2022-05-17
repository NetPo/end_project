package com.example.end_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {


    int TAG_CODE_PERMISSION_LOCATION;

    ArrayList<M_RV> malls = new ArrayList<M_RV>();

    RecyclerView recyclerView;
    DAOmalls dao;
    M_Adapter m_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //RecyclerView
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        m_adapter = new M_Adapter(this, malls);
        recyclerView.setAdapter(m_adapter);

        //БД
        dao = new DAOmalls();
        loadData();


        //fragment
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }


    //DAO
    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
            ArrayList<M_RV> malls = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()){
                    M_RV m_rv = data.getValue(M_RV.class);
                    malls.add(m_rv);
                }
                m_adapter.setItems(malls);
                m_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




    //map
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
        googleMap.addMarker(new MarkerOptions().position(new LatLng(55.612762, 37.607191)).title("Columbus"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(55.704943, 37.641285)).title("Ривьера"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(55.663697, 37.511188)).title("РИО"));

        //остальные метки добавлю потом :)
    }
}