package com.example.chapter03_4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter03_4.databinding.ActivityMainBinding
import com.example.chapter03_4.mvc.MvcActivity
import com.example.chapter03_4.mvp.MvpActivity
import com.example.chapter03_4.mvvm.MvvmActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }
    }

    fun openMvc() {
        startActivity(Intent(this, MvcActivity::class.java))
    }

    fun openMvp() {
        startActivity(Intent(this, MvpActivity::class.java))
    }

    fun openMvvm() {
        startActivity(Intent(this, MvvmActivity::class.java))
    }

    fun openMvi() {

    }
}