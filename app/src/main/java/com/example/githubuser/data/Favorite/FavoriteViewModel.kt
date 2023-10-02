package com.example.githubuser.data.Favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.database.entity.FavoriteEntity
import com.example.githubuser.data.database.room.FavoriteRoomDatabase
import com.example.githubuser.data.repository.FavoriteRepository

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(FavoriteRoomDatabase.getDatabase(application).favoriteDao())

    val fav: LiveData<List<FavoriteEntity>> = mFavoriteRepository.getAllFavorite()

    fun tambah(favorite: FavoriteEntity){
        mFavoriteRepository.insert(favorite)
    }
    fun hapus(favorite: FavoriteEntity){
        mFavoriteRepository.delete(favorite)
    }

    fun getSpecificFavorite(username: String): LiveData<FavoriteEntity> = mFavoriteRepository.getSpecificUser(username)
}