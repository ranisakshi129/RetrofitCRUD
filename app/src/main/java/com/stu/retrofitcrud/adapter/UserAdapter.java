package com.stu.retrofitcrud.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.retrofitcrud.R;
import com.stu.retrofitcrud.activities.CreateUsersActivity;
import com.stu.retrofitcrud.activities.DetailActivity;
import com.stu.retrofitcrud.activities.HomeActivity;
import com.stu.retrofitcrud.model.UserResponseModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    List<UserResponseModel> userList;
    Context context;

    public UserAdapter(List<UserResponseModel> userList){
        this.userList=userList;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserResponseModel user = userList.get(position);
        holder.nameTv.setText(user.getName());
        holder.emailTv.setText(user.getEmail());
        holder.phoneTv.setText(user.getPhone());
        holder.addressTv.setText(user.getAddress());
        holder.userIv.setImageResource(user.getImage());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
            }
        });
        holder.detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        ImageView userIv;
        TextView nameTv,emailTv,phoneTv,addressTv;
        Button deleteBtn,detailBtn;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            emailTv=itemView.findViewById(R.id.emailTv);
            nameTv=itemView.findViewById(R.id.nameTv);
            phoneTv=itemView.findViewById(R.id.phoneTv);
            addressTv=itemView.findViewById(R.id.addressTv);
            userIv=itemView.findViewById(R.id.userIv);
            deleteBtn=itemView.findViewById(R.id.deleteBtn);
            detailBtn=itemView.findViewById(R.id.detailBtn);
        }
    }
}
