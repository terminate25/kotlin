package com.example.extendlib.websocket.interfaces

import com.example.extendlib.websocket.enums.SocketIdEnum
import java.lang.Exception
import org.java_websocket.handshake.ServerHandshake

interface IWebSocketListener {
    fun onError(socketId: SocketIdEnum?, ex: Exception)
    fun onMessage(socketId: SocketIdEnum?, message: String)
    fun onOpen(socketId: SocketIdEnum?, serverHandshakeData: ServerHandshake)
    fun onClose(socketId: SocketIdEnum?, code: Int, reason: String, remote: Boolean)
}