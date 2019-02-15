package com.example.hoangducgiang.mycoordinator.interfaces
import android.os.Handler
import android.widget.EditText

interface ILogin {
    fun doLogin(handler: Handler?, userName: EditText?, password: EditText?)
}