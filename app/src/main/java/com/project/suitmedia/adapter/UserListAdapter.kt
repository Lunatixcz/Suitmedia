package com.project.suitmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.suitmedia.api.UserResponseItem
import com.project.suitmedia.databinding.ItemUserBinding

class UserListAdapter(private val onClick: (String) -> Unit) :
    PagingDataAdapter<UserResponseItem, UserListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
            holder.itemView.setOnClickListener {
                val fullName = listOfNotNull(data.firstName, data.lastName).joinToString(" ")
                onClick(fullName)
            }
        }
    }

    class MyViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserResponseItem) {
            binding.tvEmail.text = data.email
            binding.tvUsername.text = "${data.firstName} ${data.lastName}"

            Glide.with(itemView.context)
                .load(data.avatar)
                .circleCrop()
                .into(binding.ivUserProfile)
            }
        }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserResponseItem>() {
            override fun areItemsTheSame(oldItem: UserResponseItem, newItem: UserResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserResponseItem, newItem: UserResponseItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}