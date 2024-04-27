package com.example.primerproyecto.administrativa

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.primerproyecto.R

class Principal : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.administrativa_principal)

        // Obtener el nombre de usuario del Intent
        val username = intent.getStringExtra("USERNAME") ?: "Usuario"

        // Configurar el mensaje de bienvenida
        val welcomeTextView: TextView = findViewById(R.id.welcomeTextView)
        welcomeTextView.text = getString(R.string.welcome_message, username)

        // Configurar los botones
        val addButton: Button = findViewById(R.id.addButton)
        val assignButton: Button = findViewById(R.id.assignButton)
        val closeButton: Button = findViewById(R.id.closeButton)

        addButton.setOnClickListener {
            // Código para agregar un cliente nuevo
        }

        assignButton.setOnClickListener {
            // Código para asignar un préstamo a un cliente ya existente
        }

        closeButton.setOnClickListener {
            // Código para cerrar la sesión
            finish()
        }
    }
}