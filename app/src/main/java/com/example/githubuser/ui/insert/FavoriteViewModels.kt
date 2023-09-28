package com.example.githubuser.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.database.entity.FavoriteEntity
import com.example.githubuser.data.repository.FavoriteRepository

class FavoriteViewModels(application: Application) : ViewModel(){
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getFavorite() = mFavoriteRepository.getAllFavorite()

    fun insertFavorite(favorite: FavoriteEntity) {
        mFavoriteRepository.insert(favorite)
    }

    fun deleteFavorite(favorite: FavoriteEntity) {
        mFavoriteRepository.delete(favorite)
    }
}