package com.example.myapplication5.data;

import androidx.core.location.LocationRequestCompat;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
@Dao
public interface ToDoDao {

    @Insert
    void add(ToDoItem item);
    @Delete
    void delete(ToDoItem item);

    @Query("select*from todoitem")
    List<ToDoItem>getAll();
}
