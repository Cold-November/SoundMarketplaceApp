package com.example.soundmarketplaceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_authorization_page)

        //Добавление перехода
        val loginButton: Button = findViewById(R.id.btnEnter_ap)
        loginButton.setOnClickListener {
            val intent = Intent(this, AuthorizationPage2Activity::class.java)
            startActivity(intent)
        }

        val registrationButton: Button = findViewById(R.id.btnRegistration)
        registrationButton.setOnClickListener {
            val intent = Intent(this, RegistrationPageActivity::class.java)
            startActivity(intent)
        }
        }
    }