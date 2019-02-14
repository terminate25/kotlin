package com.example.extendlib.websocket

import com.example.extendlib.websocket.enums.SocketIdEnum
import com.example.extendlib.websocket.interfaces.IWebSocketListener
import org.java_websocket.client.WebSocketClient
import java.lang.Exception
import java.util.concurrent.TimeoutException
import android.os.Handler
import org.java_websocket.drafts.Draft
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class CommonWebSocket : WebSocketClient {
    private var listener: IWebSocketListener? = null
    private var socketId: SocketIdEnum? = null
    internal var isProcessing = false
        private set

    internal var timerHandler = Handler()
    internal var timeRunnable: Runnable = object : Runnable {
        override fun run() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        }
    }

    constructor(serverUri: URI, draft: Draft, headers: Map<String, String>, connecttimeout: Int) : super(serverUri, draft, headers, connecttimeout) {}

    internal constructor(serverURI: URI) : super(serverURI) {}

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


}