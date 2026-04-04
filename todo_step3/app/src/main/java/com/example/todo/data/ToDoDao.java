package com.example.todo.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ToDoDao {
    @Insert
    void add(ToDoItem item);
    @Delete
    void delete(ToDoItem item);

    @Query("select * from todoitem")
    List<ToDoItem> getAll();
}
