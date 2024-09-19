package com.stu.retrofitcrud.ui.login;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.activities.HomeActivity;
import com.stu.retrofitcrud.activities.SignUpActivity;
import com.stu.retrofitcrud.ui.login.model.LoginRequestModel;
import com.stu.retrofitcrud.ui.login.model.LoginResponseModel;
import com.stu.retrofitcrud.utils.Constants;
import com.stu.retrofitcrud.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEdt, passwordEdt;
    private Button loginBtn, signUpBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        emailEdt = findViewById(R.id.emailEdt);
        passwordEdt = findViewById(R.id.passwordEdt);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        progressBar = findViewById(R.id.progressBar);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailEdt.getText().toString().trim();
                String password=passwordEdt.getText().toString().trim();

                LoginRequestModel loginRequest = new LoginRequestModel();
                loginRequest.setEmail(email);
                loginRequest.setPassword(password);

                Call<LoginResponseModel> call = RetrofitClient.getInstance().getApiInterface().loginUser(loginRequest);

                progressBar.setVisibility(View.VISIBLE);
                call.enqueue(new Callback<LoginResponseModel>() {
                    @Override
                    public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                        progressBar.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            LoginResponseModel loginResponse = response.body();
                            if (loginResponse.getStatus()) {
                                SharedPrefManager.getInstance(LoginActivity.this).saveUser(email, password);
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }

                            Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);

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


//    public void saveUser(String email, String password) {
//        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_NAME,MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(Constants.ADMIN_EMAIL, email);
//        editor.putString(Constants.ADMIN_PASSWORD, password);
//        editor.apply();
//    }
}

