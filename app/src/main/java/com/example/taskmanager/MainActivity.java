package com.example.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FloatingActionButton btnAddTask;
    TextView txtStt,txtTaskName;
    ListView listView;
    private ArrayList<TaskModel> listTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database database = new Database(this);
        //Delete Database and create default data
        database.onUpgrade(database.getWritableDatabase(),1,1);
        database.createDefaultData();

        btnAddTask = findViewById(R.id.btn_AddTask);
        listView = findViewById(R.id.listTasks);
        txtStt = findViewById(R.id.txt_stt);
        txtTaskName = findViewById(R.id.txt_TaskName);

        listTask = new ArrayList<>();
        //get data from database
        listTask = database.getAllTask();
        //set adapter
        TaskAdapter adapter = new TaskAdapter(this,R.layout.item_task);
        adapter.addAll(listTask);
        listView.setAdapter(adapter);

        btnAddTask.setOnClickListener(v -> {
            //go to add task activity
            Intent intent = new Intent(MainActivity.this,ActivityTaskDetail.class);
            startActivity(intent);
        });

    }
}