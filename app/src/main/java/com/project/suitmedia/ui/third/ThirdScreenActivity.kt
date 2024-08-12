package com.project.suitmedia.ui.third

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.suitmedia.R
import com.project.suitmedia.adapter.LoadingStateAdapter
import com.project.suitmedia.adapter.UserListAdapter
import com.project.suitmedia.api.ApiConfig
import com.project.suitmedia.api.ApiService
import com.project.suitmedia.databinding.ActivitySecondScreenBinding
import com.project.suitmedia.databinding.ActivityThirdScreenBinding
import com.project.suitmedia.factory.ViewModelFactory
import com.project.suitmedia.repository.UserRepository
import com.project.suitmedia.ui.second.SecondScreenActivity

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnBack.setOnClickListener {
                finish()
            }
        }
        setupRecycleView()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecycleView() {
        val adapter = UserListAdapter { selectedUserName ->
            val resultIntent = Intent().apply {
                putExtra(SecondScreenActivity.EXTRA_SELECTED_USER_NAME, selectedUserName)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        binding.rvUserList.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

        viewModel.user.observe(this) {pagingData ->
            Log.d("ThirdScreenActivity", "user data updated")
            adapter.submitData(lifecycle, pagingData)
        }

        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                showLoading(true)
            } else {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading : Boolean){
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}