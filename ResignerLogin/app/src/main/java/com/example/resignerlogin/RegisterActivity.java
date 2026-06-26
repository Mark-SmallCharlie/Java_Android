package com.example.resignerlogin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword;
    private RadioGroup rgGender;
    private CheckBox cbSinging, cbDancing, cbReading;
    private TextView tvStudentId;
    private Button btnConfirm, btnBack;
    private boolean isConfirmed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 初始化控件
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        rgGender = findViewById(R.id.rgGender);
        cbSinging = findViewById(R.id.cbSinging);
        cbDancing = findViewById(R.id.cbDancing);
        cbReading = findViewById(R.id.cbReading);
        tvStudentId = findViewById(R.id.tvStudentId);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnBack = findViewById(R.id.btnBack);

        // 确定按钮点击事件
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConfirmed) {
                    Toast.makeText(RegisterActivity.this, "已确认，请勿重复点击", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 验证输入
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (rgGender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(RegisterActivity.this, "请选择性别", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 生成随机学号
                String studentId = generateStudentId();
                String gender = rgGender.getCheckedRadioButtonId() == R.id.rbMale ? "男" : "女";

                StringBuilder hobbiesBuilder = new StringBuilder();
                if (cbSinging.isChecked()) hobbiesBuilder.append("唱歌 ");
                if (cbDancing.isChecked()) hobbiesBuilder.append("跳舞 ");
                if (cbReading.isChecked()) hobbiesBuilder.append("看书 ");
                String hobbies = hobbiesBuilder.toString().trim();

                // 禁用按钮，防止重复点击
                isConfirmed = true;
                btnConfirm.setEnabled(false);
                btnConfirm.setText("已确认");

                // 显示学号
                tvStudentId.setText("学号：" + studentId);

                // 保存到本地
                DatabaseHelper.registerStudent(
                        RegisterActivity.this, studentId, name, email, password, gender, hobbies);

                // 显示结果并跳转到主页
                Toast.makeText(RegisterActivity.this, "注册成功！学号：" + studentId, Toast.LENGTH_LONG).show();

                // 延迟跳转到个人主页（给 Toast 显示时间）
                final String finalStudentId = studentId;
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        intent.putExtra("studentId", finalStudentId);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }, 1500);
            }
        });

        // 返回按钮点击事件
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String generateStudentId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
