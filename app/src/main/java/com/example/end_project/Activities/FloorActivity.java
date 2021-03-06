package com.example.end_project.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.end_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FloorActivity extends AppCompatActivity {

    Integer[] floors;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);

        //from intent
        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("Название выбранного тц").toString();
        String floors_am = arguments.get("Количество этажей").toString();

        //amount of floors
        floors = new Integer[Integer.parseInt(floors_am)];
        for(int i = 0; i < Integer.parseInt(floors_am); i++){
            floors[i] = i+1;
        }

        //ImageView
        img = findViewById(R.id.imageView);

        //spinner
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.spinner_item, floors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int selectedItemPosition, long selectedId) {
                Integer[] choose = floors.clone();
                switch (name) {
                    case("Ривьера"):
                    switch (choose[selectedItemPosition]) {
                        case (1):
                            img.setImageResource(R.drawable.riviera_1);
                            break;
                        case (2):
                            img.setImageResource(R.drawable.riviera_2);
                            break;
                        case (3):
                            img.setImageResource(R.drawable.riviera_3);
                            break;
                    }
                    break;
                    case ("Columbus"):
                        switch (choose[selectedItemPosition]) {
                            case (1):
                                img.setImageResource(R.drawable.columbus1);
                                break;
                            case (2):
                                img.setImageResource(R.drawable.columbus2);
                                break;
                            case (3):
                                img.setImageResource(R.drawable.columbus3);
                                break;
                            case (4):
                                img.setImageResource(R.drawable.columbus4);
                                break;
                            case (5):
                                img.setImageResource(R.drawable.columbus5);
                                break;
                        }
                        break;
                    case ("Бутово Молл"):
                        switch (choose[selectedItemPosition]){
                            case (1):
                                img.setImageResource(R.drawable.bm1);
                                break;
                            case (2):
                                img.setImageResource(R.drawable.bm2);
                                break;
                            case (3):
                                img.setImageResource(R.drawable.bm3);
                                break;
                        }
                        break;
                    case("РИО"):
                        switch (choose[selectedItemPosition]) {
                            case (1):
                                img.setImageResource(R.drawable.rio1);
                                break;
                            case (2):
                                img.setImageResource(R.drawable.rio2);
                                break;
                            case (3):
                                img.setImageResource(R.drawable.rio3);
                                break;
                            case (4):
                                img.setImageResource(R.drawable.rio4);
                                break;
                            case (5):
                                img.setImageResource(R.drawable.rio5);
                                break;
                            case(6):
                                img.setImageResource(R.drawable.rio6);
                        }
                        break;
                }
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Выбран этаж: " + choose[selectedItemPosition], Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}


