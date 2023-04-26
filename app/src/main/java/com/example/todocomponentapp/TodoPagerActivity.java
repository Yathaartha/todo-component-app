package com.example.todocomponentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class TodoPagerActivity extends AppCompatActivity {

    public static final String EXTRA_TODO_ID = "todo_id";

    private ViewPager2 viewPager;
    private TodoModel todoModel;
    private ArrayList<Todo> todosList;
    public static Intent makeIntent(Context context, UUID todoId) {
        Intent intent = new Intent(context, TodoPagerActivity.class);
        intent.putExtra(EXTRA_TODO_ID, todoId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_pager);

        Intent intent = getIntent();
        UUID todoId = (UUID) intent.getSerializableExtra(EXTRA_TODO_ID);

        todoModel = TodoModel.getInstance();
        todosList = todoModel.getTodosList();

        viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @Override
            public int getItemCount() {
                return todosList.size();
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Todo todo = todosList.get(position);
                return TodoFragment.newInstance(todo.getId());
            }


        });
    }
}