package com.stu.retrofitcrud.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.login.model.LoginRequestModel;
import com.stu.retrofitcrud.login.model.LoginResponseModel;
import com.stu.retrofitcrud.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEdt, passwordEdt;
    private Button loginBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        emailEdt = findViewById(R.id.emailEdt);
        passwordEdt = findViewById(R.id.passwordEdt);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginRequestModel loginRequest = new LoginRequestModel();
                loginRequest.setEmail(emailEdt.getText().toString());
                loginRequest.setPassword(passwordEdt.getText().toString());
                Call<LoginResponseModel> call = RetrofitClient.getInstance().getApiInterface().loginUser(loginRequest);
                call.enqueue(new Callback<LoginResponseModel>() {
                    @Override
                    public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                        if (response.isSuccessful()) {
                            LoginResponseModel loginResponse = response.body();
                            if (loginResponse.getStatus()) {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                    }
                });
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }

        });
    }
}

