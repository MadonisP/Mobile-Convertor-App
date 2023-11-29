package com.mertsuhaigac.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class Convertor extends AppCompatActivity {
private EditText decimalInput;
    private EditText byteInput;
    private EditText heatInput;
    private TextView decimalAnswer;
    private TextView byteAnswer;
    private TextView heatAnswer;
    private Spinner decimalSpinner;
    private Spinner byteSpinner;

    private RadioButton fahrenayt;
    private RadioButton kelvin;

    private String input1;
    private String input2;
    private String input3;

    private String spinnerdeci;
    private String spinnerbyte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);

        decimalInput = findViewById(R.id.decimalInput);
        byteInput = findViewById(R.id.byteInput);
        heatInput = findViewById(R.id.heatInput);
        decimalSpinner = findViewById(R.id.spinnerForDecimal);
        byteSpinner = findViewById(R.id.spinnerForByte);
        byteAnswer = findViewById(R.id.byteAnswer);
        decimalAnswer = findViewById(R.id.decimalAnswer);
        fahrenayt= findViewById(R.id.fahrenaytButton);
        kelvin= findViewById(R.id.kelvinButton);
        heatAnswer = findViewById(R.id.heatAnswer);

        decimalInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //sonra ekle
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    input1= charSequence.toString();
                    performConvert();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //sonra ekle
            }
        });

        byteInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //sonra ekle
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                input2= charSequence.toString();
                performByte();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //sonra ekle
            }
        });

        heatInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //sonra ekle
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                input3= charSequence.toString();
                performHeat();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //sonra ekle
            }
        });

        String[] base = {"İkilik", "Sekizlik", "Onaltılık"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, base);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        decimalSpinner.setAdapter(adapter);

        decimalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedBase = decimalSpinner.getSelectedItem().toString();
                spinnerdeci=selectedBase;
                performConvert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //todo
            }
        });

        String[] base2 = {"kilo byte", "byte", "kibi byte","bit"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, base2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        byteSpinner.setAdapter(adapter2);

        byteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedBase2 = byteSpinner.getSelectedItem().toString();
                spinnerbyte=selectedBase2;
                performByte();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //todo
            }
        });

        fahrenayt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fahrenayt.setChecked(true);
                kelvin.setChecked(false);
                performHeat();

            }
        });


        kelvin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kelvin.setChecked(true);
                fahrenayt.setChecked(false);
                performHeat();

            }
        });


    }
    private void performConvert(){
        if(input1 != null && !input1.isEmpty()){
            double decimal = Double.parseDouble((input1));

            String result="";

            switch(spinnerdeci){
                case "Sekizlik":
                    result= decimalToOctal(decimal);
                    break;
                case "Onaltılık":
                    result= decimalToHexaDecimal(decimal);
                    break;
                default:
                    result= decimalToBinary(decimal);
                    break;
            }

            if(result != ""){
                decimalAnswer.setText(String.valueOf(result));
            } else{
                Log.e("Convewrtyor","result null döndü convertor'da");
            }

        }
    }

    private void performByte(){
        if(input2 != null && !input2.isEmpty()){
            double mb = Double.parseDouble((input2));

            String result="";

            switch(spinnerbyte){
                case "byte":
                    result= mbToByte(mb);
                    break;
                case "kibi byte":
                    result= mbKibiByte(mb);
                    break;
                case "bit":
                    result= mbToBit(mb);
                    break;
                default:
                    result= mbToKb(mb);
                    break;
            }

            if(result != ""){
                byteAnswer.setText(String.valueOf(result));
            } else{
                Log.e("Convewrtyor","result null döndü convertor'da");
            }

        }
    }

    private void performHeat() {
        if (input3 != null && !input3.isEmpty()) {
            double C = Double.parseDouble(input3);

            String result = "";

            if(kelvin.isChecked()){
                result = celciusToFahre(C);

            }

            if(fahrenayt.isChecked()){
                result = celciusToKelvin(C);
            }

            if (result != "") {
                heatAnswer.setText(String.valueOf(result));
            } else {
                Log.e("ConverterActivity", "heatAnswer null!");
            }
        }
    }

    private String celciusToFahre(double C){
        double Fahre= (double) (C*33.8);
        return Double.toString(Fahre);
    }

    private String celciusToKelvin(double C){
        double kel= (double) (C*274.15);
        return Double.toString(kel);
    }

    private String mbToByte(double mb){
        if(mb<0|| mb!= (int)mb ){
            return "yanlıs";
        }

        long Byte= (long) (mb*1000000);
        return Long.toString(Byte);
    }
    private String mbKibiByte(double mb){
        if(mb<0|| mb!= (int)mb ){
            return "yanlıs";
        }

        double Kibi= (double) (mb*7812.5);
        return Double.toString(Kibi);
    }

    private String mbToBit(double mb){
        if(mb<0|| mb!= (int)mb ){
            return "yanlıs";
        }

        long Bit= (long) (mb*8000000);
        return Long.toString(Bit);
    }
    private String mbToKb(double mb){
        if(mb<0|| mb!= (int)mb ){
            return "yanlıs";
        }

        long kb= (long) (mb*1000);
        return Long.toString(kb);
    }
    private String  decimalToBinary(double decimalValue) {
        if (decimalValue < 0 || decimalValue != (int) decimalValue) {
            return "Invalid input. Please enter a non-negative integer.";
        }

        // Convert decimal to binary
        StringBuilder binaryResult = new StringBuilder();
        int intValue = (int) decimalValue;

        if (intValue == 0) {
            // If the decimal value is 0, binary representation is also 0
            binaryResult.append("0");
        } else {
            // Perform binary conversion
            while (intValue > 0) {
                int remainder = intValue % 2;
                binaryResult.insert(0, remainder);
                intValue /= 2;
            }
        }

        return binaryResult.toString();
    }

    private String decimalToOctal(double decimalValue) {
        if (decimalValue < 0 || decimalValue != (int) decimalValue) {
            return "Invalid input. ";
        }

        StringBuilder octalResult = new StringBuilder();
        int intValue = (int) decimalValue;

        if (intValue == 0) {
            octalResult.append("0");
        } else {
            while (intValue > 0) {
                int remainder = intValue % 8;
                octalResult.insert(0, remainder);
                intValue /= 8;
            }
        }

        return octalResult.toString();
    }

    private String decimalToHexaDecimal(double decimalValue) {
        if (decimalValue < 0 || decimalValue != (int) decimalValue) {
            return "Invalid input. Please enter a non-negative integer.";
        }

        StringBuilder hexadecimalResult = new StringBuilder();
        int intValue = (int) decimalValue;

        if (intValue == 0) {
            hexadecimalResult.append("0");
        } else {
            while (intValue > 0) {
                int remainder = intValue % 16;
                hexadecimalResult.insert(0, remainder);
                intValue /= 16;
            }
        }
        return hexadecimalResult.toString();
    }

}