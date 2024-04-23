package com.example.primerproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)
        val exitButton: Button = findViewById(R.id.exitButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            // Aquí iría la lógica para verificar las credenciales y determinar el rol del usuario
            // Navegar a la pantalla principal correspondiente basada en el rol del usuario
        }

        exitButton.setOnClickListener {
            // Cerrar la aplicación
            finish()
        }
    }
}