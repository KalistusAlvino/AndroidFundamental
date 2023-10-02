package com.example.githubuser.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.data.response.ItemsItem
import com.example.githubuser.databinding.ItemRowUsersBinding
import com.example.githubuser.ui.fragment.FollowFragment

class UserAdapter : ListAdapter<ItemsItem,UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowUsersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        holder.itemView.setOnClickListener{
            val moveDetailUser = Intent(holder.itemView.context, DetailUserActivity::class.java)
            moveDetailUser.putExtra(DetailUserActivity.EXTRA_DETAIL,user.login)
            holder.itemView.context.startActivity(moveDetailUser)
        }
    }
    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>(){
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MyViewHolder(private val binding: ItemRowUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user : ItemsItem){
            Glide.with(binding.root.context)
                .load(user.avatarUrl)
                .into(binding.profileImage)
            binding.tvNamaUser.text = user.login
        }
    }
}