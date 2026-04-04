package com.example.myapplication5.data;


import androidx.room.PrimaryKey;


public class ToDoItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String deadline;
    public String description;
    public String location;
    public String person;
    public String level;

}
