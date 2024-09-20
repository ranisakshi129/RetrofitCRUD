package com.stu.retrofitcrud.ui.login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.ui.home.HomeActivity;
import com.stu.retrofitcrud.ui.signup.SignUpActivity;
import com.stu.retrofitcrud.ui.login.model.LoginRequestModel;
import com.stu.retrofitcrud.ui.login.model.LoginResponseModel;
import com.stu.retrofitcrud.utils.CommonMethods;
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
                String email=emailEdt.getText().toString().trim();
                String password=passwordEdt.getText().toString().trim();

                LoginRequestModel loginRequest = new LoginRequestModel();
                loginRequest.setEmail(email);
                loginRequest.setPassword(password);

                Call<LoginResponseModel> call = RetrofitClient.getInstance().getApiInterface().loginUser(loginRequest);
                CommonMethods.showProgressBar(LoginActivity.this);
                call.enqueue(new Callback<LoginResponseModel>() {
                    @Override
                    public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                        CommonMethods.hideProgressBar();
                        if (response.isSuccessful()) {
                            LoginResponseModel loginResponse = response.body();
                            if (loginResponse.getStatus()) {
                                SharedPrefManager.getInstance(LoginActivity.this).saveUser(email, password);
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                            CommonMethods.showToast(LoginActivity.this,loginResponse.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                        CommonMethods.hideProgressBar();
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

