package com.example.extendlib.websocket

import android.os.Handler
import android.util.Log
import com.example.extendlib.websocket.enums.SocketIdEnum
import com.example.extendlib.websocket.interfaces.IWebSocketListener
import com.example.extendlib.websocket.util.SendMessageEventCode.Companion.SEND_MESSAGE_EVENT_CODE_ERROR
import com.example.extendlib.websocket.util.SendMessageEventCode.Companion.SEND_MESSAGE_EVENT_CODE_NOT_OPEN
import com.example.extendlib.websocket.util.SendMessageEventCode.Companion.SEND_MESSAGE_EVENT_CODE_OPENED
import com.example.extendlib.websocket.util.SendMessageEventCode.Companion.SEND_MESSAGE_EVENT_CODE_SUCCESS
import com.example.extendlib.websocket.util.SendMessageEventCode.Companion.SEND_MESSAGE_EVENT_CODE_TIME_OUT
import com.example.extendlib.websocket.util.ServerDefault
import org.java_websocket.WebSocket
import org.java_websocket.client.WebSocketClient
import org.java_websocket.drafts.Draft
import org.java_websocket.handshake.ServerHandshake
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.lang.NumberFormatException
import java.net.URI
import java.net.URISyntaxException
import java.util.*
import java.util.concurrent.TimeoutException
import kotlin.collections.ArrayList

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
//        set(value) {
//            serverAddress = value
//        }
    var serverPort : Int = ServerDefault.ServerPort
//        set(value) {
//            serverPort = value
//        }
    var serverTimeout : Long = ServerDefault.ServerTimeout
//        set(value) {
//            serverTimeout = value
//        }
    var serverReopenDelay : Int = ServerDefault.ServerReopenDelay
//        set(value) {
//            serverReopenDelay = value
//        }

    var serverSendDataInterval : Int = ServerDefault.ServerSendDataInterval
//        set(value) {
//        serverSendDataInterval = value
//    }

    private var onWorking: Boolean = false
    private var accessToken : String? = null
    private var eventSendMessage : IOnSendMessageListener? = null
    private var messageSocket : MessageWebSocket? = null
    private val messageQueue  = Collections.synchronizedList(ArrayList<JSONObject>())
    /**
    * SETTER
    * */

//    public fun setServerAddress(value: String) {
//        this.serverAddress = value
//    }

//    public fun setServerPort(value: Int) {
//        this.serverPort = value
//    }
//
//    public fun setServerReopenDelay(value: Int) {
//        this.serverReopenDelay = value
//    }
//
//    public fun setSendDataInterval(value: Int) {
//        this.serverSendDataInterval = value
//    }
    private fun setEventSendMessage(eventSendMessage: IOnSendMessageListener) {
        this.eventSendMessage = eventSendMessage
    }


    /**
    END SET
    * */



    /**
     * METHODS
     */

