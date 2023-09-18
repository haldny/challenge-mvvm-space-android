package com.devpass.spaceapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.devpass.spaceapp.databinding.FragmentDetailsBinding

class FragmentDetails: Fragment() {

    private var binding: FragmentDetailsBinding? = null

    private val details by lazy {
        arguments?.get(ARG_DETAILS).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding?.tvTextCard?.text = details
        binding?.tvViewMore?.setOnClickListener {
            Intent(context, LaunchpadDetailsActivity::class.java).run {
                context?.startActivity(this)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.root
    }

    companion object {
        private const val ARG_DETAILS = "ARG_DETAILS"

        fun newInstance(details: String?) = FragmentDetails().apply {
            arguments = bundleOf(ARG_DETAILS to details)
        }
    }
}
