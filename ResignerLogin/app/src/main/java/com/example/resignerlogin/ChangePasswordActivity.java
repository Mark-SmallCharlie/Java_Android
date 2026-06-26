package com.example.resignerlogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText etOldPassword, etNewPassword, etConfirmPassword;
    private Button btnConfirmChange, btnCancel;
    private String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        studentId = getIntent().getStringExtra("studentId");
        if (studentId == null || studentId.isEmpty()) {
            Toast.makeText(this, "信息丢失，请重新登录", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnConfirmChange = findViewById(R.id.btnConfirmChange);
        btnCancel = findViewById(R.id.btnCancel);

        // 确认修改
        btnConfirmChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = etOldPassword.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "请填写所有密码字段", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(ChangePasswordActivity.this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (newPassword.length() < 6) {
                    Toast.makeText(ChangePasswordActivity.this, "新密码长度不能少于6位", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean success = DatabaseHelper.changePassword(
                        ChangePasswordActivity.this, studentId, oldPassword, newPassword);

                if (success) {
                    Toast.makeText(ChangePasswordActivity.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "旧密码不正确，修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 返回
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
