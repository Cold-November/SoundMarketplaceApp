package com.example.soundmarketplaceapp
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class RegistrationPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_page)
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkgrey) // Цвет статус-бара
        window.navigationBarColor = ContextCompat.getColor(this, R.color.darkgrey) // Цвет панели навигации
    }
}
