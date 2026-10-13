package com.roc.opencode.ui

import android.content.Context
import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.roc.opencode.voice.VoiceController

@Composable
fun AppShell(webView: WebView, context: Context) {
    var isListening by remember { mutableStateOf(false) }
    val voiceController = remember { VoiceController(context) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { webView },
            modifier = Modifier.fillMaxSize()
        )

        FloatingActionButton(
            onClick = {
                if (isListening) {
                    voiceController.stopListening()
                    isListening = false
                } else {
                    voiceController.startListening { text ->
                        webView.evaluateJavascript(
                            "(function() {" +
                            "  var input = document.querySelector('textarea, input[type=\"text\"]');" +
                            "  if (input) { input.value = \"$text\"; input.dispatchEvent(new Event('input')); }" +
                            "})();",
                            null
                        )
                    }
                    isListening = true
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            shape = CircleShape,
            containerColor = if (isListening) Color.Red else MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = if (isListening) Icons.Default.MicOff else Icons.Default.Mic,
                contentDescription = if (isListening) "Stop listening" else "Start listening",
                tint = Color.White
            )
        }
    }
}
