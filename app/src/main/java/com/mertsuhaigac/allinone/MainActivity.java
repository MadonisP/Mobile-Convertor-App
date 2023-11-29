package com.mertsuhaigac.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button convertorButton= findViewById(R.id.converterButton) ;
        Button randomButton= findViewById(R.id.randomButton) ;
        Button smsButton= findViewById(R.id.smsButton) ;

        convertorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContext();
            }});
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRandom();
            }});
        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSms();
            }});

    }

    private void openContext(){
        Intent intent=new Intent(this, Convertor.class);
        startActivity(intent);
    }

    private void openRandom(){
        Intent intent=new Intent(this, Random.class);
        startActivity(intent);
    }
    private void openSms(){
        Intent intent=new Intent(this, Sms.class);
        startActivity(intent);
    }
}