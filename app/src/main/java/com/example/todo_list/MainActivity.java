package com.example.todo_list;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lvTasks;
    private static TaskAdapter tasksAdapter;
    private EditText etTaskTopic;
    private EditText etTaskContent;
    private EditText etTaskDate;
    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (tasksAdapter == null) {
            tasksAdapter = new TaskAdapter(this,
                    R.layout.list_item, Task.getTasks());
            tasksAdapter.setNotifyOnChange(true);
        }

        lvTasks = findViewById(R.id.list_view);

        lvTasks.setAdapter(tasksAdapter);
        Button buttonAdd = findViewById(R.id.btn_add);
        buttonAdd.setOnClickListener(this);
        Button buttonDel = findViewById(R.id.btn_del);
        buttonDel.setOnClickListener(this);

        etTaskTopic = findViewById(R.id.etTaskTopic);
        etTaskContent = findViewById(R.id.etTaskContent);
        etTaskDate = findViewById(R.id.etTaskDate);

        etTaskDate.setInputType(InputType.TYPE_NULL);
        etTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etTaskDate.setText(
                                        dayOfMonth + "." +
                                                (monthOfYear + 1) + "." +
                                                    year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add) {
            addNewItem();
        }
        if (view.getId() == R.id.btn_del) {
            deleteSelectedItem();
        }
    }

    private void addNewItem() {
        String topic = etTaskTopic.getText().toString();
        String content = etTaskContent.getText().toString();
        String date = etTaskDate.getText().toString();

        Task task = new Task(topic, content, date);
        tasksAdapter.add(task);

        etTaskTopic.setText("");
        etTaskContent.setText("");
        etTaskDate.setText("");
    }

    private void deleteSelectedItem() {
        int count = lvTasks.getAdapter().getCount();

        for (int i = count - 1; i >= 0; i--) {
            View elementView = lvTasks.getChildAt(i);
            CheckBox checkBox = elementView.findViewById(R.id.checkBox);
            if (checkBox.isChecked()) {
                Task task = tasksAdapter.getItem(i);
                tasksAdapter.remove(task);
            }
        }
    }
}