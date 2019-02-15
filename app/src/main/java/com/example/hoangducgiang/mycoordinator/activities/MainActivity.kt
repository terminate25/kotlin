package com.example.hoangducgiang.mycoordinator.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.hoangducgiang.mycoordinator.R
import com.example.hoangducgiang.mycoordinator.logic.Login


class MainActivity : AppCompatActivity() {

    private var mLoginBtn : Button? = null
    private var mSettingBtn : ImageButton? = null
    private var mUserName : EditText? = null
    private var mPassword : EditText? = null
    private var mHandler : Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        mLoginBtn = findViewById(R.id.loginBtn)
        mSettingBtn = findViewById(R.id.settingBtn)
        mUserName = findViewById(R.id.username)
        mPassword = findViewById(R.id.password)

        initSettingCallBack()
        initLoginCallBack()
        initHandlerMessage()

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
            mLogin.doLogin(mHandler, mUserName, mPassword)
        }
    }

    private fun initHandlerMessage() {
        mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(message: Message) {
                when (message.what) {
                    1 -> {
                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    2 -> createAlert("Connected Sensor", message.obj.toString())
                    3 -> createAlert("Connected Sensor", "Invalid user or password")
                }
            }
        }
    }

    private fun createAlert(title: String, Content: String) {
        val alertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(Content)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, resources.getString(R.string.ok)
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }
    //Auto ILogin

    //Init

    //ILogin

    //Get Saved Value

}
