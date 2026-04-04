package com.example.myapplication5.data;

import android.content.Context;

import androidx.core.net.ConnectivityManagerCompat;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {ToDoItem.class},version =1)
public abstract class ToDoDb extends RoomDatabase{
    public  abstract ToDoDao toDoDao();
    public  static ToDoDb ctx;
    public static void create(Context context){
             if (ctx==null){
                ctx=Room.databaseBuilder(context,ToDoDb.class,"todo_db").build();
             }
        }
        public static ToDoDb get(){return ctx;}
    }

