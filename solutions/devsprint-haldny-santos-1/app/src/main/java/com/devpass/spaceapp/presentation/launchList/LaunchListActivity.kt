package com.devpass.spaceapp.presentation.launchList

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.devpass.spaceapp.R
import com.devpass.spaceapp.data.datasource.remote.retrofit.RetrofitClient
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSourceImpl
import com.devpass.spaceapp.data.repository.FetchLaunchesRepository
import com.devpass.spaceapp.data.repository.FetchLaunchesRepositoryImpl
import com.devpass.spaceapp.databinding.ActivityLaunchListBinding
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchListAdapter

class LaunchListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchListBinding

    private lateinit var adapter: LaunchListAdapter
    private lateinit var repository: FetchLaunchesRepository

    private val viewModel: LaunchListViewModel by viewModels {
        LaunchListViewModel.providerFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setupToolbar()
        setupRecycleView()
        setupObserve()
    }

    private fun init() {
        val remoteDataSource = RemoteDataSourceImpl(RetrofitClient.getSpaceXAPI())
        repository = FetchLaunchesRepositoryImpl(remoteDataSource)
        viewModel.getLaunches()
    }

    private fun setupObserve() {
        viewModel.launchList.observe(this) {
            when (it) {
                is LaunchListUiState.Error -> {
                    TODO()
                }

                is LaunchListUiState.Success -> {
                    adapter.submitList(it.data)
                }

                is LaunchListUiState.Loading -> {
                    setupProgress(it.value)
                }
            }
        }
    }

    private fun setupProgress(value: Boolean) {
        binding.pbLaunches.isActivated = value
        binding.pbLaunches.isVisible = value
    }

    private fun setupToolbar() {
        with(binding.includeToolbar) {
            tvToolbarTitle.setText(R.string.text_toolbar_launch_list)
            back.isVisible = false
        }
    }

    private fun setupRecycleView() {
        adapter = LaunchListAdapter()
        binding.rvLaunches.adapter = adapter
        binding.rvLaunches.layoutManager = LinearLayoutManager(this)
    }
}