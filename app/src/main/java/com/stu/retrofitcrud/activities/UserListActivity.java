package com.stu.retrofitcrud.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.getStudents.model.GetStudentsListModel;
import com.stu.retrofitcrud.getStudents.model.GetStudentsResponseModel;
import com.stu.retrofitcrud.getStudents.adapter.StudentAdapter;
import com.stu.retrofitcrud.model.StudentResponse;
import com.stu.retrofitcrud.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListActivity extends AppCompatActivity {
    RecyclerView userRv;

    List<StudentResponse> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_list);
        userRv=findViewById(R.id.studentRv);

        Call<GetStudentsResponseModel> call = RetrofitClient.getInstance().getApiInterface().getAllStudents();

        call.enqueue(new Callback<GetStudentsResponseModel>() {
            @Override
            public void onResponse(Call<GetStudentsResponseModel> call, Response<GetStudentsResponseModel> response) {
                if (response.isSuccessful()) {
                    GetStudentsResponseModel apiResponse = response.body();
                    if (apiResponse != null) {
                        List<GetStudentsListModel> studentList = apiResponse.getData();
                        StudentAdapter adapter = new StudentAdapter(studentList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserListActivity.this);
                        userRv.setLayoutManager(linearLayoutManager);
                        userRv.setHasFixedSize(true);
                        userRv.setAdapter(adapter);
                    } else {
                        Toast.makeText(UserListActivity.this, "No Student found", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<GetStudentsResponseModel> call, Throwable t) {
                Toast.makeText(UserListActivity.this, "Failure Occur", Toast.LENGTH_SHORT).show();
            }
        });






    }
}