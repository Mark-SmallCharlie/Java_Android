package com.example.loginview;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.loginview.dialog.EquipmentDialogFragment;
import com.example.loginview.fragment.EquipmentFragment;
import com.example.loginview.model.Equipment;
import com.example.loginview.model.User;
import com.example.loginview.util.EquipmentDatabaseHelper;
import com.example.loginview.util.UserPreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements
        EquipmentFragment.OnEquipmentActionListener,
        EquipmentDialogFragment.OnEquipmentSavedListener {

    private CircleImageView ivAvatar;
    private TextView tvUserName;
    private TextView tvUserRole;
    private FloatingActionButton fabAdd;
    private BottomNavigationView bottomNavigation;

    private UserPreferences userPreferences;
    private EquipmentDatabaseHelper dbHelper;
    private String currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userPreferences = new UserPreferences(this);
        dbHelper = new EquipmentDatabaseHelper(this);

        initViews();
        setupUserInfo();
        setupNavigation();
    }

    private void initViews() {
        ivAvatar = findViewById(R.id.iv_avatar);
        tvUserName = findViewById(R.id.tv_user_name);
        tvUserRole = findViewById(R.id.tv_user_role);
        fabAdd = findViewById(R.id.fab_add);
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }

    private void setupUserInfo() {
        User user = userPreferences.getUser();
        if (user != null) {
            tvUserName.setText(user.getName());
            tvUserRole.setText(user.getRole());

            // Set avatar based on gender
            if ("female".equals(user.getGender())) {
                ivAvatar.setImageResource(R.drawable.ic_avatar_default);
            }
        }
    }

    private void setupNavigation() {
        fabAdd.setOnClickListener(v -> showAddDialog());

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_production) {
                switchFragment(EquipmentDatabaseHelper.CATEGORY_PRODUCTION);
                return true;
            } else if (itemId == R.id.nav_testing) {
                switchFragment(EquipmentDatabaseHelper.CATEGORY_TESTING);
                return true;
            } else if (itemId == R.id.nav_packaging) {
                switchFragment(EquipmentDatabaseHelper.CATEGORY_PACKAGING);
                return true;
            } else if (itemId == R.id.nav_storage) {
                switchFragment(EquipmentDatabaseHelper.CATEGORY_STORAGE);
                return true;
            }
            return false;
        });

        // Default to production
        switchFragment(EquipmentDatabaseHelper.CATEGORY_PRODUCTION);
    }

    private void switchFragment(String category) {
        currentCategory = category;
        EquipmentFragment fragment = EquipmentFragment.newInstance(category);
        fragment.setOnEquipmentActionListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void showAddDialog() {
        EquipmentDialogFragment dialog = EquipmentDialogFragment.newInstance(currentCategory);
        dialog.setOnEquipmentSavedListener(this);
        dialog.show(getSupportFragmentManager(), "add_equipment");
    }

    @Override
    public void onEditEquipment(Equipment equipment) {
        EquipmentDialogFragment dialog = EquipmentDialogFragment.newInstance(equipment);
        dialog.setOnEquipmentSavedListener(this);
        dialog.show(getSupportFragmentManager(), "edit_equipment");
    }

    @Override
    public void onDeleteEquipment(Equipment equipment) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_delete_title)
                .setMessage(String.format(getString(R.string.dialog_delete_message), equipment.getName()))
                .setPositiveButton(R.string.btn_confirm, (dialog, which) -> {
                    dbHelper.deleteEquipment(equipment.getId());
                    Toast.makeText(this, R.string.toast_delete_success, Toast.LENGTH_SHORT).show();
                    refreshCurrentFragment();
                })
                .setNegativeButton(R.string.btn_cancel, null)
                .show();
    }

    @Override
    public void onEquipmentSaved() {
        refreshCurrentFragment();
    }

    private void refreshCurrentFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment instanceof EquipmentFragment) {
            ((EquipmentFragment) fragment).loadData();
        }
    }
}
