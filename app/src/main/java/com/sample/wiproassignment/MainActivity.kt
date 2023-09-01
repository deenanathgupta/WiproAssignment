package com.sample.wiproassignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.wiproassignment.adapter.AlbumAdapter
import com.sample.wiproassignment.common.NoInternetLayout
import com.sample.wiproassignment.databinding.ActivityMainBinding
import com.sample.wiproassignment.viewmodel.AlbumViewModel
import com.sample.wiproassignment.viewmodel.AlbumViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NoInternetLayout.Callback {
    private val viewModel by viewModels<AlbumViewModel>()
    private var albumAdapter = AlbumAdapter(emptyList())
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initView()
        viewModel.getAlbumList()
        getAlbums()
    }

    private fun getAlbums() {
        lifecycleScope.launch {
            viewModel.albumUiState.collect {
                when (it) {
                    is AlbumViewState.Success -> {
                        (binding.rvTrendingRepos.adapter as AlbumAdapter).updateData(it.data)
                    }
                }

            }
        }
    }

    private fun initView() {
        binding.lytNoInternet.setCallbackListener(this)
        with(binding.rvTrendingRepos) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = albumAdapter
        }
    }

    override fun onRetryClick() {
        viewModel.getAlbumList()
    }
}