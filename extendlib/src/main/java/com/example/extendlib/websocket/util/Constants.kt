package com.example.extendlib.websocket.util

class ServerDefault {
    companion object {
        const val ServerAddress : String = ""
        const val ServerPort : Int = 5000
        const val ServerTimeout : Long = 5000
        const val ServerReopenDelay : Int = 10000
        const val ServerSendDataInterval = 5000
    }
}

class SendMessageEventCode {
    companion object {
        const val SEND_MESSAGE_EVENT_CODE_SUCCESS = 1
        const val SEND_MESSAGE_EVENT_CODE_REOPEN = 2
        const val SEND_MESSAGE_EVENT_CODE_TIME_OUT = 3
        const val SEND_MESSAGE_EVENT_CODE_NOT_OPEN = 4
        const val SEND_MESSAGE_EVENT_CODE_NOT_READY = 5
        const val SEND_MESSAGE_EVENT_CODE_ERROR = 6
        const val SEND_MESSAGE_EVENT_CODE_OPENED = 7
    }
}