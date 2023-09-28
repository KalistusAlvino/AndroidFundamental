package com.example.githubuser.ui.insert

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.database.entity.FavoriteEntity
import com.example.githubuser.data.repository.FavoriteRepository

class MainViewModel(application: Application): ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorite(): LiveData<List<FavoriteEntity>> = mFavoriteRepository.getAllFavorite()
}