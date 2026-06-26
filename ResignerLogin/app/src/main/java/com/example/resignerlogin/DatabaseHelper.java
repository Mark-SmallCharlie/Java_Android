package com.example.resignerlogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地存储工具类（SharedPreferences），替代原来的 SQL Server JDBC 连接。
 */
public class DatabaseHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String PREFS_NAME = "StudentDB";

    // ==================== 数据库连接（已注释） ====================

    // ==================== 本地存储实现 ====================

    /**
     * 注册学生信息到本地 SharedPreferences
     */
    public static boolean registerStudent(Context context, String studentId, String name, String email,
                                          String password, String gender, String hobbies) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            prefs.edit()
                    .putString("pwd_" + studentId, password)
                    .putString("info_" + studentId, name + "|" + email + "|" + gender + "|" + hobbies)
                    .apply();

            Log.d(TAG, "本地注册成功，学号：" + studentId);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "本地注册失败", e);
            return false;
        }
    }

    /**
     * 验证登录（从 SharedPreferences 读取）
     */
    public static boolean verifyLogin(Context context, String studentId, String password) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String savedPassword = prefs.getString("pwd_" + studentId, null);
            boolean success = savedPassword != null && savedPassword.equals(password);
            Log.d(TAG, "本地登录验证：" + (success ? "成功" : "失败") + "，学号：" + studentId);
            return success;
        } catch (Exception e) {
            Log.e(TAG, "本地登录验证失败", e);
            return false;
        }
    }

    /**
     * 获取用户信息（返回 name, email, gender, hobbies 的 Map）
     */
    public static Map<String, String> getUserInfo(Context context, String studentId) {
        Map<String, String> info = new HashMap<>();
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String raw = prefs.getString("info_" + studentId, null);
            if (raw != null) {
                String[] parts = raw.split("\\|", 4);
                if (parts.length >= 1) info.put("name", parts[0]);
                if (parts.length >= 2) info.put("email", parts[1]);
                if (parts.length >= 3) info.put("gender", parts[2]);
                if (parts.length >= 4) info.put("hobbies", parts[3]);
            }
            Log.d(TAG, "获取用户信息，学号：" + studentId + "，数据：" + (raw != null ? "有" : "无"));
        } catch (Exception e) {
            Log.e(TAG, "获取用户信息失败", e);
        }
        return info;
    }

    /**
     * 修改密码（验证旧密码后更新为新密码）
     */
    public static boolean changePassword(Context context, String studentId,
                                         String oldPassword, String newPassword) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String savedPassword = prefs.getString("pwd_" + studentId, null);
            if (savedPassword == null || !savedPassword.equals(oldPassword)) {
                Log.d(TAG, "修改密码失败：旧密码不正确");
                return false;
            }
            prefs.edit().putString("pwd_" + studentId, newPassword).apply();
            Log.d(TAG, "密码修改成功，学号：" + studentId);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "修改密码失败", e);
            return false;
        }
    }
}
