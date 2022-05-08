package com.example.taskmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "TaskManager";

    public Database(android.content.Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create table task
        sqLiteDatabase.execSQL("CREATE TABLE TASK(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TASK");
//        Create tables again
        onCreate(sqLiteDatabase);
    }
    public void createDefaultData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO TASK (NAME) VALUES ('Làm quen với Android')");
        db.execSQL("INSERT INTO TASK (NAME) VALUES ('Layout và View ')");
        db.execSQL("INSERT INTO TASK (NAME) VALUES ('Intent và Activity')");
        db.execSQL("INSERT INTO TASK (NAME) VALUES ('Làm quen với Java')");

    }
    //    get All task from database
//    get all data user
    public ArrayList<TaskModel> getAllTask() {
        ArrayList<TaskModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TASK", null);
        if (cursor.moveToFirst()) {
            do {
                TaskModel task = new TaskModel();
                task.setTaskId(cursor.getInt(0));
                task.setTaskName(cursor.getString(1));
                list.add(task);
            } while (cursor.moveToNext());
        }
        return list;
    }
    //delete task
    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TASK", "ID = ?", new String[]{String.valueOf(id)});

    }
    //add task
    public void addTask(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO TASK (NAME) VALUES ('" + name + "')");
    }
    //update task
    public void updateTask(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE TASK SET NAME = '" + name + "' WHERE ID = " + id);
    }
}