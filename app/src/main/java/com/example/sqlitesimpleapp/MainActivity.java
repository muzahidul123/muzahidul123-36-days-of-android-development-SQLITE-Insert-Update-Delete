package com.example.sqlitesimpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nameET, ageET, idET;
    private Button insertBtn, showdataBtn, updateBtn;
    private List<User> userList;
    private UserAdapter adapter;
    private DatabaseHelper helper;
    private String name, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        insertData();

        updateData();

        showdataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowActivity.class));
            }
        });
    }

    private void updateData() {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = helper.updateData(idET.getText().toString(), nameET.getText().toString(), ageET.getText().toString());

                if (isUpdate == true)
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertData() {

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameET.getText().toString().trim();
                age = ageET.getText().toString().trim();

                long id = helper.insertData(name, age);
                Toast.makeText(MainActivity.this, "Your ID"+id, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void init() {

        idET = findViewById(R.id.idET);
        nameET = findViewById(R.id.nameET);
        ageET = findViewById(R.id.ageET);
        insertBtn = findViewById(R.id.insertBtn);
        showdataBtn = findViewById(R.id.showdataBtn);
        updateBtn = findViewById(R.id.updateBtn);

        helper = new DatabaseHelper(this);
        adapter = new UserAdapter(userList,this);

    }
}
