package com.example.loginview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.loginview.model.User;
import com.example.loginview.util.UserPreferences;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextInputEditText etConfirmPassword;
    private RadioGroup rgGender;
    private MaterialButton btnSubmit;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userPreferences = new UserPreferences(this);
        initViews();
        initListeners();
    }

    private void initViews() {
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_reg_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        rgGender = findViewById(R.id.rg_gender);
        btnSubmit = findViewById(R.id.btn_submit);

        findViewById(R.id.tv_back_to_login).setOnClickListener(v -> finish());
    }

    private void initListeners() {
        btnSubmit.setOnClickListener(v -> {
            if (validateForm()) {
                saveUserInfo();
                showRegisterSuccess();
            }
        });
    }

    private boolean validateForm() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError(getString(R.string.error_empty_name));
            return false;
        }
        if (email.isEmpty()) {
            etEmail.setError(getString(R.string.error_empty_email));
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.error_invalid_email));
            return false;
        }
        if (password.length() < 6) {
            etPassword.setError(getString(R.string.error_password_short));
            return false;
        }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError(getString(R.string.error_password_mismatch));
            return false;
        }
        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, R.string.error_no_gender, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void saveUserInfo() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String gender = rgGender.getCheckedRadioButtonId() == R.id.rb_male ? "male" : "female";

        User user = new User(name, email, gender, password);
        userPreferences.saveUser(user);
    }

    private void showRegisterSuccess() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.register_success_title)
                .setMessage(R.string.register_success_message)
                .setCancelable(false)
                .setPositiveButton(R.string.btn_back_to_login, (dialog, which) -> {
                    Toast.makeText(this, R.string.toast_user_saved, Toast.LENGTH_SHORT).show();
                    finish();
                })
                .show();
    }
}
