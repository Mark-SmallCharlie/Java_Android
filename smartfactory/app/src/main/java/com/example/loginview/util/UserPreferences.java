package com.example.loginview.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.loginview.model.User;

public class UserPreferences {
    private static final String PREF_NAME = "user_prefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ROLE = "role";
    private static final String KEY_REGISTERED = "registered";

    private final SharedPreferences prefs;

    public UserPreferences(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(User user) {
        prefs.edit()
                .putString(KEY_NAME, user.getName())
                .putString(KEY_EMAIL, user.getEmail())
                .putString(KEY_GENDER, user.getGender())
                .putString(KEY_PASSWORD, user.getPassword())
                .putString(KEY_ROLE, user.getRole())
                .putBoolean(KEY_REGISTERED, true)
                .apply();
    }

    public User getUser() {
        if (!isRegistered()) return null;
        User user = new User();
        user.setName(prefs.getString(KEY_NAME, ""));
        user.setEmail(prefs.getString(KEY_EMAIL, ""));
        user.setGender(prefs.getString(KEY_GENDER, ""));
        user.setPassword(prefs.getString(KEY_PASSWORD, ""));
        user.setRole(prefs.getString(KEY_ROLE, "管理员"));
        return user;
    }

    public boolean isRegistered() {
        return prefs.getBoolean(KEY_REGISTERED, false);
    }

    public boolean validateLogin(String email, String password) {
        if (!isRegistered()) return false;
        String savedEmail = prefs.getString(KEY_EMAIL, "");
        String savedPassword = prefs.getString(KEY_PASSWORD, "");
        return savedEmail.equals(email) && savedPassword.equals(password);
    }

    public void clearUser() {
        prefs.edit().clear().apply();
    }
}
