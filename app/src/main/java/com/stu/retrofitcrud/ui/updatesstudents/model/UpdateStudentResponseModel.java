package com.stu.retrofitcrud.ui.updatesstudents.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UpdateStudentResponseModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private UpdateStudentsListModel data;

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

    public UpdateStudentsListModel getData() {
        return data;
    }

    public void setData(UpdateStudentsListModel data) {
        this.data = data;
    }

}