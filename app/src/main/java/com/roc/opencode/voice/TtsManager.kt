package com.roc.opencode.voice

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

object TtsManager {

    private var tts: TextToSpeech? = null
    private var isReady = false

    fun init(context: Context) {
        if (tts != null) return
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.US
                isReady = true
            }
        }
    }

    fun speak(context: Context, text: String) {
        if (!isReady) init(context)

        val cleanText = text
            .replace(Regex("```[\\s\\S]*?```"), " code block ")
            .replace(Regex("`[^`]*`"), "")
            .replace(Regex("\\[.*?\\]\\(.*?\\)"), "")
            .replace(Regex("!\\[.*?\\]\\(.*?\\)"), "")
            .replace(Regex("#{1,6}\\s"), "")
            .replace(Regex("[*#_~>|]"), "")
            .replace(Regex("\\s+"), " ")
            .trim()

        if (cleanText.isNotEmpty() && cleanText.length < 500) {
            tts?.speak(cleanText, TextToSpeech.QUEUE_FLUSH, null, "opencode_tts")
        }
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.shutdown()
        tts = null
        isReady = false
    }
}
