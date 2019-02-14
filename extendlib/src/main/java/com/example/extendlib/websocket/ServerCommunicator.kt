package com.example.extendlib.websocket

import android.os.Handler
import com.example.extendlib.websocket.enums.SocketIdEnum
import com.example.extendlib.websocket.interfaces.IOnLoginCompleted
import com.example.extendlib.websocket.interfaces.IWebSocketListener
import com.example.extendlib.websocket.util.ServerDefault
import org.java_websocket.WebSocket
import org.java_websocket.client.WebSocketClient
import org.java_websocket.drafts.Draft
import org.java_websocket.handshake.ServerHandshake
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.net.URI
import java.net.URISyntaxException
import java.util.concurrent.TimeoutException
import kotlin.math.log

class ServerCommunicator private constructor() : IWebSocketListener{
    /**
     * SINGLETON
     */
    init {
        // Define in constructor

    }

    private object Holder {
        val INSTANCE = ServerCommunicator()
    }

    companion object {
        @JvmStatic
        fun getInstance() : ServerCommunicator {
            return Holder.INSTANCE
        }
    }

    /**
     * END SINGLETON
     */


    /**
    * DECLARATION
    * */
    var serverAddress : String = ServerDefault.ServerAddress
        set(value) {
            serverAddress = value
        }
    var serverPort : Int = ServerDefault.ServerPort
        set(value) {
            serverPort = value
        }
    var serverTimeout : Long = ServerDefault.ServerTimeout
        set(value) {
            serverTimeout = value
        }
    var serverReopenDelay : Int = ServerDefault.ServerReopenDelay
        set(value) {
            serverReopenDelay = value
        }

    var serverSendDataInterval : Int = ServerDefault.ServerSendDataInterval
        set(value) {
        serverSendDataInterval = value
    }

    /**
    * SETTER
    * */

//    private fun setServerAddress(value: String) {
//        this.serverAddress = value
//    }

//    private fun setServerPort(value: Int) {
//        this.serverPort = value
//    }
//
//    private fun setServerReopenDelay(value: Int) {
//        this.serverReopenDelay = value
//    }
//
//    private fun setSendDataInterval(value: Int) {
//        this.serverSendDataInterval = value
//    }


    /**
    END SET
    * */


    override fun onError(socketId: SocketIdEnum?, ex: Exception) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun onMessage(socketId: SocketIdEnum?, message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onOpen(socketId: SocketIdEnum?, serverHandshakeData: ServerHandshake) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClose(socketId: SocketIdEnum?, code: Int, reason: String, remote: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private var eventLoginCompleted : IOnLoginCompleted? = object : IOnLoginCompleted {
        override fun onCompleted(success: Boolean, errorCode: Int, errorMessage: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    fun login(userName: String, password: String, onCompleted: IOnLoginCompleted) {
        var loginSocket : LoginWebSocket? = null
        try {
            val uri : String = "ws://" + this.serverAddress + ":" + serverPort + "/login"
            loginSocket = LoginWebSocket(URI(uri))
        }
        catch (ex: URISyntaxException) {
            onCompleted.onCompleted(false, 0, ex.message!!)
            return
        }

        this.eventLoginCompleted = onCompleted
        loginSocket.setListener(SocketIdEnum.LOGIN,this)
        loginSocket.login(userName, password, this.serverTimeout)

    }

    /**
     * INNER CLASS
     */
    private open inner class CommonWebSocket : WebSocketClient {
        private var listener: IWebSocketListener? = null
        private var socketId: SocketIdEnum? = null
        internal var isProcessing = false
            private set

        internal var timerHandler = Handler()
        internal var timerRunnable : Runnable = object : Runnable {
            override fun run() {
                if (isProcessing) {
                    this@CommonWebSocket.onError(
                        TimeoutException("Time out")
                    )
                    //Close
                    if (this@CommonWebSocket.readyState == WebSocket.READYSTATE.OPEN) {
                        this@CommonWebSocket.close()
                    }
                }
                isProcessing = false
                timerHandler.removeCallbacks(this)
            }
        }

        constructor(serverUri: URI, draft: Draft, headers: Map<String, String>, connectionTimeout: Int) : super(serverUri, draft, headers, connectionTimeout) {}

        internal constructor(serverURI: URI) : super(serverURI) {}

        internal fun setListener(socketId: SocketIdEnum, listener: IWebSocketListener?) {
            this.listener = listener
            this.socketId = socketId
        }

        internal open fun waitUntilOpen(millis: Long): Boolean {
            val count = millis / 100
            var i: Long = 0
            while (this.readyState != WebSocket.READYSTATE.OPEN) {
                if (i > count) {
                    return false
                }
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    this.onError(e)
                    return false
                }
                i++
            }
            return true
        }

        override fun onOpen(handshakedata: ServerHandshake) {
            if (listener != null) {
                listener!!.onOpen(this.socketId, handshakedata)
            }
        }

        override fun onMessage(message: String) {
            // Message come, not time out
            isProcessing = false
            if (listener != null) {
                listener!!.onMessage(this.socketId, message)
            }
        }

        override fun onClose(code: Int, reason: String, remote: Boolean) {
            if (listener != null) {
                listener!!.onClose(this.socketId, code, reason, remote)
            }
        }

        override fun onError(ex: Exception) {
            // Error come, off time out
            isProcessing = false
            if (listener != null) {
                listener!!.onError(this.socketId, ex)
                // After error, socket invalidate, no need send event to server
                listener = null
            }
        }

        fun setTimeout(timeoutMillis: Long) {
            // Remove first
            timerHandler.removeCallbacks(timerRunnable)
            // Start timer
            timerHandler.postDelayed(timerRunnable, timeoutMillis)
            isProcessing = true
        }
    }

    private inner class LoginWebSocket constructor(serverURI: URI) : CommonWebSocket(serverURI) {
        private var userName : String? = null
        private var password : String? = null
        private var timeout : Long? = 0

        fun login(userName: String, password: String, timeOut: Long) {
            this.userName = userName
            this.password = password
            this.timeout = timeout

            this.setTimeout(timeOut)
            this.connect()
        }

        override fun waitUntilOpen(millis: Long): Boolean {
            return super.waitUntilOpen(millis)
        }

        override fun onOpen(handshakedata: ServerHandshake) {
            try{
                this.setTimeout(this.timeout!!)
                val json = JSONObject()
                json.put("user_id",this.userName)
                json.put("password",this.password)
                this.send(json.toString())
            }
            catch (ex: JSONException) {
                this.onError(ex)
            }
            super.onOpen(handshakedata)
        }
    }
}