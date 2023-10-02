package com.example.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.data.Favorite.FavoriteViewModel
import com.example.githubuser.data.Favorite.FavoriteViewModelFactory
import com.example.githubuser.data.response.ItemsItem
import com.example.githubuser.databinding.ActivityFavUserBinding

class FavUserActivity : AppCompatActivity() {
    private lateinit var favBinding: ActivityFavUserBinding
    private lateinit var favAdapter: UserAdapter
    private lateinit var favViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favBinding = ActivityFavUserBinding.inflate(layoutInflater)
        setContentView(favBinding.root)

        val favoriteFactory = FavoriteViewModelFactory(application)
        favViewModel = ViewModelProvider(this, favoriteFactory).get(FavoriteViewModel::class.java)

        favAdapter = UserAdapter()
        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)

        with(favBinding.rvFavoriteUser) {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
            addItemDecoration(itemDecoration)
            adapter = favAdapter
        }

        favViewModel.fav.observe(this) { favorite ->
            val items = arrayListOf<ItemsItem>()
            favorite?.map {
                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl.toString())
                items.add(item)
            }
            favAdapter.submitList(items)
        }

        // Hide the ActionBar
        supportActionBar?.hide()

    }

}