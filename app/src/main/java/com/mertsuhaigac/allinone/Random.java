package com.mertsuhaigac.allinone;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Random extends AppCompatActivity {
private EditText editTextAdet    , textViewMin,textViewMax;
private LinearLayout parentLayout;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        editTextAdet = findViewById(R.id.editTextNumber);
        textViewMin = findViewById(R.id.editTextNumber3);
        textViewMax = findViewById(R.id.editTextNumber2);
        parentLayout = findViewById(R.id.parentLayout);
        progressBar = findViewById(R.id.idPBLoading);


        editTextAdet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculateValues();
            }
        });
        textViewMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculateValues();
            }
        });
        textViewMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculateValues();
            }
        });


    }

    private void calculateValues(){
        int adet;

        try{
            adet = Integer.parseInt(editTextAdet.getText().toString());
        } catch (NumberFormatException e){
            adet=0;
        }
        int maks;

        try{
            maks = Integer.parseInt(textViewMax.getText().toString());
        } catch (NumberFormatException e){
            maks=100;
        }
        int min;

        try{
            min = Integer.parseInt(textViewMin.getText().toString());
        } catch (NumberFormatException e){
            min=0;
        }


        parentLayout.removeAllViews();

        java.util.Random random = new java.util.Random();

        for (int i = 0; i < adet; i++) {
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.VERTICAL);
            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            TextView textViewMinValue = new TextView(this);
            textViewMinValue.setText("Min: " + String.valueOf(min));
            rowLayout.addView(textViewMinValue);

            ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            progressBar.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            progressBar.setMax(maks);
            progressBar.setProgress(random.nextInt((maks - min) + 1) + min);
            rowLayout.addView(progressBar);

            TextView textViewMaxValue = new TextView(this);
            textViewMaxValue.setText("Max: " + String.valueOf(maks));
            rowLayout.addView(textViewMaxValue);

            parentLayout.addView(rowLayout);
        }
    }
}