package com.stu.retrofitcrud.interfaces;

import com.stu.retrofitcrud.ui.getStudents.model.GetStudentsListModel;

public interface UserListButtonsClickListener {
    void onDetailButtonClicked(GetStudentsListModel student);
    void onDeleteButtonClicked(GetStudentsListModel student);

}
