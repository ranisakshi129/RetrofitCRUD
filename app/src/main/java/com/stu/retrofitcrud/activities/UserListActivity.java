package com.stu.retrofitcrud.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.adapter.UserAdapter;
import com.stu.retrofitcrud.model.UserResponseModel;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    RecyclerView userRv;

    List<UserResponseModel> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_list);
        userRv=findViewById(R.id.userRv);


        for(int i=1;i<=20;i++) {
            UserResponseModel user = new UserResponseModel();
            user.setName("Sakshi");
            user.setPhone("8851436628");
            user.setEmail("ranisakshi129@gmail.com");
            user.setAddress("Sain Vihar");
            user.setImage(R.drawable.contact);
            userList.add(user);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(UserListActivity.this);
        UserAdapter adapter=new UserAdapter(userList);
        userRv.setLayoutManager(layoutManager);
        userRv.setHasFixedSize(true);
        userRv.setAdapter(adapter);




    }
}