package com.example.rip1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rip1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.button.setOnClickListener {
      if (isLuhnValid(binding.editTextNumber.toString()))
        binding.textView2.text = "Номер карты корректный"
      else
        binding.textView2.text = "Ошибка в номере карты!"
      Log.d("Log","${isLuhnValid(binding.editTextNumber.toString())}")
    }
  }
  private fun isLuhnValid(cardNumber: String): Boolean {
    var sum = 0
    val nDigits = cardNumber.length
    val parity = nDigits % 2

    for (i in 0 until nDigits) {
      var digit = Character.getNumericValue(cardNumber[i])
      if (i % 2 == parity) {
        digit *= 2
        if (digit > 9) {
          digit -= 9
        }
      }

      sum += digit
    }

    return sum % 10 == 0
  }
}