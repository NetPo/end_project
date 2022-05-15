package com.example.end_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton fire = findViewById(R.id.imageButton3);
        Button btn = findViewById(R.id.buttonDAO);
        //DAOmalls dao = new DAOmalls();
        //btn.setOnClickListener(v->
        //{
        //    M_RV mall = new M_RV("РИО", "7", "55.663697", "37.511188");
        //    dao.add(mall).addOnSuccessListener(suc ->{
        //        Toast.makeText(this, "OK", Toast.LENGTH_LONG).show();
        //    }).addOnFailureListener(err ->{
        //        Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
        //    });
        //});
        fire.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        Button tc = findViewById(R.id.button);
        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, FloorActivity.class);
                startActivity(intent2);
            }
        });
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