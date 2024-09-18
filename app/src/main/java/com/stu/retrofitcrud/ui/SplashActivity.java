package com.stu.retrofitcrud.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.activities.HomeActivity;
import com.stu.retrofitcrud.ui.login.LoginActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences sharedPreferences=getSharedPreferences("Login", MODE_PRIVATE);
                    Boolean isLoggedIn=sharedPreferences.getBoolean("isLoggedIn",false);
                    Intent intent;
                    if(isLoggedIn) {
                        intent = new Intent(SplashActivity.this, HomeActivity.class);
                    }
                    else{
                        intent=new Intent(SplashActivity.this, LoginActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }
}