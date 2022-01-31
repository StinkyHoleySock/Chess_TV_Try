package com.example.chess_tv_try.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.chess_tv_try.MainActivity
import com.example.chess_tv_try.R
import kotlinx.android.synthetic.main.fragment_streams.*


class StreamsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity?)
            ?.setActionBarTitle("Трансляции")


    }

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_streams, container, false)
        val webView = view.findViewById(R.id.webview_player_view) as WebView
        webView.loadUrl("https://www.youtube.com/results?search_query=%D1%88%D0%B0%D1%85%D0%BC%D0%B0%D1%82%D1%8B&sp=EgJAAQ%253D%253D")

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.webViewClient = WebViewClient()

        return view
    }

}