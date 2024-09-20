package com.stu.retrofitcrud.ui.updatesstudents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.ui.addstudentsandgetstudentsbyid.CreateStudentActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView nameTv, emailTv, phoneTv, addressTv;
    private Button updateBtn;
    private ImageButton editIb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        nameTv = findViewById(R.id.nameTv);
        emailTv = findViewById(R.id.emailTv);
        phoneTv = findViewById(R.id.phoneTv);
        addressTv = findViewById(R.id.addressTv);
        editIb = findViewById(R.id.editIb);

        Intent intent = getIntent();
        if (intent.hasExtra("studentId")) {
            nameTv.setText("Name : " + intent.getStringExtra("name"));
            emailTv.setText("Email : " + intent.getStringExtra("email"));
            phoneTv.setText("Phone : " + intent.getStringExtra("phone"));
            addressTv.setText("Address :" + intent.getStringExtra("address"));
        }

        editIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editUserIntent = new Intent(DetailActivity.this, CreateStudentActivity.class);
                editUserIntent.putExtra("id", intent.getIntExtra("studentId", 0));
                editUserIntent.putExtra("name", intent.getStringExtra("name"));
                editUserIntent.putExtra("phone", intent.getStringExtra("phone"));
                editUserIntent.putExtra("email", intent.getStringExtra("email"));
                editUserIntent.putExtra("address", intent.getStringExtra("address"));
                startActivity(editUserIntent);
                finish();
            }
        });
    }
}