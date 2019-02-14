package com.example.hoangducgiang.mycoordinator.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.hoangducgiang.mycoordinator.R
import com.example.hoangducgiang.mycoordinator.logic.Login
import com.example.hoangducgiang.mycoordinator.utils.MessageLogin


class MainActivity : AppCompatActivity() {

    private var mLoginBtn : Button? = null
    private var mSettingBtn : ImageButton? = null
    private var mUserNameText : EditText? = null
    private var mPassword : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        mLoginBtn = findViewById(R.id.loginBtn)
        mSettingBtn = findViewById(R.id.settingBtn)
        mUserNameText = findViewById(R.id.username)
        mPassword = findViewById(R.id.password)

        initSettingCallBack()
        initLoginCallBack()
    }

    private fun initSettingCallBack() {
        mSettingBtn!!.setOnClickListener{
            // DO Setting
        }
    }

    private fun initLoginCallBack() {
        mLoginBtn!!.setOnClickListener{
            // Do ILogin
            val mLogin = Login.getInstance()
            mLogin.doLogin(mUserNameText, mPassword)
        }
    }
    //Auto ILogin

    //Init

    //ILogin

    //Get Saved Value

}
