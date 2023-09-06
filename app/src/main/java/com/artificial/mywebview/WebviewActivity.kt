package com.artificial.mywebview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.webkit.WebView
import androidx.compose.ui.viewinterop.AndroidView
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.artificial.mywebview.ui.theme.MyWebViewTheme

class WebviewActivity : ComponentActivity() {
    private val webViewUrl = "https://radio-episode.bdlove24.com/2022/10/bhootcom-by-rj-russell-episode-142-28.html"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWebViewTheme {
                WebViewScreen(webViewUrl)
            }
        }
    }

    @Composable
    fun WebViewScreen(url: String) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                createWebView(context, url)
            }
        )
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun createWebView(context: Context, url: String): WebView {
        val webView = WebView(context).apply {
            webViewClient = WebViewClient()
            settings.apply {
                javaScriptEnabled = true
                allowContentAccess = true
                allowFileAccess = true
                allowFileAccessFromFileURLs = true
                allowUniversalAccessFromFileURLs = true

                // Enable geolocation
                setGeolocationEnabled(true)

                // Enable app cache
                val cachePath = context.cacheDir.absolutePath
                databaseEnabled = true
                domStorageEnabled = true

                // Other settings as needed
                // For example, enabling mixed content mode
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

                // Adjust text zoom
                textZoom = 100

                // Enable built-in zoom controls
                builtInZoomControls = true

                // Display zoom controls
                displayZoomControls = false

                // Enable wide viewport
                useWideViewPort = true

                // Load the URL
                loadUrl(url)
            }
        }
        webView.loadUrl(url)
        return webView
    }

}