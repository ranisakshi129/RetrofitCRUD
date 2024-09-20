package com.stu.retrofitcrud.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.ui.home.HomeActivity;
import com.stu.retrofitcrud.ui.login.LoginActivity;
import com.stu.retrofitcrud.ui.login.SharedPrefManager;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_splash);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (SharedPrefManager.getInstance(SplashActivity.this).isLoggedIn()) {
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    finish();
                }
            }, 3000);
        }
}