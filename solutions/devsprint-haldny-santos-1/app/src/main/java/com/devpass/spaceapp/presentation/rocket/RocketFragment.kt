package com.devpass.spaceapp.presentation.rocket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.devpass.spaceapp.data.datasource.remote.retrofit.RetrofitClient
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSourceImpl
import com.devpass.spaceapp.data.repository.rocket.RocketRepositoryImpl
import com.devpass.spaceapp.databinding.FragmentRocketBinding
import com.devpass.spaceapp.domain.usecase.RocketUseCaseImpl
import kotlinx.coroutines.launch

class RocketFragment : Fragment() {

    private var binding: FragmentRocketBinding? = null
    private lateinit var viewModel: RocketViewModel

    private val id by lazy {
        arguments?.get(ARG_ID).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRocketBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupObserve()
    }

    private fun init() {
        val remoteDataSource = RemoteDataSourceImpl(RetrofitClient.getSpaceXAPI())
        val repository = RocketRepositoryImpl(remoteDataSource)
        val rocketUseCase = RocketUseCaseImpl(repository)
        val factory = RocketViewModel.providerFactory(rocketUseCase)

        viewModel = ViewModelProvider(requireActivity(), factory)
            .get(modelClass = RocketViewModel::class.java)
        viewModel.getRocket(id)

    }

    private fun setupViews(name: String, description: String) {
        binding?.tvTittleCardRocket?.text = name
        binding?.tvTextCardRocket?.text = description
    }

    private fun setupGlide(image: String) {
        binding?.ivImageCard?.let {
            Glide.with(this)
                .load(image)
                .into(it)
        }
    }

    private fun setupError() {
        binding?.includeViewErrorState?.root?.isVisible = true
        binding?.includeViewErrorState?.lottieError?.playAnimation()
    }

    private fun setupLoadingVisibility(visibility: Boolean) {
        binding?.lottieLoading?.isVisible = visibility
        if (visibility) binding?.lottieLoading?.playAnimation()

    }

    private fun setupObserve() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is RocketUiState.Error -> {
                            setupLoadingVisibility(false)
                            setupError()
                        }

                        is RocketUiState.Success -> {
                            setupViews(it.data.name, it.data.description)

                            if (it.data.flickrImages.isNotEmpty()) setupGlide(
                                it.data.flickrImages.first()
                            )

                            setupLoadingVisibility(false)
                        }

                        is RocketUiState.Loading -> {
                            setupLoadingVisibility(true)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.root
    }

    companion object {
        private const val ARG_ID = "ARG_ID"

        fun newInstance(id: String?) = RocketFragment().apply {
            arguments = bundleOf(ARG_ID to id)
        }
    }
}
