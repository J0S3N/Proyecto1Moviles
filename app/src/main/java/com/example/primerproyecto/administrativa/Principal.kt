package com.example.primerproyecto.administrativa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.primerproyecto.R

class Principal : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.administrativa_principal)


        val username = intent.getStringExtra("USERNAME") ?: "Usuario"


        val welcomeTextView: TextView = findViewById(R.id.welcomeTextView)
        welcomeTextView.text = getString(R.string.welcome_message, username)

        // Configurar los botones
        val addButton: Button = findViewById(R.id.addButton)
        val assignButton: Button = findViewById(R.id.assignButton)
        val closeButton: Button = findViewById(R.id.closeButton)

        addButton.setOnClickListener {
            abrirPaginaAgregarCliente()
        }

        assignButton.setOnClickListener {
            abrirPaginaAgregarPrestamo()
        }

        closeButton.setOnClickListener {
            finish()
        }
    }

    private fun abrirPaginaAgregarPrestamo() {
        val intent = Intent(this, Agregar_Prestamo::class.java)
        startActivity(intent)
    }

    private fun abrirPaginaAgregarCliente() {
        val intent = Intent(this, Agregar_Cliente::class.java)
        startActivity(intent)
    }
}