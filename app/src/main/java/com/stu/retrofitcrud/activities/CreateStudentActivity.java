package com.stu.retrofitcrud.activities;

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
import com.stu.retrofitcrud.addStudentsAndGetStudentsById.AddStudentsRequestModel;
import com.stu.retrofitcrud.addStudentsAndGetStudentsById.AddStudentsResponseModel;
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
        // personIv=findViewById(R.id.personIv);

//        personIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                chooseProfilePicture();
//            }
//        });

        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            studentId = intent.getIntExtra("id", -1);
            submitBtn.setText("Update");
            nameEdt.setText(intent.getStringExtra("name"));
            emailEdt.setText(intent.getStringExtra("email"));
            phoneEdt.setText(intent.getStringExtra("phone"));
            addressEdt.setText(intent.getStringExtra("address"));
            // getStudentById(studentId);
        }
        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStudentsRequestModel addStudentsRequestModel = new AddStudentsRequestModel();
                addStudentsRequestModel.setName(nameEdt.getText().toString());
                addStudentsRequestModel.setEmail(emailEdt.getText().toString());
                addStudentsRequestModel.setAddress(addressEdt.getText().toString());
                addStudentsRequestModel.setPhone(phoneEdt.getText().toString());

                if (studentId == -1) {
                    addStudent(addStudentsRequestModel);
                } else {
                    updateStudent(studentId, addStudentsRequestModel);
                }
            }
        });
    }

    private void addStudent(AddStudentsRequestModel addStudentsRequestModel) {
        Call<AddStudentsResponseModel> call = RetrofitClient.getInstance().getApiInterface().addStudent(addStudentsRequestModel);
        call.enqueue(new Callback<AddStudentsResponseModel>() {
            @Override
            public void onResponse(Call<AddStudentsResponseModel> call, Response<AddStudentsResponseModel> response) {
                handleResponse(response);
            }

            @Override
            public void onFailure(Call<AddStudentsResponseModel> call, Throwable t) {
                Toast.makeText(CreateStudentActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateStudent(int id, AddStudentsRequestModel addStudentsRequestModel) {
        Call<AddStudentsResponseModel> call = RetrofitClient.getInstance().getApiInterface().updateStudent(id, addStudentsRequestModel);
        call.enqueue(new Callback<AddStudentsResponseModel>() {
            @Override
            public void onResponse(Call<AddStudentsResponseModel> call, Response<AddStudentsResponseModel> response) {
                handleResponse(response);
            }

            @Override
            public void onFailure(Call<AddStudentsResponseModel> call, Throwable t) {
                Toast.makeText(CreateStudentActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleResponse(Response<AddStudentsResponseModel> response) {
        if (response.isSuccessful()) {
            Toast.makeText(CreateStudentActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            AddStudentsResponseModel addStudentResponse = response.body();
            if (addStudentResponse.getStatus()) {
                Intent intent = new Intent(CreateStudentActivity.this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(CreateStudentActivity.this, addStudentResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
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
