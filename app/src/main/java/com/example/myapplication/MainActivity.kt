package com.example.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webview = findViewById<WebView>(R.id.webview)
        val webSettings: WebSettings = webview.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.setSupportZoom(true)
        webSettings.defaultTextEncodingName = "utf-8"
        webSettings.setAllowFileAccess(true)
        webSettings.setAllowFileAccessFromFileURLs(true)
        webSettings.setAllowUniversalAccessFromFileURLs(true)
        webview.setWebChromeClient(WebChromeClient())
        webview.loadUrl("file:///android_asset/index.html")

        findViewById<Button>(R.id.click).setOnClickListener {
            val start = System.currentTimeMillis()
            val fcData = FCData()
            fcData.cap = 5
            fcData.resetTime = 10 * 60 * 60
            fcData.firstImpressionTime = 1605166200

            val gson = Gson()
            val fcdatastr = gson.toJson(fcData)
            val s = formatScript(
                "isLimitReached", "75941", fcdatastr, 5,
                System.currentTimeMillis()
            )
            webview.evaluateJavascript(s, ValueCallback {
                Log.d("chromium results", it)
                Log.d("chromium test time", (System.currentTimeMillis() - start).toString()+" milliseconds")
            })

        }
    }


    fun formatScript(function: String, vararg params: Any?): String {
        val builder = StringBuilder(function).append('(')
        val length = params.size
        for (i in params.indices) {
            if (params[i] is String) {
                builder.append("\'")
            }
            builder.append(params[i])
            if (params[i] is String) {
                builder.append("\'")
            }
            if (i != length - 1) {
                builder.append(",")
            }
        }
        builder.append(')')
        return builder.toString()
    }
}