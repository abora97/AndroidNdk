package com.abora.ndkexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.abora.ndkexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var num1 = ""
    var num2 = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()

        clicks()
    }


    private fun clicks() {
        binding.btnAdd.setOnClickListener {
            getNumFromUi()
            if (checkNumbrs(num1, num2)) {
                binding.sampleText.text = add(num1.toInt(), num2.toInt()).toString()
            }
        }
        binding.btnSub.setOnClickListener {
            getNumFromUi()
            if (checkNumbrs(num1, num2)) {
                binding.sampleText.text = sub(num1.toInt(), num2.toInt()).toString()
            }
        }
        binding.btnMultiply.setOnClickListener {
            getNumFromUi()
            if (checkNumbrs(num1, num2)) {
                binding.sampleText.text = multiply(num1.toInt(), num2.toInt()).toString()
            }
        }
        binding.btnDivide.setOnClickListener {
            getNumFromUi()
            if (checkNumbrs(num1, num2)) {
                if (num2 != "0")
                    binding.sampleText.text = divide(num1.toInt(), num2.toInt()).toString()
            }
        }
    }


    private fun checkNumbrs(num1: String, num2: String): Boolean {
        return num1.isNullOrEmpty().not() && num2.isNullOrEmpty().not()
    }

    private fun getNumFromUi() {
        num1 = binding.edNum1.text.toString()
        num2 = binding.edNum2.text.toString()
    }


    /**
     * A native method that is implemented by the 'ndkexample' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String
    external fun add(x: Int, y: Int): Int
    external fun sub(x: Int, y: Int): Int
    external fun multiply(x: Int, y: Int): Int
    external fun divide(x: Int, y: Int): Int

    companion object {
        // Used to load the 'ndkexample' library on application startup.
        init {
            System.loadLibrary("ndkexample")
        }
    }
}