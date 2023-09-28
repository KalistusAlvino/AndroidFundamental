package com.example.githubuser.data.database.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.githubuser.data.database.entity.FavoriteEntity

class FavoriteDiffCallBack(private val oldFavoriteList: List<FavoriteEntity>,private val newFavoriteList: List<FavoriteEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldFavoriteList.size

    override fun getNewListSize(): Int = newFavoriteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFavoriteList[oldItemPosition].username == newFavoriteList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavorite = oldFavoriteList[oldItemPosition]
        val newFavorite = newFavoriteList[oldItemPosition]
        return oldFavorite.avatarUrl == newFavorite.avatarUrl
    }
}