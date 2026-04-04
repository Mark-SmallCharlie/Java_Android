package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.todo.data.ToDoDb;
import com.example.todo.data.ToDoItem;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void cancel(View view) {
        finish();
    }

    public void selectDate(View view) {
        DatePickerDialog dialog=new DatePickerDialog(this);
        DatePicker picker=dialog.getDatePicker();
        Calendar calendar=Calendar.getInstance();
        picker.setMinDate(calendar.getTimeInMillis());
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date=String.format("%d-%02d-%02d",year,month+1,dayOfMonth);
                EditText txtDate=findViewById(R.id.txtDate);
                txtDate.setText(date);
            }
        });
        dialog.show();
    }

    public void selectTime(View view) {
        TimePickerDialog dialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time=String.format("%02d:%02d",hourOfDay,minute);
                EditText txtTime=findViewById(R.id.txtTime);
                txtTime.setText(time);
            }
        },12,0,false);
        dialog.show();
    }

    public void save(View view){
        EditText txtDesc=findViewById(R.id.txtDesc);
        EditText txtLocation=findViewById(R.id.txtLocation);
        EditText txtPerson=findViewById(R.id.txtPerson);
        EditText txtDate=findViewById(R.id.txtDate);
        EditText txtTime=findViewById(R.id.txtTime);
        RadioGroup rGroup=findViewById(R.id.rgLevel);
        int rbId=rGroup.getCheckedRadioButtonId();
        if(rbId<0) {
            Toast.makeText(this,"请选择优先级。",Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rb=findViewById(rbId);

        ToDoItem item=new ToDoItem();
        item.description=txtDesc.getText().toString();
        if(item.description.trim().equals("")){
            Toast.makeText(this,"请填写待办事件。",Toast.LENGTH_LONG).show();
            return;
        }
        item.deadline=txtDate.getText().toString()+" "+txtTime.getText().toString();
        item.location=txtLocation.getText().toString();
        item.person=txtPerson.getText().toString();
        item.level=rb.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ToDoDb.get().toDoDao().add(item);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddActivity.this,"待办事项保存成功",Toast.LENGTH_LONG).show();
                    }
                });
                finish();
            }
        }).start();
    }
}