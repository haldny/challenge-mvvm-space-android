package com.devpass.spaceapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.devpass.spaceapp.databinding.ActivityTabBinding
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchModel
import com.devpass.spaceapp.presentation.rocket.RocketFragment

class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentModel: LaunchModel? = intent.getParcelableExtra(LAUNCH_MODEL)
        Log.d("MODEL", intentModel.toString())

        val fragments = listOf(FragmentDetails(), RocketFragment(), FragmentLaunchpad())
        val fragmentsPageTitle = listOf("Details", "Rocket", "Launchpad")
        val viewPagerAdapter = ViewPagerAdapter(
            fragments = fragments,
            fragmentManager = supportFragmentManager,
            tittles = fragmentsPageTitle
        )

        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    private companion object {
        const val LAUNCH_MODEL = "LAUNCH_MODEL"
    }
}