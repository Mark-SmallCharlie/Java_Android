package com.example.textviews2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_one,btn_two,btn_three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_one =findViewById(R.id.button1);
        btn_two =findViewById(R.id.button2);
        btn_three =findViewById(R.id.button3);

    }
}