package com.example.loginview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.example.loginview.util.UserPreferences;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etAccount;
    private TextInputEditText etPassword;
    private MaterialButton btnLogin;
    private MaterialButton btnRegister;
    private View llQq;
    private View llWechat;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPreferences = new UserPreferences(this);
        initViews();
        initListeners();
    }

    private void initViews() {
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        llQq = findViewById(R.id.ll_qq);
        llWechat = findViewById(R.id.ll_wechat);
    }

    private void initListeners() {
        btnLogin.setOnClickListener(v -> {
            String account = etAccount.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (account.isEmpty()) {
                etAccount.setError(getString(R.string.toast_empty_account));
                return;
            }
            if (password.isEmpty()) {
                etPassword.setError(getString(R.string.toast_empty_password));
                return;
            }

            if (!userPreferences.isRegistered()) {
                Toast.makeText(MainActivity.this, R.string.toast_please_register, Toast.LENGTH_SHORT).show();
                return;
            }

            if (userPreferences.validateLogin(account, password)) {
                Toast.makeText(MainActivity.this, R.string.toast_login_success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, R.string.toast_login_failed, Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        llQq.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, R.string.toast_qq_login, Toast.LENGTH_SHORT).show());

        llWechat.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, R.string.toast_wechat_login, Toast.LENGTH_SHORT).show());
    }
}
