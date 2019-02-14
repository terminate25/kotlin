package com.example.hoangducgiang.mycoordinator.logic

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.extendlib.websocket.ServerCommunicator
import com.example.hoangducgiang.mycoordinator.interfaces.ILogin
import com.example.hoangducgiang.mycoordinator.utils.MessageLogin

/**
 * Library
 */

class Login private constructor(): ILogin {
    /*
    * SINGLETON */
    init {
        // Define in constructor
    }

    private object Holder {
        val INSTANCE = Login()
    }

    companion object {
        @JvmStatic
        fun getInstance() : Login {
            return Holder.INSTANCE
        }
    }
    /*
    * END SINGLETON
    * */

    override fun doLogin(userName: EditText?, password: EditText?) {
        userName!!.validate({s -> s.isValidText()}, MessageLogin.UserNameEmpty)
        password!!.validate({s -> s.isValidText()}, MessageLogin.PasswordEmpty)

        this.login(userName.toString(), password.toString())

    }

    private val com = ServerCommunicator.getInstance()

    private fun login(userName: String, password: String) {
        //To DO get URL and Port from Setting
        var serverAddress : String = "192.168.0.206"
        var serverPort : Int = 5000
        var serverTimeout : Long = 5000
        //
        com.serverAddress = serverAddress
        com.serverPort = serverPort
        com.serverTimeout = serverTimeout

        com.login(userName, password) { success, errorCode, errorMessage ->
            if(success) {
                //TO DO
            }
            else{

            }
        }
    }

    private fun configTimeServer() {
        //TO DO Get from Setting
        var serverRetryInterval = 5000
        var serverSendDataInterval = 10000

        com.serverReopenDelay = serverRetryInterval
        com.serverSendDataInterval = serverSendDataInterval
    }
    /*
    * VALIDATE*/
    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    private fun EditText.validate(validator: (String) -> Boolean, message: String) {
        this.afterTextChanged {
            this.error = if (validator(it)) null else message
        }
        this.error = if (validator(this.text.toString())) null else message
    }

    private fun String.isValidText() : Boolean = this.isNotEmpty()
    /*
    * END VALIDATE*/
}