package com.example.sqlitesimpleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<User> userList;
    private DatabaseHelper helper;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        init();

        getData();
    }

    private void getData() {

        Cursor cursor = helper.showData();

        while (cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(helper.COL_NAME));
            String age = cursor.getString(cursor.getColumnIndex(helper.COL_AGE));

            userList.add(new User(id, name, age));

            adapter.notifyDataSetChanged();
        }
    }

    private void init() {

        recyclerView = findViewById(R.id.recyclearView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        helper = new DatabaseHelper(this);
        adapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(adapter);
    }
}
