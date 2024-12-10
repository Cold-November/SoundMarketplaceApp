package com.example.soundmarketplaceapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ProfileActivity: AppCompatActivity() {
    private lateinit var btnUpload: ImageButton
    private lateinit var btnNews: ImageButton
    private lateinit var btnStore: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        window.statusBarColor = ContextCompat.getColor(this, R.color.darkgrey) // Цвет статус-бара
        window.navigationBarColor = ContextCompat.getColor(this, R.color.darkgrey) // Цвет панели навигации

        btnUpload = findViewById(R.id.imgbtn_upload_profile)
        btnUpload.setOnClickListener {
            val intent = Intent(this, UploadPageActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnNews = findViewById(R.id.imgbtn_news_profile)
        btnNews.setOnClickListener {
            val intent = Intent(this, NewsPageActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnStore = findViewById(R.id.imgbtn_store_profile)
        btnStore.setOnClickListener {
            val intent = Intent(this, StorePageActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}