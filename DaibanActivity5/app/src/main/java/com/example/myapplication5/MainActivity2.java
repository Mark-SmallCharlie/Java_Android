package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onCancel(View view) {
        finish();
    }

    public void onSave(View view) {

    }

    public void selectDate(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this);
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = String.format("%d-%0d-%0d", year, month, dayOfMonth);
                EditText txtDate = findViewById(R.id.txtDate);
                txtDate.setText(date);
            }
        });
        dialog.show();
    }

    public void selectTime(View view) {
        TimePickerDialog dialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time =String.format("%2d:%2d",hourOfDay,minute);
                EditText txtTime=findViewById(R.id.txtTime);
                txtTime.setText(time);
            }
        },0,0,true);
        dialog.show();
    }
}