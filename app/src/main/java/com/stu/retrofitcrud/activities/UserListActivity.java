package com.stu.retrofitcrud.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.addStudentsAndGetStudentsById.AddStudentsResponseModel;
import com.stu.retrofitcrud.getStudents.model.GetStudentsListModel;
import com.stu.retrofitcrud.getStudents.model.GetStudentsResponseModel;
import com.stu.retrofitcrud.getStudents.adapter.StudentAdapter;
import com.stu.retrofitcrud.interfaces.UserListButtonsClickListener;
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
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_list);
        userRv = findViewById(R.id.studentRv);

        setUpUserList();

        getAllStudents();


    }

    private void getAllStudents() {
        Call<GetStudentsResponseModel> call = RetrofitClient.getInstance().getApiInterface().getAllStudents();

        call.enqueue(new Callback<GetStudentsResponseModel>() {
            @Override
            public void onResponse(Call<GetStudentsResponseModel> call, Response<GetStudentsResponseModel> response) {
                if (response.isSuccessful()) {
                    GetStudentsResponseModel apiResponse = response.body();
                    if (apiResponse != null) {
                        List<GetStudentsListModel> studentList = apiResponse.getData();
                        adapter.setStudentList(studentList);

                    } else {
                        Toast.makeText(UserListActivity.this, "No Student found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetStudentsResponseModel> call, Throwable t) {
                Toast.makeText(UserListActivity.this, "Failure Occur", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpUserList() {
        adapter = new StudentAdapter();
        adapter.setOnButtonClickListener(new UserListButtonsClickListener() {
            @Override
            public void onDetailButtonClicked(GetStudentsListModel student) {

                Intent intent = new Intent(UserListActivity.this, DetailActivity.class);
                intent.putExtra("studentId", student.getId());
                intent.putExtra("name", student.getName());
                intent.putExtra("email", student.getEmail());
                intent.putExtra("phone", student.getPhone());
                intent.putExtra("address", student.getAddress());
                startActivity(intent);
            }

            @Override
            public void onDeleteButtonClicked(GetStudentsListModel student) {
                deleteStudent(student.getId());
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserListActivity.this);
        userRv.setLayoutManager(linearLayoutManager);
        userRv.setHasFixedSize(true);
        userRv.setAdapter(adapter);
    }

    private void deleteStudent(int id) {
        Call<AddStudentsResponseModel> call = RetrofitClient.getInstance().getApiInterface().deleteStudent(id);
        call.enqueue(new Callback<AddStudentsResponseModel>() {
            @Override
            public void onResponse(Call<AddStudentsResponseModel> call, Response<AddStudentsResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AddStudentsResponseModel responseModel = response.body();
                    if (responseModel.getStatus()) {
                        getAllStudents();
                        Toast.makeText(UserListActivity.this, responseModel.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(UserListActivity.this, "Failed to delete student", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserListActivity.this, "Failed to delete student", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddStudentsResponseModel> call, Throwable t) {
                Toast.makeText(UserListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}