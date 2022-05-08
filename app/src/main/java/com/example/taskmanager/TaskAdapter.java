package com.example.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TaskAdapter extends ArrayAdapter<TaskModel> {
    Activity context;
    int resource;

    public TaskAdapter(Activity context, int resource) {
        super(context,resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(resource,null);

        TaskModel task = getItem(position);
        TextView taskName = v.findViewById(R.id.txt_TaskName);
        TextView stt = v.findViewById(R.id.txt_stt);
        Button btn_delete = v.findViewById(R.id.btn_Delete);
        Button btn_edit = v.findViewById(R.id.btn_update);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete task on database
                System.out.println("delete");
                Database database = new Database(context);
                database.deleteTask(task.getTaskId());
                //delete task on listview
                remove(task);
                notifyDataSetChanged();

            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edit task on database
                System.out.println("edit");
                //foward to update activity
                Intent intent = new Intent(context, ActivityTaskDetail.class);
                intent.putExtra("taskId",String.valueOf(task.getTaskId()));
                intent.putExtra("taskName",task.getTaskName());

                context.startActivity(intent);
            }
        });

        taskName.setText(task.getTaskName());
        stt.setText("Lab" + task.getTaskId());

        return v;
    }
}
