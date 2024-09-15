package com.stu.retrofitcrud.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class CommonMethods {

    public static Boolean validateLoginCredentials(Context context, String email, String password) {

        if (email.isEmpty()) {
            Toast.makeText(context, "Email Field cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!email.matches("^[a-zA-Z0-9]{1,20}@[a-z0-9]{1,20}.[a-zA-Z]{2,3}$") ||
                (!email.matches("^[a-zA-Z0-9]{1,20}.[a-zA-Z0-9]{1,20}@[a-z0-9]{1,20}.[a-zA-Z]{2,3}$"))) {
            Toast.makeText(context, "Enter only alphabetical character", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.isEmpty()) {
            Toast.makeText(context, "Password Field cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!password.matches("^.{6,}$")) {
            Toast.makeText(context, "Enter atleast 6 digits password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    public static Boolean validateSignupCredentials(Context context, String name, String email, String phone, String password, String confirmPassword) {
        if (name.isEmpty()) {
            Toast.makeText(context, "Name Field cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!name.matches("^[a-zA-Z ]+$")) {
            Toast.makeText(context, "Enter only alphabetical character in name", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()) {
            Toast.makeText(context, "Email Field cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!email.matches("^[a-zA-Z0-9]{1,20}@[a-z0-9]{1,20}.[a-zA-Z]{2,3}$") ||
                (!email.matches("^[a-zA-Z0-9]{1,20}.[a-zA-Z0-9]{1,20}@[a-z0-9]{1,20}.[a-zA-Z]{2,3}$"))) {
            Toast.makeText(context, "Enter only alphabetical character in email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (phone.isEmpty()) {
            Toast.makeText(context, "Phone field cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!phone.matches("^[+][0-9]{10,13}$")) {
            Toast.makeText(context, "Correct Format : +91XXXXXXXXXX", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.isEmpty()  || confirmPassword.isEmpty()){
            Toast.makeText(context, "Password Field cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!password.matches("^.{6,}$") &&  !confirmPassword.matches("^.{6,}$")) {
            Toast.makeText(context, "Enter atleast 6 digits password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.matches(confirmPassword)) {
            Toast.makeText(context, "Password not matched", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static Boolean checkCredentials(Context context,String email,String intentEmail, String password, String intentPassword){
        if(!email.matches(intentEmail)){
            Toast.makeText(context, "Email not matched", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!password.matches(intentPassword)){
            Toast.makeText(context, "Password not matched", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
    }