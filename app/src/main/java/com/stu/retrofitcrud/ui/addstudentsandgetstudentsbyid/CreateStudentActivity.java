package com.stu.retrofitcrud.ui.addstudentsandgetstudentsbyid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.ui.home.HomeActivity;
import com.stu.retrofitcrud.ui.addstudentsandgetstudentsbyid.model.AddStudentsRequestModel;
import com.stu.retrofitcrud.ui.addstudentsandgetstudentsbyid.model.AddStudentsResponseModel;
import com.stu.retrofitcrud.utils.CommonMethods;
import com.stu.retrofitcrud.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateStudentActivity extends AppCompatActivity {
    private EditText nameEdt, emailEdt, phoneEdt, addressEdt;
    private Button submitBtn;
    private ImageView personIv;
    private Integer studentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_users);
        nameEdt = findViewById(R.id.nameEdt);
        emailEdt = findViewById(R.id.emailEdt);
        phoneEdt = findViewById(R.id.phoneEdt);
        addressEdt = findViewById(R.id.addressEdt);
        submitBtn = findViewById(R.id.submitBtn);

        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            studentId = intent.getIntExtra("id", -1);
            submitBtn.setText("Update");
            nameEdt.setText(intent.getStringExtra("name"));
            emailEdt.setText(intent.getStringExtra("email"));
            phoneEdt.setText(intent.getStringExtra("phone"));
            addressEdt.setText(intent.getStringExtra("address"));
        }
        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String email=emailEdt.getText().toString().trim();
                String name=nameEdt.getText().toString().trim();
                String phone=phoneEdt.getText().toString().trim();
                String address=addressEdt.getText().toString().trim();
                if(CommonMethods.validateStudentsCredentials(CreateStudentActivity.this,email,name,phone,address)) {
                    AddStudentsRequestModel addStudentsRequestModel = new AddStudentsRequestModel();
                    addStudentsRequestModel.setName(nameEdt.getText().toString());
                    addStudentsRequestModel.setEmail(emailEdt.getText().toString());
                    addStudentsRequestModel.setAddress(addressEdt.getText().toString());
                    addStudentsRequestModel.setPhone(phoneEdt.getText().toString());

                    if (studentId == -1) {
                        addStudent(addStudentsRequestModel);
                    } else {
                        addStudentsRequestModel.setId(studentId);
                        updateStudent(addStudentsRequestModel);
                    }
                }
            }
        });
    }

    private void addStudent(AddStudentsRequestModel addStudentsRequestModel) {

        Call<AddStudentsResponseModel> call = RetrofitClient.getInstance().getApiInterface().addStudent(addStudentsRequestModel);
        CommonMethods.showProgressBar(CreateStudentActivity.this);
        call.enqueue(new Callback<AddStudentsResponseModel>() {
            @Override
            public void onResponse(Call<AddStudentsResponseModel> call, Response<AddStudentsResponseModel> response) {
                CommonMethods.hideProgressBar();
                handleResponse(response);
            }

            @Override
            public void onFailure(Call<AddStudentsResponseModel> call, Throwable t) {
                CommonMethods.hideProgressBar();
                CommonMethods.showToast(CreateStudentActivity.this,"Data not found");
            }
        });
    }

    private void updateStudent(AddStudentsRequestModel addStudentsRequestModel) {
        Call<AddStudentsResponseModel> call = RetrofitClient.getInstance().getApiInterface().updateStudent(addStudentsRequestModel);
        CommonMethods.showProgressBar(CreateStudentActivity.this);
        call.enqueue(new Callback<AddStudentsResponseModel>() {
            @Override
            public void onResponse(Call<AddStudentsResponseModel> call, Response<AddStudentsResponseModel> response) {
                CommonMethods.hideProgressBar();
                handleResponse(response);
            }

            @Override
            public void onFailure(Call<AddStudentsResponseModel> call, Throwable t) {
                CommonMethods.hideProgressBar();
                CommonMethods.showToast(CreateStudentActivity.this,"Data not found");
            }
        });
    }

    private void handleResponse(Response<AddStudentsResponseModel> response) {
        if (response.isSuccessful()) {
            AddStudentsResponseModel addStudentResponse = response.body();
            if (addStudentResponse.getStatus()) {
                Intent intent = new Intent(CreateStudentActivity.this, HomeActivity.class);
                startActivity(intent);
                CommonMethods.showToast(CreateStudentActivity.this,addStudentResponse.getMessage());
            }
            else {
                CommonMethods.showToast(CreateStudentActivity.this,addStudentResponse.getMessage());
            }
        }
        finish();

    }

}












//    private void getStudentById(int id) {
//        Call<AddStudentsResponseModel> call = RetrofitClient.getInstance().getApiInterface().getStudentById(id);
//        call.enqueue(new Callback<AddStudentsResponseModel>() {
//            @Override
//            public void onResponse(Call<AddStudentsResponseModel> call, Response<AddStudentsResponseModel> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    AddStudentsResponseModel student = response.body();
//                    if (student.getStatus()) {
//                        nameEdt.setText(student.getData().getName());
//                        emailEdt.setText(student.getData().getEmail());
//                        phoneEdt.setText(student.getData().getPhone());
//                        addressEdt.setText(student.getData().getAddress());
//                    } else {
//                        Toast.makeText(CreateStudentActivity.this, "Failed to get student details", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(CreateStudentActivity.this, "Failed to get student details", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AddStudentsResponseModel> call, Throwable t) {
//                Toast.makeText(CreateStudentActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
