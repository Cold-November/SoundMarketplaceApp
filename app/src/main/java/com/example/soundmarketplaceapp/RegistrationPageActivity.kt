package com.example.soundmarketplaceapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistrationPageActivity : AppCompatActivity() {

    private lateinit var editLogin: EditText
    private lateinit var editPassword: EditText
    private lateinit var editEmail: EditText
    private lateinit var btnSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_page)

        // Инициализация компонентов
        editLogin = findViewById(R.id.editLogin_ap)
        editPassword = findViewById(R.id.editPassword_ap)
        editEmail = findViewById(R.id.editEmail_ap)
        btnSignUp = findViewById(R.id.btnSignUp_ap)

        // Настройка цветов статус-бара и панели навигации
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkgrey)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.darkgrey)

        btnSignUp.setOnClickListener {
            val login = editLogin.text.toString().trim()
            val password = editPassword.text.toString().trim()
            val email = editEmail.text.toString().trim()

            if (login.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(email, password, login)
                navigateToMainActivity()
            }
        }
    }

    private fun registerUser(email: String, password: String, login: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    val userId = FirebaseAuth.getInstance().currentUser?.uid
                    if (userId != null) {
                        Log.d("Registration", "Регистрация успешна. userId: $userId")

                        val userInformation = mapOf(
                            "login" to login,
                            "email" to email
                        )

                        FirebaseDatabase.getInstance().reference
                            .child("users")
                            .child(userId)
                            .setValue(userInformation)
                            .addOnCompleteListener { dbTask ->
                                if (dbTask.isSuccessful) {
                                    Log.d("Registration", "Данные успешно записаны в базу")
                                    Toast.makeText(this, "Регистрация завершена", Toast.LENGTH_SHORT).show()
                                    //navigateToMainActivity()
                                } else {
                                    Log.e("Registration", "Ошибка записи в базу: ${dbTask.exception}")
                                    Toast.makeText(this, "Ошибка при сохранении данных", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } else {
                        Log.e("Registration", "Ошибка: userId равен null")
                        Toast.makeText(this, "Не удалось получить userId", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("Registration", "Ошибка регистрации: ${authTask.exception}")
                    Toast.makeText(this, "Ошибка регистрации: ${authTask.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToMainActivity() {
        Log.d("Registration", "Переход в AuthorizationPage2Activity")
        val intent = Intent(this, AuthorizationPage2Activity::class.java)
        startActivity(intent)
        finish() // Закрытие текущей активности
    }
}
