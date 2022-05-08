package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityTaskDetail extends AppCompatActivity {
    private EditText txtLabName,txtId;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiti_detailtask);
        txtLabName = findViewById(R.id.txtLabName);
        txtId = findViewById(R.id.txtId);
        btnSave = findViewById(R.id.btn_Save);
        // get data from intent
        Intent intent = getIntent();
        String id = intent.getStringExtra("taskId");
        String labName = intent.getStringExtra("taskName");
        if(id != null && labName != null){
            txtId.setText("Lab"+id);
            txtLabName.setText(labName);
        }
        // set data to edit text
        Database database = new Database(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add new task on database
                String labName = txtLabName.getText().toString();
                if(id == null){
                    database.addTask(labName);
                }
                else{
                    database.updateTask(Integer.valueOf(id),labName);
                }
                // foward to main activity
                finish();
                Intent intent = new Intent(ActivityTaskDetail.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
