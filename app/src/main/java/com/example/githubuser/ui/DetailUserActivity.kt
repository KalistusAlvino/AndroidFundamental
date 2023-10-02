package com.example.githubuser.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.data.Favorite.FavoriteViewModel
import com.example.githubuser.data.database.entity.FavoriteEntity
import com.example.githubuser.data.response.DetailUserResponse
import com.example.githubuser.data.viewModel.DetailViewModel
import com.example.githubuser.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object {
        const val EXTRA_DETAIL = "username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_followers,
            R.string.tab_following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraDetail = intent.getStringExtra(EXTRA_DETAIL) as String
        val sectionsPagerAdapter = SectionsPagerAdapter(this, extraDetail)

        detailViewModel.cariDetailUser(extraDetail)
        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        detailViewModel.detailUser.observe(this) { detailUser ->
            setDetailUserData(detailUser)
        }

        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }
    private fun setDetailUserData(detailUser: DetailUserResponse) {
        binding.tvUserNameDetail.text = detailUser.login
        binding.tvNamaDetail.text = detailUser.name
        binding.tvJumlahFollowers.text = resources.getString(R.string.followers, detailUser.followers)
        binding.tvJumlahFollowing.text = resources.getString(R.string.following, detailUser.following)
        Glide.with(binding.root.context)
            .load(detailUser.avatarUrl)
            .into(binding.detailProfileImage)

        val favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        var isFavorite = false

        favoriteViewModel.getSpecificFavorite(detailUser.login).observe(this){
            if (it!= null) {
                isFavorite = true
                binding.fabFavBorder.setImageResource(R.drawable.ic_favorite)
            } else {
                isFavorite = false
                binding.fabFavBorder.setImageResource(R.drawable.ic_fav_border)
            }
        }
        binding.fabFavBorder.setOnClickListener {
            val insert = FavoriteEntity(
                username = detailUser.login,
                avatarUrl = detailUser.avatarUrl
            )
            val delete = FavoriteEntity(
                username = detailUser.login,
                avatarUrl = detailUser.avatarUrl
            )
            if (isFavorite){
                favoriteViewModel.hapus(delete)
            } else {
                favoriteViewModel.tambah(insert)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
