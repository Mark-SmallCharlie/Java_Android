package com.example.loginview.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginview.R;
import com.example.loginview.adapter.EquipmentAdapter;
import com.example.loginview.model.Equipment;
import com.example.loginview.util.EquipmentDatabaseHelper;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Locale;

public class EquipmentFragment extends Fragment {

    private static final String ARG_CATEGORY = "category";

    private String category;
    private EquipmentDatabaseHelper dbHelper;
    private EquipmentAdapter adapter;
    private LinearLayout detailContainer;

    // Detail views
    private TextView tvDetailName;
    private TextView tvDetailCode;
    private TextView tvDetailDamage;
    private TextView tvDetailUsage;
    private TextView tvDetailPrice;
    private ImageView ivDetailImage;
    private MaterialButton btnEdit;
    private MaterialButton btnDelete;

    public interface OnEquipmentActionListener {
        void onEditEquipment(Equipment equipment);
        void onDeleteEquipment(Equipment equipment);
    }

    private OnEquipmentActionListener actionListener;

    public static EquipmentFragment newInstance(String category) {
        EquipmentFragment fragment = new EquipmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    public void setOnEquipmentActionListener(OnEquipmentActionListener listener) {
        this.actionListener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);
        }
        dbHelper = new EquipmentDatabaseHelper(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_equipment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvCategoryTitle = view.findViewById(R.id.tv_category_title);
        RecyclerView rvEquipment = view.findViewById(R.id.rv_equipment);
        detailContainer = view.findViewById(R.id.detail_container);

        tvCategoryTitle.setText(category);

        // Setup detail views
        tvDetailName = view.findViewById(R.id.tv_detail_name);
        tvDetailCode = view.findViewById(R.id.tv_detail_code);
        tvDetailDamage = view.findViewById(R.id.tv_detail_damage);
        tvDetailUsage = view.findViewById(R.id.tv_detail_usage);
        tvDetailPrice = view.findViewById(R.id.tv_detail_price);
        ivDetailImage = view.findViewById(R.id.iv_detail_image);
        btnEdit = view.findViewById(R.id.btn_edit);
        btnDelete = view.findViewById(R.id.btn_delete);

        // Setup RecyclerView
        adapter = new EquipmentAdapter();
        rvEquipment.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvEquipment.setAdapter(adapter);

        adapter.setOnItemClickListener((equipment, position) -> {
            adapter.setSelectedPosition(position);
            showDetail(equipment);
        });

        btnEdit.setOnClickListener(v -> {
            Equipment selected = adapter.getSelectedEquipment();
            if (selected != null && actionListener != null) {
                actionListener.onEditEquipment(selected);
            }
        });

        btnDelete.setOnClickListener(v -> {
            Equipment selected = adapter.getSelectedEquipment();
            if (selected != null && actionListener != null) {
                actionListener.onDeleteEquipment(selected);
            }
        });

        loadData();
    }

    public void loadData() {
        if (dbHelper != null && adapter != null) {
            List<Equipment> list = dbHelper.getEquipmentByCategory(category);
            adapter.setData(list);
            detailContainer.setVisibility(View.GONE);

            // Update equipment count
            TextView tvCount = getView() != null ? getView().findViewById(R.id.tv_equipment_count) : null;
            if (tvCount != null) {
                tvCount.setText(String.format(Locale.getDefault(), "%d 台", list.size()));
            }
        }
    }

    private void showDetail(Equipment equipment) {
        detailContainer.setVisibility(View.VISIBLE);

        tvDetailName.setText(equipment.getName());
        tvDetailCode.setText(equipment.getCode());

        // Set damage level with color
        String damage = equipment.getDamageLevel();
        tvDetailDamage.setText(damage);
        int bgColor;
        int textColor;
        if (damage.equals("良好")) {
            bgColor = ContextCompat.getColor(requireContext(), R.color.damage_good);
            textColor = ContextCompat.getColor(requireContext(), R.color.damage_good_text);
        } else if (damage.equals("轻微")) {
            bgColor = ContextCompat.getColor(requireContext(), R.color.damage_minor);
            textColor = ContextCompat.getColor(requireContext(), R.color.damage_minor_text);
        } else if (damage.equals("中等")) {
            bgColor = ContextCompat.getColor(requireContext(), R.color.damage_moderate);
            textColor = ContextCompat.getColor(requireContext(), R.color.damage_moderate_text);
        } else {
            bgColor = ContextCompat.getColor(requireContext(), R.color.damage_severe);
            textColor = ContextCompat.getColor(requireContext(), R.color.damage_severe_text);
        }
        tvDetailDamage.setBackgroundTintList(ColorStateList.valueOf(bgColor));
        tvDetailDamage.setTextColor(textColor);

        tvDetailUsage.setText(String.format(Locale.getDefault(),
                getString(R.string.info_hours_format), equipment.getUsageHours()));
        tvDetailPrice.setText(String.format(Locale.getDefault(),
                getString(R.string.info_price_format), equipment.getMarketPrice()));

        if (equipment.getImageResId() != 0) {
            ivDetailImage.setImageResource(equipment.getImageResId());
        }
    }
}
