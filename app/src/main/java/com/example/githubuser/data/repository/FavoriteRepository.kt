package com.example.githubuser.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubuser.data.database.entity.FavoriteEntity
import com.example.githubuser.data.database.room.FavoriteDao
import com.example.githubuser.data.database.room.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application){
    private val mFavoriteDao: FavoriteDao
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorite(): LiveData<List<FavoriteEntity>> = mFavoriteDao.getAllFavorite()

    fun insert(favorite : FavoriteEntity){
        executorService.execute { mFavoriteDao.insert(favorite) }
    }
    fun delete(favorite: FavoriteEntity) {
        executorService.execute { mFavoriteDao.delete(favorite) }
    }
}