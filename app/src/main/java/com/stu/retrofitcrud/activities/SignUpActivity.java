package com.stu.retrofitcrud.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.utils.CommonMethods;

public class SignUpActivity extends AppCompatActivity {
    private EditText nameEdt ,  emailEdt ,  phoneEdt, passwordEdt , confirmPasswordEdt ;
    private Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        nameEdt=findViewById(R.id.nameEdt);
        emailEdt=findViewById(R.id.emailEdt);
        phoneEdt=findViewById(R.id.phoneEdt);
        passwordEdt=findViewById(R.id.passwordEdt);
        confirmPasswordEdt=findViewById(R.id.confirmPasswordEdt);
        signUpBtn=findViewById(R.id.signUpBtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdt.getText().toString().trim();
                String email = emailEdt.getText().toString().trim();
                String phone = phoneEdt.getText().toString().trim();
                String password = passwordEdt.getText().toString().trim();
                String confirmPassword = confirmPasswordEdt.getText().toString().trim();
                if (CommonMethods.validateSignupCredentials(SignUpActivity.this, name, email, phone, password, confirmPassword)) {
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    intent.putExtra("email",email);
                    intent.putExtra("password",password);
                    Toast.makeText(SignUpActivity.this, "User signup Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}