package com.example.chapter03_4.mvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter03_4.databinding.ActivityMvvmBinding
import com.example.chapter03_4.mvvm.repository.ImageRepositoryImpl

class MvvmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvvmBinding
    private val viewModel: MvvmViewModel by viewModels {
        MvvmViewModel.MvvmViewModelFactory(ImageRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.lifecycleOwner = this
            it.view = this
            it.viewModel = viewModel
        }
    }

    fun loadImage() {
        viewModel.loadRandomImage()
    }
}