package com.stu.retrofitcrud.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.stu.retrofitcrud.R;

public class DetailActivity extends AppCompatActivity {
    private TextView nameTv,emailTv,phoneTv,addressTv;
    private Button updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        nameTv=findViewById(R.id.nameTv);
        emailTv=findViewById(R.id.emailTv);
        phoneTv=findViewById(R.id.phoneTv);
        addressTv=findViewById(R.id.addressTv);
        updateBtn=findViewById(R.id.updateBtn);

        Intent intent=getIntent();
        if(intent.hasExtra("studentId")) {
            nameTv.setText("Name : "+intent.getStringExtra("name"));
            emailTv.setText("Email : "+intent.getStringExtra("email"));
            phoneTv.setText("Phone : "+intent.getStringExtra("phone"));
            addressTv.setText("Address :"+intent.getStringExtra("address"));
        }

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editUserIntent=new Intent(DetailActivity.this,CreateStudentActivity.class);
                editUserIntent.putExtra("id",intent.getLongExtra(""+"id",0));
                editUserIntent.putExtra("name",intent.getStringExtra("name"));
                editUserIntent.putExtra("phone",intent.getStringExtra("phone"));
                editUserIntent.putExtra("email",intent.getStringExtra("email"));
                editUserIntent.putExtra("address",intent.getStringExtra("address"));
                startActivity(editUserIntent);
                finish();
            }
        });
//                startActivity(intent);
//                if(studentId  >0){
//                UpdateStudentRequestModel updateStudentRequestModel=new UpdateStudentRequestModel();
//                updateStudentRequestModel.setId(studentId);
//                updateStudentRequestModel.setName(nameTv.getText().toString());
//                updateStudentRequestModel.setEmail(emailTv.getText().toString());
//                updateStudentRequestModel.setPhone(phoneTv.getText().toString());
//                updateStudentRequestModel.setAddress(addressTv.getText().toString());
//                Call<UpdateStudentResponseModel> call =  RetrofitClient.getInstance().getApiInterface().updateStudent(updateStudentRequestModel);
//                    call.enqueue(new Callback<UpdateStudentResponseModel>() {
//                        @Override
//                        public void onResponse(Call<UpdateStudentResponseModel> call, Response<UpdateStudentResponseModel> response) {
//                            if (response.isSuccessful()) {
//                                UpdateStudentResponseModel updateStudentResponse = response.body();
//                                Log.d("DetailActivity", "Update successful: " + updateStudentResponse.getMessage());
//                                if (updateStudentResponse.getStatus()) {
//                                    Toast.makeText(DetailActivity.this, updateStudentResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(DetailActivity.this, CreateStudentActivity.class);
//                                    startActivity(intent);
//                                } else {
//                                    Toast.makeText(DetailActivity.this, "Update failed: " + updateStudentResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(DetailActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
//                                Log.e("DetailActivity", "Update failed: " + response.message());
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<UpdateStudentResponseModel> call, Throwable t) {
//                            Toast.makeText(DetailActivity.this, "Update failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                            Log.e("DetailActivity", "Update failed", t);
//                        }
//                    });
//                } else {
//                    Toast.makeText(DetailActivity.this, "Invalid student ID", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
}
}