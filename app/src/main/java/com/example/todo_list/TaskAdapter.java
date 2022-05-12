package com.example.todo_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private LayoutInflater inflater;
    private int viewResource;
    private List<Task> taskList;
    private Context context;

    public TaskAdapter(@NonNull Context context, int resource, @NonNull List<Task> objects) {
        super(context, resource, objects);
        this.context = context;
        inflater = ((AppCompatActivity) context).getLayoutInflater();
        viewResource = resource;
        taskList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = this.inflater.inflate(this.viewResource, parent, false);

        final CheckBox checkView = view.findViewById(R.id.checkBox);
        checkView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkView.isChecked()) {
                    taskList.get(position).setCheckOnList(true);
                } else {
                    taskList.get(position).setCheckOnList(false);
                }
            }
        });

        TextView topicView = view.findViewById(R.id.tvTopic);
        TextView contentView = view.findViewById(R.id.tvContent);
        TextView dateView = view.findViewById(R.id.tvDate);

        Task task = taskList.get(position);

        topicView.setText(task.getTopic());
        contentView.setText(task.getContent());
        dateView.setText(task.getDate());

        return view;
    }
}
