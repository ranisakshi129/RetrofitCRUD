package com.stu.retrofitcrud.getStudents.model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStudentsResponseModel {
@SerializedName("status")
@Expose
private Boolean status;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private List<GetStudentsListModel> data;

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

public List<GetStudentsListModel> getData() {
return data;
}

public void setData(List<GetStudentsListModel> data) {
this.data = data;
}

}