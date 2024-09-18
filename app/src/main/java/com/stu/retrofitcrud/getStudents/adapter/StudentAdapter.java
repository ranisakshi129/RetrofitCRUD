package com.stu.retrofitcrud.getStudents.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.activities.DetailActivity;
import com.stu.retrofitcrud.addStudentsAndGetStudentsById.AddStudentsResponseModel;
import com.stu.retrofitcrud.getStudents.model.GetStudentsListModel;
import com.stu.retrofitcrud.interfaces.UserListButtonsClickListener;
import com.stu.retrofitcrud.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    List<GetStudentsListModel> studentList;
    Context context;
    UserListButtonsClickListener userListButtonsClickListener;

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        GetStudentsListModel student = studentList.get(position);
        holder.userId.setText(String.valueOf(student.getId()));
        holder.nameTv.setText(student.getName());
        holder.emailTv.setText(student.getEmail());
        holder.phoneTv.setText(student.getPhone());
        holder.addressTv.setText(student.getAddress());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteStudent(student.getId(), position);
            }
        });

        holder.detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userListButtonsClickListener.onDetailButtonClicked(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (studentList==null) {
            return 0;
        } else {
            return studentList.size();

        }
    }

    private void deleteStudent(int id, int position) {
        Call<AddStudentsResponseModel> call = RetrofitClient.getInstance().getApiInterface().deleteStudent(id);
        call.enqueue(new Callback<AddStudentsResponseModel>() {
            @Override
            public void onResponse(Call<AddStudentsResponseModel> call, Response<AddStudentsResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AddStudentsResponseModel responseModel = response.body();
                    if (responseModel.getStatus()) {
                        Toast.makeText(context, responseModel.getMessage(), Toast.LENGTH_SHORT).show();
                        studentList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, studentList.size());
                    } else {
                        Toast.makeText(context, "Failed to delete student", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Failed to delete student", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddStudentsResponseModel> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setOnButtonClickListener(UserListButtonsClickListener userListButtonsClickListener) {
        this.userListButtonsClickListener = userListButtonsClickListener;
    }

    public void setStudentList(List<GetStudentsListModel> studentList) {
        this.studentList = studentList;
        notifyDataSetChanged();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView userId;
        TextView nameTv, emailTv, phoneTv, addressTv;
        Button deleteBtn, detailBtn;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            emailTv = itemView.findViewById(R.id.emailTv);
            nameTv = itemView.findViewById(R.id.nameTv);
            phoneTv = itemView.findViewById(R.id.phoneTv);
            addressTv = itemView.findViewById(R.id.addressTv);
            userId = itemView.findViewById(R.id.idTv);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            detailBtn = itemView.findViewById(R.id.detailBtn);
        }
    }
}
