package com.example.primerproyecto.cliente

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.primerproyecto.R

class Principal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cliente_principal)

        val username = intent.getStringExtra("USERNAME") ?: "Usuario"

        val verPrestamosButton: Button = findViewById(R.id.verPrestamosButton)
        val gestionarAhorrosButton: Button = findViewById(R.id.gestionarAhorrosButton)
        val calcularCuotaButton: Button = findViewById(R.id.calcularCuotaButton)
        val informacionPersonalButton: Button = findViewById(R.id.informacionPersonalButton)
        val cerrarSesionButton: Button = findViewById(R.id.cerrarSesionButton)

        verPrestamosButton.setOnClickListener {
            // Código para ver los préstamos
        }

        gestionarAhorrosButton.setOnClickListener {
            // Código para gestionar los ahorros
        }

        calcularCuotaButton.setOnClickListener {
            // Código para calcular la cuota
        }

        informacionPersonalButton.setOnClickListener {
            abrirPaginaInformacion(username)
        }

        cerrarSesionButton.setOnClickListener {
            finish()
        }
    }

    private fun abrirPaginaInformacion(username: String) {
        val intent = Intent(this, Ver_Informacion_Personal::class.java)
        intent.putExtra("USER_ID", username)
        startActivity(intent)
    }
}