package com.example.end_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
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
    ImageButton thm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView helper = findViewById(R.id.helper);

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

    private void openBottomSheetfragment() {
        ArrayList<Advices> adv = new ArrayList<>();
        adv.add(new Advices("Если Вы услышали предупреждение системы безопасности необходимо спокойно следовать к ближайшему выходу, путь отмечен специальными стрелками на пути эвакуации"));
        adv.add(new Advices("Оказавшись в давке, согните руки в локтях и прижмите их к бокам, сжав кулаки; защищайте бока от сдавливания. Наклоните корпус назад, уперев ноги спереди, и попытайтесь сдерживать напор спиной"));
        adv.add(new Advices("Если Вас сбили с ног, постарайтесь встать на колено и, опираясь о пол руками, другой ногой резко оттолкнитесь, рывком выпрямите тело. Заслоняйте детей спиной или посадите их к себе на плечи"));
        adv.add(new Advices("Если Вы находитесь в момент срабатывании пожарной сигнализации в лифте, не переживайте все лифты спускаются на 1 этаж и открываются, это сделано для безопасности граждан"));
        adv.add(new Advices("Если Вы находитесь на верхних этажах, не ждите лифт, панель будет не работоспособна, поэтому следует воспользоваться незадымляемыми лестничными клетками, эвак. выходами"));
        adv.add(new Advices("Во время эвакуации посетителям не следует подниматься по лестницам вверх, так как дым изначально заполняет верхние этажи — нужно выйти из здания на открытую территорию"));
        adv.add(new Advices("Если имеется возможность справиться с огнем (нет угрозы для Вашей жизни), немедленно оповестите об этом окружающих, потушите пожар, привлекая на помощь находящихся рядом людей"));
        adv.add(new Advices("Если пути эвакуации отрезаны, постарайтесь выбраться на крышу или подойти к окнам, чтобы Вас заметили спасатели и провели эвакуацию. Не прыгайте в окно с большой высоты"));
        BottomSheet bottomSheet = new BottomSheet(adv, new AClickListener() {
            @Override
            public void clickItem(Advices advice) {
                Toast.makeText(getApplicationContext(), "Главное - не паникуйте!", Toast.LENGTH_SHORT).show();
            }
        });
        bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
    }
}