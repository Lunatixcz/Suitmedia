package com.project.suitmedia.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.suitmedia.R
import com.project.suitmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnCheck.setCustomText("CHECK")
            btnNext.setCustomText("NEXT")
            etName.hint = "Name"
            etPalindrome.hint = "Palindrome"

            btnCheck.setOnClickListener {
                viewModel.checkPalindrome(etPalindrome.text.toString())
            }
        }

        viewModel.isPalindrome.observe(this) { isPalindrome ->
            val message = if (isPalindrome) "isPalindrome" else "not palindrome"

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}