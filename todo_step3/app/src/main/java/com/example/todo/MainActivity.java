package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.data.ToDoDb;
import com.example.todo.data.ToDoItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ToDoDb.create(getApplicationContext());

        setContentView(R.layout.activity_main);

        RecyclerView todoListView=findViewById(R.id.todoListView);
        adapter=new TodoAdapter();
        todoListView.setAdapter(adapter);
        todoListView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void add(View view){
        Intent it=new Intent();
        it.setClass(this,AddActivity.class);
        startActivity(it);
    }

    @Override
    protected void onResume() {
        
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ToDoItem> all = ToDoDb.get().toDoDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(all);
                    }
                });
            }
        }).start();
    }
}