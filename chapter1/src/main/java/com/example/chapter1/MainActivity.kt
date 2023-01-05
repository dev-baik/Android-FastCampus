package com.example.chapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var number = 0

        binding.resetButton.setOnClickListener {
            number = 0
            binding.numberTextView.text = number.toString()
        }

        binding.plusButton.setOnClickListener {
            number += 1
            binding.numberTextView.text = number.toString()
        }
    }
}