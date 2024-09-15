package com.stu.retrofitcrud.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.stu.retrofitcrud.R;

public class CreateUsersActivity extends AppCompatActivity {
    private EditText nameEdt ,emailEdt, phoneEdt, addressEdt;
    private Button submitBtn;
    private ImageView personIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_users);

        nameEdt=findViewById(R.id.nameEdt);
        emailEdt=findViewById(R.id.emailEdt);
        phoneEdt=findViewById(R.id.phoneEdt);
        addressEdt=findViewById(R.id.addressEdt);
        submitBtn=findViewById(R.id.submitBtn);
        personIv=findViewById(R.id.personIv);

        personIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseProfilePicture();
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateUsersActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private  void chooseProfilePicture(){
        AlertDialog.Builder builder=new AlertDialog.Builder(CreateUsersActivity.this);
        LayoutInflater layoutInflater=getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.alert_dialog_box,null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        ImageView cameraIv = dialogView.findViewById(R.id.cameraIv);
        ImageView galleryIv = dialogView.findViewById(R.id.galleryIv);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

//        cameraIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                takepictureFromCamera();
//                alertDialog.cancel();
//            }
//        });
//
        galleryIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureFromGallery();
                alertDialog.cancel();
            }
        });
    }

    private void takePictureFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

//    private void takepictureFromCamera(){
//
//    }
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                if(requestCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    personIv.setImageURI(selectedImage);
                }
                break;
        }
    }
//
//    private void checkAndRequestPermission(){
//        if(Build.VERSION.SDK_INT >= 23){
//            int cameraPermission= ActivityCompat.checkSelfPermission(CreateUsersActivity.this, Manifest.permission.CAMERA);
//            if(cameraPermission == PackageManager.PERMISSION_DENIED){
//                ActivityCompat.requestPermissions(CreateUsersActivity.this,new String[]{Manifest.permission.CAMERA});
//                return false;
//            }
//        }
//        return true;
 //   }
}