//    private fun openSocket() {
//        this.onWorking = true
//        this.openMessageSocket()
//    }
//
//    private fun storeMessage() {
//        var temp : MutableList<JSONObject>
//        synchronized(messageQueue) {
//            temp = ArrayList(messageQueue)
//            messageQueue.clear()
//        }
//        for (json in temp) {
//            db.
//        }
//        temp.clear()
//    }
//
//    private fun closeMessageSocket() {
//        this.storeMessage()
//        if (this.messageSocket != null) {
//            this.messageSocket!!.setListener(SocketIdEnum.MESSAGE, null)
//            this.messageSocket!!.close()
//        }
//    }
//
//    private fun openMessageSocket() {
//        if (this.accessToken == null) {
//            if (this.eventSendMessage != null) {
//                this.eventSendMessage!!.onSendMessage(SEND_MESSAGE_EVENT_CODE_NOT_OPEN, Exception("Not Login yet"))
//            }
//        }
//        val headers = HashMap<String, String>()
//        headers.put("Sec-WebSocket-Protocol", this.accessToken!!)
//        try {
//            closeMessageSocket()
//        }
//    }
    /**
     * IMPLEMENTS
     */
    override fun onError(socketId: SocketIdEnum?, ex: Exception) {
//        when (socketId) {
//            SocketIdEnum.LOGIN -> if (this.eventLoginCompleted != null) {
//                this.eventLoginCompleted!!.onCompleted(false, 1, ex.message)
//            }
//            SocketIdEnum.MESSAGE -> {
//                // store message and close socket
//                closeMessageSocket()
//
//                if (ex is TimeoutException) {
//                    if (eventSendMessage != null) {
//                        eventSendMessage!!.onSendMessage(SEND_MESSAGE_EVENT_CODE_TIME_OUT, null)
//                    }
//                } else {
//                    if (eventSendMessage != null) {
//                        eventSendMessage!!.onSendMessage(SEND_MESSAGE_EVENT_CODE_ERROR, ex)
//                    }
//                }
//
//                // Reopen
//                openSocketDelay()
//            }
//        }
        var a  = ""
    }


    override fun onMessage(socketId: SocketIdEnum?, message: String) {
//        when (socketId) {
//            SocketIdEnum.LOGIN -> try {
//                val json = JSONObject(message)
//                if (json.getBoolean("success")) {
//                    this.access_token = json.getString("access_token")
//                    // Tell main thread to create message socket - comment out 20181115 user open socket by them self
//                    //                        Message messageToHandler = mHandler.obtainMessage(HANDLE_WHAT_OPEN_SOCKET, access_token);
//                    //                        messageToHandler.sendToTarget();
//                    this.eventLoginCompleted!!.onCompleted(true, 0, null)
//                } else {
//                    this.eventLoginCompleted!!.onCompleted(false, 2, "Wrong user name or password.")
//                }
//            } catch (e: JSONException) {
//                this.eventLoginCompleted!!.onCompleted(false, 0, e.message)
//                e.printStackTrace()
//            }
//
//            SocketIdEnum.MESSAGE -> {
//                if (eventSendMessage != null) {
//                    eventSendMessage!!.onSendMessage(SEND_MESSAGE_EVENT_CODE_SUCCESS, null)
//                }
//
//
//                // remove sent message
//                //int count = SEND_LOT;
//                try {
//                    val count = Integer.parseInt(message)
//                    synchronized(messageQueue) {
//                        for (i in 0 until count) {
//                            if (messageQueue.size > 0) {
//                                messageQueue.removeAt(0)
//                            }
//                        }
//                    }
//                } catch (ignored: NumberFormatException) {
//
//                }
//
//            }
//        }
        var b = ""
    }

    override fun onOpen(socketId: SocketIdEnum?, serverHandshakeData: ServerHandshake) {
        if (socketId == SocketIdEnum.MESSAGE && eventSendMessage != null) {
            eventSendMessage!!.onSendMessage(SEND_MESSAGE_EVENT_CODE_OPENED, Exception())
        }
    }

    override fun onClose(socketId: SocketIdEnum?, code: Int, reason: String, remote: Boolean) {
//        when (socketId) {
//            SocketIdEnum.LOGIN -> {
//            }
//            SocketIdEnum.MESSAGE ->
//                // Store unsent message
//                this.storeMessages()
//        }
        var c = ""
    }


    private var eventLoginCompleted : IOnLoginCompleted? = object : IOnLoginCompleted {
        override fun onCompleted(success: Boolean, errorCode: Int, errorMessage: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    fun login(userName: String, password: String, onCompleted: IOnLoginCompleted) {
        Log.e("UserName is ", userName)
        Log.e("Password is ", password)
        Log.e("ServerURL ", this.serverAddress)


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
            this.timeout = timeOut

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

    private inner class MessageWebSocket constructor(serverURI: URI, draft: Draft, headers : HashMap<String, String>, connectionTimeout: Int)
        : CommonWebSocket(serverURI, draft, headers, connectionTimeout) {
        fun message(message : JSONArray, timeOut: Long) {
            if (!isProcessing && readyState == WebSocket.READYSTATE.OPEN) {
                this.setTimeout(timeOut)
                this.send(message.toString())
            }
        }
    }
    /**
     * INNER INTERFACE
     *
     */
    interface IOnLoginCompleted {
        fun onCompleted(success: Boolean, errorCode: Int, errorMessage: String)
    }

    interface IOnSendMessageListener {
        fun onSendMessage(code: Int, ex: Exception)
    }
}