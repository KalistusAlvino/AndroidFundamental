package com.example.githubuser.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.githubuser.data.database.entity.FavoriteEntity
import com.example.githubuser.data.database.room.FavoriteDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(
    private val mFavoriteDao: FavoriteDao,
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()
){
    fun getAllFavorite(): LiveData<List<FavoriteEntity>> {
        return mFavoriteDao.getAllUser()
    }

    fun insert(favorite : FavoriteEntity){
        executorService.execute {
            mFavoriteDao.insert(favorite)
            Log.d("FavoriteRepository", "Insert user : ${favorite.username}")
        }
    }
    fun delete(favorite: FavoriteEntity) {
        executorService.execute {
            mFavoriteDao.delete(favorite)
            Log.d("FavoriteRepository", "Delete user : ${favorite.username}")
        }
    }
    fun getSpecificUser(username: String): LiveData<FavoriteEntity> {
        return mFavoriteDao.getUserFavorite(username)
    }
}