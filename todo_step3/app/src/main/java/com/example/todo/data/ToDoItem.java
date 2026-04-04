package com.example.todo.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ToDoItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String deadline;
    public String description;
    public String location;
    public String person;
    public String level;
}
