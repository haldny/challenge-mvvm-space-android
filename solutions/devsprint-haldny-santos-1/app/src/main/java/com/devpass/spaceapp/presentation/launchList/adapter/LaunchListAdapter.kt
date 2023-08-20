package com.devpass.spaceapp.presentation.launchList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devpass.spaceapp.databinding.ListItemBinding

class LaunchListAdapter : ListAdapter<LaunchModel, LaunchViewHolder>(LaunchModel) {

    override fun submitList(list: List<LaunchModel>?) {
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LaunchViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: LaunchModel) = binding.apply {
        binding.ivLogo.setImageResource(model.image)
        binding.tvNumber.text = model.number
        binding.tvName.text = model.name
        binding.tvDate.text = model.date
        binding.tvStatus.text = model.status
    }

    companion object {
        fun from(parent: ViewGroup): LaunchViewHolder {
            return LaunchViewHolder(
                ListItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}