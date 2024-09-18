package com.stu.retrofitcrud.ui.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stu.retrofitcrud.model.StudentResponse;

public class LoginResponseModel {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("user")
    @Expose
    private StudentResponse  user;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StudentResponse getUser() {
        return user;
    }

    public void setUser(StudentResponse user) {
        this.user = user;
    }
}
