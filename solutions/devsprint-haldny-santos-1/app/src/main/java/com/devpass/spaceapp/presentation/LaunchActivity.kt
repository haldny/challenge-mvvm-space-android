package com.devpass.spaceapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.devpass.spaceapp.R
import com.devpass.spaceapp.databinding.ActivityTabBinding
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchModel
import com.devpass.spaceapp.presentation.rocket.RocketFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabBinding

    private val model: LaunchModel? by lazy {
        intent.getParcelableExtra(LAUNCH_MODEL)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupViews()
        setupGlide()
    }

    private fun setupViews() {
        with(binding) {
            btRunBack.setOnClickListener {
                onBackPressed()
            }
            tvTittle.text = model?.name
            tvDate.text = model?.date
            tvStatus.text =
                if (model?.status == true) root.context.getString(R.string.text_status_success)
                else root.context.getString(R.string.text_status_failure)
        }
    }

    private fun setupGlide() {
        Glide.with(this@LaunchActivity)
            .load(model?.image)
            .into(binding.ivImageSpace)
    }

    private fun setupViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addListFragment(getFragments())
        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getPagesTitle()[position]
        }.attach()
    }

    private fun getPagesTitle(): List<String> {
        return if (model?.details.isNullOrEmpty()) {
            listOf(ROCKET, LAUNCHPAD)
        } else {
            listOf(DETAILS, ROCKET, LAUNCHPAD)
        }
    }

    private fun getFragments(): List<Fragment> {
        return if (model?.details.isNullOrEmpty()) {
            listOf(
                FragmentDetails.newInstance(model?.details),
                RocketFragment.newInstance(model?.rocketId)
            )
        } else {
            listOf(
                FragmentDetails.newInstance(model?.details),
                RocketFragment.newInstance(model?.rocketId),
                FragmentLaunchpad()
            )
        }
    }

    private companion object {
        const val LAUNCH_MODEL = "LAUNCH_MODEL"
        const val DETAILS = "Details"
        const val ROCKET = "Rocket"
        const val LAUNCHPAD = "Launchpad"
    }
}
