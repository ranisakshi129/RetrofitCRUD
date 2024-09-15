package com.stu.retrofitcrud.activities;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.utils.CommonMethods;

public class LoginActivity extends AppCompatActivity {
        private EditText emailEdt , passwordEdt;
        private Button loginBtn , signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        emailEdt=findViewById(R.id.emailEdt);
        passwordEdt=findViewById(R.id.passwordEdt);
        loginBtn=findViewById(R.id.loginBtn);
        signUpBtn=findViewById(R.id.signUpBtn);

        Intent intent = getIntent();
        String intentEmail = intent.getStringExtra("email");
        String intentPassword = intent.getStringExtra("password");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEdt.getText().toString().trim();
                String password = passwordEdt.getText().toString().trim();
                if(CommonMethods.validateLoginCredentials(LoginActivity.this,email,password)) {
//                        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putBoolean("isLoggedIn", true);
//                        editor.apply();
                    if (CommonMethods.checkCredentials(LoginActivity.this, email, intentEmail, password, intentPassword)) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Credentials not matched.Please SignUp firstly", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                   // Toast.makeText(LoginActivity.this, "Credentials not matched.Please SignUp firstly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }

        });

    }
}