package com.project.suitmedia.ui.second

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.suitmedia.R
import com.project.suitmedia.databinding.ActivitySecondScreenBinding
import com.project.suitmedia.ui.main.MainActivityViewModel
import com.project.suitmedia.ui.third.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private val viewModel : SecondScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USERNAME")

        with(binding) {
            tvUsername.text = username
            btnBack.setOnClickListener {
                finish()
            }

            btnSearchUser.setCustomText("Choose a User")
            btnSearchUser.setOnClickListener {
                val intent = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}