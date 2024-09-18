package com.stu.retrofitcrud.interfaces;

import com.stu.retrofitcrud.getStudents.model.GetStudentsListModel;

public interface UserListButtonsClickListener {
    void onDetailButtonClicked(GetStudentsListModel student);
    void onDeleteButtonClicked(GetStudentsListModel student);

}
