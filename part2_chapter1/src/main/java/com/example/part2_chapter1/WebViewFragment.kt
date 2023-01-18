package com.example.part2_chapter1

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.part2_chapter1.databinding.FragmentWebviewBinding

class WebViewFragment(
    private val position: Int,
    private val webViewUrl: String,
) : Fragment() {
    private lateinit var binding: FragmentWebviewBinding

    var listener: OnTabLayoutNameChanged? = null

    companion object {
        const val SHARED_PREFERENCE = "WEB_HISTORY"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.apply {
            webViewClient = WebtoonWebViewClient(binding.prgressBar) { url ->
                requireActivity().getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
                    .edit {
                        putString("tab$position", url).commit()
                    }
            }
            settings.javaScriptEnabled = true
            loadUrl(webViewUrl)
        }

        binding.backToLastButton.setOnClickListener {
//            activity?.getSharedPreferences()
            val sharedPreference =
                requireActivity().getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
            val url = sharedPreference?.getString("tab$position", "")
            if (url.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "마지막 저장 시점이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.webView.loadUrl(url)
            }
        }

        binding.changeTabNameButton.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
            val editText = EditText(requireContext())

            dialog.setView(editText)
            dialog.setPositiveButton("저장") { _, _ ->
                requireActivity().getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
                    .edit {
                        putString("tab${position}_name", editText.text.toString())
                        listener?.nameChanged(position, editText.text.toString())
                    }
            }
            dialog.setNegativeButton("취소") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
        }
    }

    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }
}

interface OnTabLayoutNameChanged {
    fun nameChanged(position: Int, name: String)
}