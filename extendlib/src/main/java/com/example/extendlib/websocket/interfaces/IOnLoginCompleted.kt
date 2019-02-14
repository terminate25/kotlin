package com.example.extendlib.websocket.interfaces

interface IOnLoginCompleted {
    fun onCompleted(success: Boolean, errorCode: Int, errorMessage: String)
}