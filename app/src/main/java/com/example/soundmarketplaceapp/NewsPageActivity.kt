package com.example.soundmarketplaceapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class NewsPageActivity: AppCompatActivity() {
    private lateinit var btnUpload: ImageButton
    private lateinit var btnStore: ImageButton
    private lateinit var btnProfile: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_page)

        window.statusBarColor = ContextCompat.getColor(this, R.color.darkgrey) // Цвет статус-бара
        window.navigationBarColor = ContextCompat.getColor(this, R.color.darkgrey) // Цвет панели навигации

        btnUpload = findViewById(R.id.imgbtn_upload_news)
        btnUpload.setOnClickListener {
            val intent = Intent(this, UploadPageActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnStore = findViewById(R.id.imgbtn_store_news)
        btnStore.setOnClickListener {
            val intent = Intent(this, StorePageActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnProfile = findViewById(R.id.imgbtn_profile_news)
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}