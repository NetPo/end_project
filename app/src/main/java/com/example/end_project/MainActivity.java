package com.example.end_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DAOmalls daOmalls;
    ArrayList<M_RV> malls = new ArrayList<>();
    String name;
    String floors_am;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView helper = findViewById(R.id.helper);

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
}