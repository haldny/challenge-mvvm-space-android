package com.devpass.spaceapp.presentation.launchList

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.devpass.spaceapp.R
import com.devpass.spaceapp.data.datasource.remote.retrofit.RetrofitClient
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSourceImpl
import com.devpass.spaceapp.data.repository.FetchLaunchesRepository
import com.devpass.spaceapp.data.repository.FetchLaunchesRepositoryImpl
import com.devpass.spaceapp.databinding.ActivityLaunchListBinding
import com.devpass.spaceapp.presentation.LaunchActivity
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchListAdapter
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchModel
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is LaunchListUiState.Error -> {
                            TODO()
                        }

                        is LaunchListUiState.Success -> {
                            adapter.submitList(it.data)
                            setupLoadingVisibility(false)
                        }

                        is LaunchListUiState.Loading -> {
                            setupLoadingVisibility(true)
                        }
                    }
                }
            }
        }
    }

    private fun setupLoadingVisibility(visibility: Boolean) {
        with(binding.lottieLoading) {
            isVisible = visibility
            if (visibility) playAnimation()
        }
    }

    private fun setupToolbar() {
        with(binding.includeToolbar) {
            tvToolbarTitle.setText(R.string.text_toolbar_launch_list)
            back.isVisible = false
        }
    }

    private fun setupRecycleView() {
        adapter = LaunchListAdapter {
            setupLaunchActivity(it)
        }
        binding.rvLaunches.adapter = adapter
        binding.rvLaunches.layoutManager = LinearLayoutManager(this)
    }

    private fun setupLaunchActivity(launchModel: LaunchModel) {
        Intent(baseContext, LaunchActivity::class.java).also {
            it.putExtra(LAUNCH_MODEL, launchModel)
        }.run {
            baseContext.startActivity(this)
        }
    }

    private companion object {
        const val LAUNCH_MODEL = "LAUNCH_MODEL"
    }
}