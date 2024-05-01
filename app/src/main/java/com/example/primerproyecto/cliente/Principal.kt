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

        val username = intent.getIntExtra("USER_ID", 1)

        val verPrestamosButton: Button = findViewById(R.id.verPrestamosButton)
        val gestionarAhorrosButton: Button = findViewById(R.id.gestionarAhorrosButton)
        val calcularCuotaButton: Button = findViewById(R.id.calcularCuotaButton)
        val informacionPersonalButton: Button = findViewById(R.id.informacionPersonalButton)
        val cerrarSesionButton: Button = findViewById(R.id.cerrarSesionButton)

        verPrestamosButton.setOnClickListener {
            abrirPaginaVerPrestamos(username)
        }

        gestionarAhorrosButton.setOnClickListener {

        }

        calcularCuotaButton.setOnClickListener {
            abrirCalcularCuota()
        }

        informacionPersonalButton.setOnClickListener {
            abrirPaginaInformacion(username)
        }

        cerrarSesionButton.setOnClickListener {
            finish()
        }
    }

    private fun abrirCalcularCuota() {
        val intent = Intent(this, Calcular_Cuota::class.java)
        startActivity(intent)
    }

    private fun abrirPaginaVerPrestamos(username: Int) {
        val intent = Intent(this, Ver_Prestamo::class.java)
        intent.putExtra("USER_ID", username)
        startActivity(intent)
    }

    private fun abrirPaginaInformacion(username: Int) {
        val intent = Intent(this, Ver_Informacion_Personal::class.java)
        intent.putExtra("USER_ID", username)
        startActivity(intent)
    }
}