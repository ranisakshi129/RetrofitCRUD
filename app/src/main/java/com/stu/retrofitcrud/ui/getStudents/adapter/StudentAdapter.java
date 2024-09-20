package com.stu.retrofitcrud.ui.getStudents.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.ui.getStudents.model.GetStudentsListModel;
import com.stu.retrofitcrud.interfaces.UserListButtonsClickListener;

import java.util.ArrayList;
import java.util.List;

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
                userListButtonsClickListener.onDeleteButtonClicked(student);
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
        ProgressBar deleteStudentPb;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            emailTv = itemView.findViewById(R.id.emailTv);
            nameTv = itemView.findViewById(R.id.nameTv);
            phoneTv = itemView.findViewById(R.id.phoneTv);
            addressTv = itemView.findViewById(R.id.addressTv);
            userId = itemView.findViewById(R.id.idTv);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            detailBtn = itemView.findViewById(R.id.detailBtn);
            deleteStudentPb=itemView.findViewById(R.id.deleteStudentPb);
        }
    }
}
