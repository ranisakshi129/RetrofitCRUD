package com.stu.retrofitcrud.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.ui.addstudentsandgetstudentsbyid.CreateStudentActivity;
import com.stu.retrofitcrud.ui.getStudents.StudentListActivity;

public class HomeActivity extends AppCompatActivity {
     Button addUsersBtn , viewAllUsersBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        addUsersBtn=findViewById(R.id.addUsersBtn);
        viewAllUsersBtn=findViewById(R.id.viewAllUsersBtn);
        addUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CreateStudentActivity.class);
                startActivity(intent);
            }
        });


        viewAllUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, StudentListActivity.class);
                startActivity(intent);
            }
        });
    }
}