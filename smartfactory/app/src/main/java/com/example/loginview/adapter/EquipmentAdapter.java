package com.example.loginview.adapter;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginview.R;
import com.example.loginview.model.Equipment;

import java.util.ArrayList;
import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {

    private List<Equipment> equipmentList = new ArrayList<>();
    private int selectedPosition = -1;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Equipment equipment, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Equipment> list) {
        this.equipmentList = list;
        selectedPosition = -1;
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int position) {
        int oldPosition = selectedPosition;
        selectedPosition = position;
        if (oldPosition >= 0) notifyItemChanged(oldPosition);
        if (position >= 0) notifyItemChanged(position);
    }

    public Equipment getSelectedEquipment() {
        if (selectedPosition >= 0 && selectedPosition < equipmentList.size()) {
            return equipmentList.get(selectedPosition);
        }
        return null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_equipment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Equipment equipment = equipmentList.get(position);
        holder.tvName.setText(equipment.getName());
        holder.tvCode.setText(equipment.getCode());

        // Set damage badge
        String damage = equipment.getDamageLevel();
        holder.tvDamageBadge.setText(damage);
        int bgColor;
        int textColor;
        if (damage.equals("良好")) {
            bgColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.damage_good);
            textColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.damage_good_text);
        } else if (damage.equals("轻微")) {
            bgColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.damage_minor);
            textColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.damage_minor_text);
        } else if (damage.equals("中等")) {
            bgColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.damage_moderate);
            textColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.damage_moderate_text);
        } else {
            bgColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.damage_severe);
            textColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.damage_severe_text);
        }
        holder.tvDamageBadge.setBackgroundTintList(ColorStateList.valueOf(bgColor));
        holder.tvDamageBadge.setTextColor(textColor);

        // Set equipment image
        if (equipment.getImageResId() != 0) {
            holder.ivEquipment.setImageResource(equipment.getImageResId());
        }

        // Highlight selected item
        if (position == selectedPosition) {
            holder.itemView.setAlpha(1.0f);
            holder.itemView.setBackgroundResource(R.color.input_background);
        } else {
            holder.itemView.setAlpha(0.9f);
            holder.itemView.setBackgroundResource(android.R.color.transparent);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(equipment, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivEquipment;
        TextView tvName;
        TextView tvCode;
        TextView tvDamageBadge;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivEquipment = itemView.findViewById(R.id.iv_equipment);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvDamageBadge = itemView.findViewById(R.id.tv_damage_badge);
        }
    }
}
