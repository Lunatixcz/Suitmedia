package com.project.suitmedia.ui.second

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.suitmedia.R
import com.project.suitmedia.databinding.ActivitySecondScreenBinding
import com.project.suitmedia.ui.third.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private val viewModel: SecondScreenViewModel by viewModels()

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
                startActivityForResult(intent, REQUEST_CODE_SELECT_USER)
            }
        }

        viewModel.selectedUsername.observe(this) { selectedUserName ->
            binding.tvSelectedUsername.text = selectedUserName ?: "Selected User Name"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @Deprecated("Deprecated in favor of the Activity Result API")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_USER && resultCode == Activity.RESULT_OK) {
            val selectedUserName = data?.getStringExtra(EXTRA_SELECTED_USER_NAME)
            selectedUserName?.let {
                viewModel.setSelectedUsername(it)
            }
        }
    }

    companion object {
        const val REQUEST_CODE_SELECT_USER = 1
        const val EXTRA_SELECTED_USER_NAME = "extra_selected_user_name"
    }
}
