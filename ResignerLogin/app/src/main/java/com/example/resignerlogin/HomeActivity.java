package com.example.resignerlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome, tvInfoStudentId, tvInfoName, tvInfoEmail, tvInfoGender, tvInfoHobbies;
    private Button btnChangePassword, btnLogout;
    private String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 获取传入的学号
        studentId = getIntent().getStringExtra("studentId");
        if (studentId == null || studentId.isEmpty()) {
            Toast.makeText(this, "登录信息丢失，请重新登录", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 初始化控件
        tvWelcome = findViewById(R.id.tvWelcome);
        tvInfoStudentId = findViewById(R.id.tvInfoStudentId);
        tvInfoName = findViewById(R.id.tvInfoName);
        tvInfoEmail = findViewById(R.id.tvInfoEmail);
        tvInfoGender = findViewById(R.id.tvInfoGender);
        tvInfoHobbies = findViewById(R.id.tvInfoHobbies);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnLogout = findViewById(R.id.btnLogout);

        // 加载用户信息
        loadUserInfo();

        // 修改密码
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ChangePasswordActivity.class);
                intent.putExtra("studentId", studentId);
                startActivity(intent);
            }
        });

        // 退出登录
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 从修改密码页返回时刷新信息
        loadUserInfo();
    }

    private void loadUserInfo() {
        Map<String, String> info = DatabaseHelper.getUserInfo(this, studentId);
        String name = info.getOrDefault("name", "未知");
        tvWelcome.setText("欢迎，" + name + " 同学！");
        tvInfoStudentId.setText(studentId);
        tvInfoName.setText(info.getOrDefault("name", "未知"));
        tvInfoEmail.setText(info.getOrDefault("email", "未知"));
        tvInfoGender.setText(info.getOrDefault("gender", "未知"));
        tvInfoHobbies.setText(info.getOrDefault("hobbies", "无"));
    }
}
