package com.example.resignerlogin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Toast;
import java.util.Random;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private RadioGroup rgGender;
    private CheckBox cbSinging, cbDancing, cbReading, cbShowPassword;
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
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        rgGender = findViewById(R.id.rgGender);
        cbSinging = findViewById(R.id.cbSinging);
        cbDancing = findViewById(R.id.cbDancing);
        cbReading = findViewById(R.id.cbReading);
        cbShowPassword = findViewById(R.id.cbShowPassword);
        tvStudentId = findViewById(R.id.tvStudentId);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnBack = findViewById(R.id.btnBack);

        // 显示/隐藏密码
        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                // 光标移到末尾
                etPassword.setSelection(etPassword.getText().length());
                etConfirmPassword.setSelection(etConfirmPassword.getText().length());
            }
        });

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
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 验证姓名长度
                if (name.length() < 2 || name.length() > 6) {
                    Toast.makeText(RegisterActivity.this, "姓名长度需在2到6个字符之间", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 验证邮箱格式
                String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
                if (!Pattern.matches(emailRegex, email)) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的邮箱地址", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 验证密码安全强度
                if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "密码长度不能少于6个字符", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean hasUpper = Pattern.matches(".*[A-Z].*", password);
                boolean hasLower = Pattern.matches(".*[a-z].*", password);
                boolean hasSpecial = Pattern.matches(".*[^A-Za-z0-9].*", password);
                if (!hasUpper || !hasLower || !hasSpecial) {
                    Toast.makeText(RegisterActivity.this, "密码必须包含大写字母、小写字母和特殊字符", Toast.LENGTH_SHORT).show();
                    return;
                }
                //验证双重密码
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
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
        StringBuilder sb = new StringBuilder("2026");
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
