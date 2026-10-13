package com.roc.opencode.bridge

import android.content.Context
import android.webkit.JavascriptInterface
import com.roc.opencode.notification.NotificationHelper
import com.roc.opencode.voice.TtsManager

class JsBridge(private val context: Context) {

    @JavascriptInterface
    fun speakText(text: String) {
        TtsManager.speak(context, text)
    }

    @JavascriptInterface
    fun notify(title: String, body: String) {
        NotificationHelper.show(context, title, body)
    }

    @JavascriptInterface
    fun log(message: String) {
        android.util.Log.d("OpenCodeWeb", message)
    }
}
