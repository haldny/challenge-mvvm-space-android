package com.devpass.spaceapp.presentation.launchList

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
        setupRecycleView()
        initLaunchList()
    }

    private fun init() {
       val remoteDataSource = RemoteDataSourceImpl(RetrofitClient.getSpaceXAPI())
        repository = FetchLaunchesRepositoryImpl(remoteDataSource)
        viewModel.getLaunches()
    }

    private fun initLaunchList() {
        viewModel.list.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun setupRecycleView() {
        adapter = LaunchListAdapter()
        binding.rvLaunches.adapter = adapter
        binding.rvLaunches.layoutManager = LinearLayoutManager(this)
    }
}