package com.stu.retrofitcrud.retrofit;
import com.stu.retrofitcrud.addStudentsAndGetStudentsById.AddStudentsRequestModel;
import com.stu.retrofitcrud.addStudentsAndGetStudentsById.AddStudentsResponseModel;
import com.stu.retrofitcrud.getStudents.model.GetStudentsResponseModel;
import com.stu.retrofitcrud.ui.login.model.LoginRequestModel;
import com.stu.retrofitcrud.ui.login.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {


        @POST("/api/loginUser")
        Call<LoginResponseModel> loginUser(@Body LoginRequestModel loginRequest);

        @POST("/api/addStudent")
        Call<AddStudentsResponseModel>  addStudent(@Body AddStudentsRequestModel addStudentsRequestModel);

        @GET("/api/getAllStudents")
        Call<GetStudentsResponseModel> getAllStudents();

        @PATCH("/api/updateStudent/{id}")
        Call<AddStudentsResponseModel> updateStudent(@Path("id") int id, @Body AddStudentsRequestModel addStudentsRequestModel);

        @DELETE("/api/deleteStudent/{id}")
        Call<AddStudentsResponseModel> deleteStudent(@Path("id") Integer id );

        //@GET("/api/getStudentById/{id")
        //Call<AddStudentsResponseModel> getStudentById(@Path("id") int id);

    }

