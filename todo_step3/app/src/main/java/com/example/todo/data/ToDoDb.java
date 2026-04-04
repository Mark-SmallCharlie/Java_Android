package com.example.todo.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ToDoItem.class},version = 2,exportSchema = false)
public abstract class ToDoDb extends RoomDatabase {
    public abstract ToDoDao toDoDao();

    private static ToDoDb ctx;

    public static void create(Context context) {
        if (ctx == null) {
            ctx = Room.databaseBuilder(context, ToDoDb.class, "todo_db_2").build();
        }
    }

    public static ToDoDb get(){
        return ctx;
    }
}
