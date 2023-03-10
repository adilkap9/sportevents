package com.example.sportevents.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.sportevents.data.Constants
import com.example.sportevents.databinding.FragmentWebviewBinding

class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webViewSetup()
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup() = with(binding) {
        webView.webViewClient = WebViewClient()
        webView.apply {
            loadUrl(Constants.WEBVIEW_URL)
            settings.javaScriptEnabled = true
        }
    }
}