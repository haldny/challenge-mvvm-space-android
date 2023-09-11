package com.devpass.spaceapp.presentation.launchList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devpass.spaceapp.R
import com.devpass.spaceapp.databinding.ListItemBinding

class LaunchListAdapter(private var onItemClick: (LaunchModel) -> Unit) :
    ListAdapter<LaunchModel, LaunchViewHolder>(LaunchModel) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder.from(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LaunchViewHolder(
    private val binding: ListItemBinding,
    private var onItemClick: (LaunchModel) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: LaunchModel) = binding.apply {
        Glide.with(binding.root)
            .load(model.image)
            .into(binding.ivLogo)

        binding.tvNumber.text = model.number
        binding.tvName.text = model.name
        binding.tvDate.text = model.date
        binding.tvStatus.text =
            if (model.status) root.context.getString(R.string.text_status_success)
            else root.context.getString(R.string.text_status_failure)


        itemView.setOnClickListener {
            onItemClick(model)
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            onItemClick: (LaunchModel) -> Unit
        ): LaunchViewHolder {
            return LaunchViewHolder(
                ListItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                onItemClick
            )
        }
    }
}