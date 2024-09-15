package com.stu.retrofitcrud.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.stu.retrofitcrud.R;

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
                Intent intent = new Intent(HomeActivity.this,CreateUsersActivity.class);
                startActivity(intent);
                finish();
            }
        });


        viewAllUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,UserListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}