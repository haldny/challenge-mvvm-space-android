package com.devpass.spaceapp.presentation.launchList

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.devpass.spaceapp.R
import com.devpass.spaceapp.data.datasource.remote.retrofit.RetrofitClient
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSourceImpl
import com.devpass.spaceapp.data.repository.FetchLaunchesRepository
import com.devpass.spaceapp.data.repository.FetchLaunchesRepositoryImpl
import com.devpass.spaceapp.databinding.ActivityLaunchListBinding
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchListAdapter
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchModel

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
        viewModel.getLaunches()
        setupRecycleView()
        initLaunchList()
    }

    private fun init() {
       val remoteDataSource = RemoteDataSourceImpl(RetrofitClient.getSpaceXAPI())
        repository = FetchLaunchesRepositoryImpl(remoteDataSource)
    }

    private fun initLaunchList() {

        val launch1 = LaunchModel("Launch 1","1", "July 03, 2020", "Success", R.drawable.crs)
        val launch2 = LaunchModel("Launch 2","2", "July 03, 2020", "Success", R.drawable.falcon_sat)
        val launch3 = LaunchModel("Launch 3","3", "July 03, 2020", "Success", R.drawable.starlink)
        val launch4 = LaunchModel("Launch 4","4", "July 03, 2020", "Success", R.drawable.spacex_dragon_crs20_patch01)
        val launch5 = LaunchModel("Launch 5","5", "July 03, 2020", "Success", R.drawable.starlink)

        var launchList: List<LaunchModel> = listOf(launch1, launch2, launch3, launch4, launch5)
        adapter.submitList(launchList)
    }

    private fun setupRecycleView() {
        adapter = LaunchListAdapter()
        binding.rvLaunches.adapter = adapter
        binding.rvLaunches.layoutManager = LinearLayoutManager(this)
    }
}