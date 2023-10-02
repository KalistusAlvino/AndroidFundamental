package com.example.githubuser.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.githubuser.R
import com.example.githubuser.ui.MainActivity

class SplashActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splash : ImageView = findViewById(R.id.img_splash)
        splash.alpha = 0f
        splash.animate().setDuration(DURATION_ANIMATION).alpha(ALPHA_VAL).withEndAction{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
    companion object {
        private const val DURATION_ANIMATION = 2000L
        private const val ALPHA_VAL = 1f
    }
}