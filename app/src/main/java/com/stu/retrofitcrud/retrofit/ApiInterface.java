package com.stu.retrofitcrud.retrofit;
import com.stu.retrofitcrud.ui.addstudentsandgetstudentsbyid.model.AddStudentsRequestModel;
import com.stu.retrofitcrud.ui.addstudentsandgetstudentsbyid.model.AddStudentsResponseModel;
import com.stu.retrofitcrud.ui.getStudents.model.GetStudentsResponseModel;
import com.stu.retrofitcrud.ui.login.model.LoginRequestModel;
import com.stu.retrofitcrud.ui.login.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {


        @POST("/api/loginUser")
        Call<LoginResponseModel> loginUser(@Body LoginRequestModel loginRequest);

        @POST("/api/addStudent")
        Call<AddStudentsResponseModel>  addStudent(@Body AddStudentsRequestModel addStudentsRequestModel);

        @GET("/api/getAllStudents")
        Call<GetStudentsResponseModel> getAllStudents();

        @POST("/api/updateStudent")
        Call<AddStudentsResponseModel> updateStudent(@Body AddStudentsRequestModel addStudentsRequestModel);

        @DELETE("/api/deleteStudent/{id}")
        Call<AddStudentsResponseModel> deleteStudent(@Path("id") Integer id );

        //@GET("/api/getStudentById/{id")
        //Call<AddStudentsResponseModel> getStudentById(@Path("id") int id);

    }

