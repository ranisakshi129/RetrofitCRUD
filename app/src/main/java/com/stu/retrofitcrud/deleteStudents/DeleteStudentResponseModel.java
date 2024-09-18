package com.stu.retrofitcrud.deleteStudents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteStudentResponseModel {

@SerializedName("status")
@Expose
private Boolean status;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private Object data;

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

public Object getData() {
return data;
}

public void setData(Object data) {
this.data = data;
}

}