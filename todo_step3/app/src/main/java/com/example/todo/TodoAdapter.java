package com.example.todo;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.data.ToDoItem;

import java.util.Calendar;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<ToDoItem> list;
    public void setData(List<ToDoItem> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoItem item=list.get(position);
        holder.setData(item);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView labDesc,labDeadline,labLevel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            labDesc=itemView.findViewById(R.id.labDesc);
            labDeadline=itemView.findViewById(R.id.labDeadline);
            labLevel=itemView.findViewById(R.id.labLevel);
        }

        public void setData(ToDoItem item){
            labDesc.setText(item.description);
            labDeadline.setText(item.deadline);
            labLevel.setText(item.level);
        }
    }
}
