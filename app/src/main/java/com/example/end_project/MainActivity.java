package com.example.end_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton fire = findViewById(R.id.imageButton3);
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