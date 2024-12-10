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

class AuthorizationPage2Activity : AppCompatActivity() {
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnEnter: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization_page_2)
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkgrey) // Цвет статус-бара
        window.navigationBarColor = ContextCompat.getColor(this, R.color.darkgrey) // Цвет панели навигации
        // Инициализация UI элементов
        editEmail = findViewById(R.id.editEmail_ap2)
        editPassword = findViewById(R.id.editPassword_ap2)
        btnEnter = findViewById(R.id.btnEnter_ap2)

        Log.d("Authorization", "UI элементы успешно инициализированы")

        // Обработчик нажатия на кнопку "Войти"
        btnEnter.setOnClickListener {
            Log.d("Authorization", "Кнопка 'Войти' нажата")
            attemptLogin()
        }

    }
    // Функция для выполнения авторизации
    private fun attemptLogin() {
        val email = editEmail.text.toString().trim()
        val password = editPassword.text.toString().trim()

        Log.d("Authorization", "Введённый email: $email")
        Log.d("Authorization", "Введённый пароль: $password")

        if (email.isEmpty() || password.isEmpty()) {
            Log.w("Authorization", "Одно из полей пустое")
            Toast.makeText(applicationContext, "Заполните все поля", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("Authorization", "Попытка авторизации через Firebase")
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Authorization", "Авторизация успешна")
                        Toast.makeText(this, "Авторизация успешна", Toast.LENGTH_SHORT).show()
                        navigateToProfileActivity()
                    } else {
                        val error = task.exception?.message
                        Log.e("Authorization", "Ошибка авторизации: $error")
                        Toast.makeText(
                            applicationContext,
                            "Ошибка авторизации: $error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    // Переход на экран профиля
    private fun navigateToProfileActivity() {
        Log.d("ProfileActivity", "Переход на экран профиля")
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish() // Закрыть текущую активность после успешного входа
    }
}
