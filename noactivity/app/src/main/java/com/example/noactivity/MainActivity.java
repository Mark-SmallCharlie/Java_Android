package com.example.noactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginClick(View view) {

        EditText txtAccount = findViewById(R.id.txtAccount);
        String account = txtAccount.getText().toString();

        EditText txtPassword = findViewById(R.id.txtPassword);
        String password = txtPassword.getText().toString();

        if ("admin".equals(account)&&"1234".equals(password))
        {
            Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(intent);
        }
        else
        {
            Toast toast=Toast.makeText(getApplicationContext(),
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}