package com.devpass.spaceapp.presentation.launchList.adapter

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class LaunchModel(
    val name: String,
    val number: String,
    var date: String,
    val status: Boolean,
    val details: String,
    val image: String
) : Parcelable {
    companion object : DiffUtil.ItemCallback<LaunchModel>() {
        override fun areItemsTheSame(oldItem: LaunchModel, newItem: LaunchModel): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: LaunchModel, newItem: LaunchModel): Boolean {
            return oldItem == newItem
        }
    }
}