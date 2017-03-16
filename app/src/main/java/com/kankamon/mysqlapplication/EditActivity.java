package com.kankamon.mysqlapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final TodoList editTodoList = (TodoList) getIntent().getSerializableExtra("editTodoList");

        final EditText editText = (EditText)findViewById(R.id.edit_Text);
        editText.setText(editTodoList.getTaskname());

        Button editBtn = (Button) findViewById(R.id.edit_button);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList eTodoList = new TodoList();
                eTodoList.setTaskid(editTodoList.getTaskid());
                eTodoList.setTaskname(String.valueOf(editText.getText()));

                TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
                todoListDAO.open();
                todoListDAO.update(eTodoList);
                todoListDAO.close();

                finish();
            }
        });

        Button delBtn = (Button) findViewById(R.id.del_button);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoListDAO delTodoListDAO = new TodoListDAO(getApplicationContext());

                delTodoListDAO.open();
                delTodoListDAO.delete(editTodoList);
                delTodoListDAO.close();

                finish();
            }
        });
    }
}
