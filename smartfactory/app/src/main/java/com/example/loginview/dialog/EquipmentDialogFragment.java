package com.example.loginview.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.loginview.R;
import com.example.loginview.model.Equipment;
import com.example.loginview.util.EquipmentDatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;

public class EquipmentDialogFragment extends DialogFragment {

    private static final String ARG_EQUIPMENT_ID = "equipment_id";
    private static final String ARG_EQUIPMENT_NAME = "equipment_name";
    private static final String ARG_EQUIPMENT_CODE = "equipment_code";
    private static final String ARG_EQUIPMENT_CATEGORY = "equipment_category";
    private static final String ARG_EQUIPMENT_DAMAGE = "equipment_damage";
    private static final String ARG_EQUIPMENT_USAGE = "equipment_usage";
    private static final String ARG_EQUIPMENT_PRICE = "equipment_price";
    private static final String ARG_IS_EDIT = "is_edit";

    private TextInputEditText etName;
    private TextInputEditText etCode;
    private Spinner spinnerCategory;
    private Spinner spinnerDamage;
    private TextInputEditText etUsage;
    private TextInputEditText etPrice;

    private EquipmentDatabaseHelper dbHelper;
    private String currentCategory;
    private Equipment editingEquipment;
    private boolean isEditMode = false;

    public interface OnEquipmentSavedListener {
        void onEquipmentSaved();
    }

    private OnEquipmentSavedListener savedListener;

    public static EquipmentDialogFragment newInstance(String category) {
        EquipmentDialogFragment fragment = new EquipmentDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EQUIPMENT_CATEGORY, category);
        args.putBoolean(ARG_IS_EDIT, false);
        fragment.setArguments(args);
        return fragment;
    }

    public static EquipmentDialogFragment newInstance(Equipment equipment) {
        EquipmentDialogFragment fragment = new EquipmentDialogFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_EQUIPMENT_ID, equipment.getId());
        args.putString(ARG_EQUIPMENT_NAME, equipment.getName());
        args.putString(ARG_EQUIPMENT_CODE, equipment.getCode());
        args.putString(ARG_EQUIPMENT_CATEGORY, equipment.getCategory());
        args.putString(ARG_EQUIPMENT_DAMAGE, equipment.getDamageLevel());
        args.putInt(ARG_EQUIPMENT_USAGE, equipment.getUsageHours());
        args.putDouble(ARG_EQUIPMENT_PRICE, equipment.getMarketPrice());
        args.putBoolean(ARG_IS_EDIT, true);
        fragment.setArguments(args);
        return fragment;
    }

    public void setOnEquipmentSavedListener(OnEquipmentSavedListener listener) {
        this.savedListener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new EquipmentDatabaseHelper(requireContext());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(requireContext())
                .inflate(R.layout.dialog_equipment_form, null);

        initViews(view);
        setupSpinners();

        if (getArguments() != null) {
            isEditMode = getArguments().getBoolean(ARG_IS_EDIT, false);
            currentCategory = getArguments().getString(ARG_EQUIPMENT_CATEGORY);

            if (isEditMode) {
                populateFields();
            }
        }

        String title = isEditMode ? getString(R.string.edit_equipment) : getString(R.string.add_equipment);

        return new AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setView(view)
                .setPositiveButton(R.string.btn_save, (dialog, which) -> saveEquipment())
                .setNegativeButton(R.string.btn_cancel, null)
                .create();
    }

    private void initViews(View view) {
        etName = view.findViewById(R.id.et_name);
        etCode = view.findViewById(R.id.et_code);
        spinnerCategory = view.findViewById(R.id.spinner_category);
        spinnerDamage = view.findViewById(R.id.spinner_damage);
        etUsage = view.findViewById(R.id.et_usage);
        etPrice = view.findViewById(R.id.et_price);
    }

    private void setupSpinners() {
        String[] categories = {
                EquipmentDatabaseHelper.CATEGORY_PRODUCTION,
                EquipmentDatabaseHelper.CATEGORY_TESTING,
                EquipmentDatabaseHelper.CATEGORY_PACKAGING,
                EquipmentDatabaseHelper.CATEGORY_STORAGE
        };
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        String[] damageLevels = {
                getString(R.string.damage_good),
                getString(R.string.damage_minor),
                getString(R.string.damage_moderate),
                getString(R.string.damage_severe)
        };
        ArrayAdapter<String> damageAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, damageLevels);
        damageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDamage.setAdapter(damageAdapter);
    }

    private void populateFields() {
        Bundle args = getArguments();
        if (args == null) return;

        etName.setText(args.getString(ARG_EQUIPMENT_NAME));
        etCode.setText(args.getString(ARG_EQUIPMENT_CODE));
        etUsage.setText(String.valueOf(args.getInt(ARG_EQUIPMENT_USAGE)));
        etPrice.setText(String.valueOf(args.getDouble(ARG_EQUIPMENT_PRICE)));

        String category = args.getString(ARG_EQUIPMENT_CATEGORY);
        String[] categories = {
                EquipmentDatabaseHelper.CATEGORY_PRODUCTION,
                EquipmentDatabaseHelper.CATEGORY_TESTING,
                EquipmentDatabaseHelper.CATEGORY_PACKAGING,
                EquipmentDatabaseHelper.CATEGORY_STORAGE
        };
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(category)) {
                spinnerCategory.setSelection(i);
                break;
            }
        }

        String damage = args.getString(ARG_EQUIPMENT_DAMAGE);
        String[] damageLevels = {
                getString(R.string.damage_good),
                getString(R.string.damage_minor),
                getString(R.string.damage_moderate),
                getString(R.string.damage_severe)
        };
        for (int i = 0; i < damageLevels.length; i++) {
            if (damageLevels[i].equals(damage)) {
                spinnerDamage.setSelection(i);
                break;
            }
        }
    }

    private void saveEquipment() {
        String name = etName.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String usageStr = etUsage.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();

        if (name.isEmpty() || code.isEmpty() || usageStr.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(requireContext(), R.string.toast_fill_required, Toast.LENGTH_SHORT).show();
            return;
        }

        int usageHours;
        double price;
        try {
            usageHours = Integer.parseInt(usageStr);
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), R.string.toast_fill_required, Toast.LENGTH_SHORT).show();
            return;
        }

        String category = (String) spinnerCategory.getSelectedItem();
        String damage = (String) spinnerDamage.getSelectedItem();

        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setCode(code);
        equipment.setCategory(category);
        equipment.setDamageLevel(damage);
        equipment.setUsageHours(usageHours);
        equipment.setMarketPrice(price);
        equipment.setImageResId(R.drawable.ic_equipment);

        if (isEditMode) {
            equipment.setId(getArguments().getLong(ARG_EQUIPMENT_ID));
            dbHelper.updateEquipment(equipment);
            Toast.makeText(requireContext(), R.string.toast_update_success, Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.insertEquipment(equipment);
            Toast.makeText(requireContext(), R.string.toast_add_success, Toast.LENGTH_SHORT).show();
        }

        if (savedListener != null) {
            savedListener.onEquipmentSaved();
        }
    }
}
