package com.example.hoangducgiang.mycoordinator.interfaces

import android.text.Editable
import android.widget.EditText

interface ILogin {
    fun doLogin(userName: EditText?, password: EditText?)
}