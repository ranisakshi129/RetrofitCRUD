package com.stu.retrofitcrud.ui.getStudents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.ui.addstudentsandgetstudentsbyid.model.AddStudentsResponseModel;
import com.stu.retrofitcrud.ui.getStudents.model.GetStudentsListModel;
import com.stu.retrofitcrud.ui.getStudents.model.GetStudentsResponseModel;
import com.stu.retrofitcrud.ui.getStudents.adapter.StudentAdapter;
import com.stu.retrofitcrud.interfaces.UserListButtonsClickListener;
import com.stu.retrofitcrud.ui.signup.model.StudentResponse;
import com.stu.retrofitcrud.ui.updatesstudents.DetailActivity;
import com.stu.retrofitcrud.utils.CommonMethods;
import com.stu.retrofitcrud.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentListActivity extends AppCompatActivity {
    RecyclerView userRv;
    SearchView searchView;
    List<StudentResponse> userList = new ArrayList<>();
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_list);
        userRv = findViewById(R.id.studentRv);
        searchView=findViewById(R.id.searchView);
        setUpUserList();
        getAllStudents();
    }

    private void getAllStudents() {
        Call<GetStudentsResponseModel> call = RetrofitClient.getInstance().getApiInterface().getAllStudents();
        CommonMethods.showProgressBar(StudentListActivity.this);
        call.enqueue(new Callback<GetStudentsResponseModel>() {
            @Override
            public void onResponse(Call<GetStudentsResponseModel> call, Response<GetStudentsResponseModel> response) {
                CommonMethods.hideProgressBar();
                if (response.isSuccessful()) {
                    GetStudentsResponseModel apiResponse = response.body();
                    if (apiResponse != null) {
                        List<GetStudentsListModel> studentList = apiResponse.getData();
                        adapter.setStudentList(studentList);

                    } else {
                        CommonMethods.showToast(StudentListActivity.this,"No Student found");
                    }
                }
            }

            @Override
            public void onFailure(Call<GetStudentsResponseModel> call, Throwable t) {
                CommonMethods.hideProgressBar();
                CommonMethods.showToast(StudentListActivity.this,"Failure Occur");
            }
        });
    }

    private void setUpUserList() {
        adapter = new StudentAdapter();
        adapter.setOnButtonClickListener(new UserListButtonsClickListener() {
            @Override
            public void onDetailButtonClicked(GetStudentsListModel student) {

                Intent intent = new Intent(StudentListActivity.this, DetailActivity.class);
                intent.putExtra("studentId", student.getId());
                intent.putExtra("name", student.getName());
                intent.putExtra("email", student.getEmail());
                intent.putExtra("phone", student.getPhone());
                intent.putExtra("address", student.getAddress());
                startActivity(intent);
                finish();
            }

            @Override
            public void onDeleteButtonClicked(GetStudentsListModel student) {
                deleteStudent(student.getId());
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StudentListActivity.this);
        userRv.setLayoutManager(linearLayoutManager);
        userRv.setHasFixedSize(true);
        userRv.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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
                        CommonMethods.showToast(StudentListActivity.this,responseModel.getMessage());

                    } else {
                        CommonMethods.showToast(StudentListActivity.this,"Failed to delete Student");
                    }
                } else {
                    CommonMethods.showToast(StudentListActivity.this,"Failed to delete Student");
                }
            }

            @Override
            public void onFailure(Call<AddStudentsResponseModel> call, Throwable t) {
                CommonMethods.showToast(StudentListActivity.this,"Error : "+t.getMessage());
            }
        });
    }

}