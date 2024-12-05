package com.example.soundmarketplaceapp
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class AuthorizationPage2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization_page_2)
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkgrey) // Цвет статус-бара
        window.navigationBarColor = ContextCompat.getColor(this, R.color.darkgrey) // Цвет панели навигации
    }
}
