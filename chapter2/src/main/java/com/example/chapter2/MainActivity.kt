package com.example.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.chapter2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var inputNumber = 0
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputEditText.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()) {
                0
            } else {
                text.toString().toInt()
            }

            if(cmToM) {
                binding.outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                binding.outputTextView.text = inputNumber.times(100).toString()
            }
        }

        binding.swapImageButton.setOnClickListener {
            cmToM = cmToM.not()
            if(cmToM) {
                binding.inputUnitTextView.text = "cm"
                binding.outputUnitTextView.text = "m"
                binding.outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                binding.inputUnitTextView.text = "m"
                binding.outputUnitTextView.text = "cm"
                binding.outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if(cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if(cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}